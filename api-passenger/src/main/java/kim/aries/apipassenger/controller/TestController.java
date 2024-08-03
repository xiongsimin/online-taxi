package kim.aries.apipassenger.controller;

import kim.aries.internalcommon.dto.CommonResponseResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test api passenger";
    }

    @GetMapping("/auth-test")
    public CommonResponseResultDto<Object> authTest() {
        return CommonResponseResultDto.success("auth test");
    }

    @GetMapping("/noauth-test")
    public CommonResponseResultDto<Object> noAuthTest() {
        return CommonResponseResultDto.success("noauth test");
    }
}
