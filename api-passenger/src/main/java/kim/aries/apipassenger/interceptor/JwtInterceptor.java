package kim.aries.apipassenger.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        boolean tokenVerified = true;
        String errMsg = "";
        try {
            JwtUtil.parseJwtToken(token);
        } catch (SignatureVerificationException e) {
            tokenVerified = false;
            errMsg = "token sign error";
        } catch (TokenExpiredException e) {
            tokenVerified = false;
            errMsg = "token time out";
        } catch (AlgorithmMismatchException e) {
            tokenVerified = false;
            errMsg = "token AlgorithmMismatch";
        } catch (Exception e) {
            tokenVerified = false;
            errMsg = "token invalid";
        }
        if (!tokenVerified) {
            PrintWriter out = response.getWriter();
            String s = JSON.toJSONString(CommonResponseResultDto.fail(errMsg));
            out.print(s);
        }

        return tokenVerified;
    }
}
