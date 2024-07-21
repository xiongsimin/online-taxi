package kim.aries.servicepassengeruser.controller;

import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.VerificationCodeDto;
import kim.aries.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public CommonResponseResultDto<Object> loginOrRegister(@RequestBody VerificationCodeDto dto) {
        System.out.println("手机号是：" + dto.getPassengerPhone());

        userService.loginOrRegister(dto.getPassengerPhone());
        return CommonResponseResultDto.success();
    }
}
