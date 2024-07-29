package kim.aries.apipassenger.service;

import kim.aries.apipassenger.dto.TokenDto;
import kim.aries.apipassenger.enums.EnumBizException;
import kim.aries.apipassenger.remote.ServicePassengerUserClient;
import kim.aries.apipassenger.remote.ServiceVerificationCodeClient;
import kim.aries.apipassenger.util.RedisKeyUtil;
import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.NumberCodeDto;
import kim.aries.internalcommon.dto.verificationcode.VerificationCodeDto;
import kim.aries.internalcommon.enums.EnumUserIdentity;
import kim.aries.internalcommon.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationService {
    @Autowired
    ServiceVerificationCodeClient verificationCodeClient;
    @Autowired
    ServicePassengerUserClient passengerUserClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public CommonResponseResultDto<Object> generatorCode(String passengerPhone) {
        CommonResponseResultDto<NumberCodeDto> numberCode = verificationCodeClient.getNumberCode(6);
        System.out.println("调用生成验证码服务，拿到验证码：" + numberCode.getData().getNumberCode());
        // 验证码存入redis
        stringRedisTemplate.opsForValue().set(RedisKeyUtil.getPassengerVerificationCodeKey(passengerPhone), numberCode.getData().getNumberCode() + "", 2, TimeUnit.MINUTES);

        System.out.println("异步发送验证码");
        return CommonResponseResultDto.success();
    }

    public CommonResponseResultDto<TokenDto> checkCode(String passengerPhone, String verificationCode) {
        // 从redis缓存中查询验证码
        String numberCode = stringRedisTemplate.opsForValue().get(RedisKeyUtil.getPassengerVerificationCodeKey(passengerPhone));
        // 如果已过期，则失败
        if (StringUtils.isBlank(numberCode)) {
            System.out.println("redis中不存在验证码");
            return CommonResponseResultDto.fail(0, "验证码错误");
        }
        // 校验验证码
        if (!numberCode.equals(verificationCode)) {
            System.out.println("校验验证码失败");
            return CommonResponseResultDto.fail(EnumBizException.VERIFICATION_CODE_ERR.getCode(), EnumBizException.VERIFICATION_CODE_ERR.getMessage());
        }

        VerificationCodeDto dto = new VerificationCodeDto();
        dto.setPassengerPhone(passengerPhone);

        passengerUserClient.loginOrRegister(dto);
        System.out.println("用户登录/注册成功");
        // 发放token
        String token = JwtUtil.generateJwtToken(passengerPhone, EnumUserIdentity.PASSENGER);

        System.out.println("校验成功，发放token：" + token);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);

        return CommonResponseResultDto.success(tokenDto);
    }
}
