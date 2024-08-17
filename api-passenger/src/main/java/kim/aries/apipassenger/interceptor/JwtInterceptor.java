package kim.aries.apipassenger.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import kim.aries.apipassenger.util.RedisKeyUtil;
import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.TokenResultDto;
import kim.aries.internalcommon.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        boolean tokenVerified = true;
        String errMsg = "";
        TokenResultDto tokenResultDto = null;
        try {
            tokenResultDto = JwtUtil.parseJwtToken(token);
        } catch (SignatureVerificationException e) {
            tokenVerified = false;
            errMsg = "token sign error";
        } catch (AlgorithmMismatchException e) {
            tokenVerified = false;
            errMsg = "token AlgorithmMismatch";
        } catch (Exception e) {
            tokenVerified = false;
            errMsg = "token invalid";
        }

        if (tokenResultDto == null) {
            tokenVerified = false;
            errMsg = "token invalid";
        } else {

            String tokenKey = RedisKeyUtil.getTokenKey(tokenResultDto.getPhone(), tokenResultDto.getIdentity());
            String tokenFromRedis = stringRedisTemplate.opsForValue().get(tokenKey);

            if (StringUtils.isBlank(token)) {
                tokenVerified = false;
                errMsg = "token invalid";
            } else {
                if (!Objects.equals(tokenFromRedis, token)) {
                    tokenVerified = false;
                    errMsg = "token invalid";
                }
            }
        }

        if (!tokenVerified) {
            PrintWriter out = response.getWriter();
            String s = JSON.toJSONString(CommonResponseResultDto.fail(errMsg));
            out.print(s);
        }

        return tokenVerified;
    }
}
