package kim.aries.apipassenger.remote;

import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.VerificationCodeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    /**
     * 登录/注册获取验证码
     *
     * @param dto 入参
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    CommonResponseResultDto<Object> loginOrRegister(@RequestBody VerificationCodeDto dto);
}
