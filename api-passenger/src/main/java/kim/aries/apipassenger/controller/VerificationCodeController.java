package kim.aries.apipassenger.controller;

import kim.aries.apipassenger.dto.VerificationCodeDto;
import kim.aries.apipassenger.service.VerificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    VerificationService verificationService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDto dto) throws Exception {

        if (dto == null || StringUtils.isBlank(dto.getPassengerPhone())) {
            throw new Exception("参数异常");
        }
        return verificationService.generatorCode(dto.getPassengerPhone());
    }
}
