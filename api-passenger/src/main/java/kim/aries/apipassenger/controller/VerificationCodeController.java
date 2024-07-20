package kim.aries.apipassenger.controller;

import kim.aries.apipassenger.dto.VerificationCodeDto;
import kim.aries.apipassenger.remote.ServiceVerificationCodeClient;
import kim.aries.apipassenger.service.VerificationService;
import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.NumberCodeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    VerificationService verificationService;
    @Autowired
    ServiceVerificationCodeClient verificationCodeClient;

    @GetMapping("/verification-code")
    public CommonResponseResultDto<NumberCodeDto> verificationCode(@RequestBody VerificationCodeDto dto) throws Exception {

        if (dto == null || StringUtils.isBlank(dto.getPassengerPhone())) {
            throw new Exception("参数异常");
        }

        CommonResponseResultDto<NumberCodeDto> numberCode = verificationCodeClient.getNumberCode(6);
        System.out.println("验证码：" + numberCode.getData().getNumberCode());
        return CommonResponseResultDto.success(null);
    }
}
