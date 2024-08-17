package kim.aries.serviceverificationcode.controller;

import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.NumberCodeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {
    @GetMapping("/number-code/{size}")
    public CommonResponseResultDto<NumberCodeDto> numberCode(@PathVariable("size") int size) throws Exception {

        System.out.println("size = " + size);
        if (size <= 0 || size > 8) {
            throw new Exception("参数错误");
        }

        NumberCodeDto numberCodeDto = new NumberCodeDto();
        numberCodeDto.setNumberCode(generateNumberCode(size));

        return CommonResponseResultDto.success(numberCodeDto);
    }

    private int generateNumberCode(int size) {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, size - 1));
    }

    public static void main(String[] args) {
        NumberCodeController controller = new NumberCodeController();
        System.out.println(controller.generateNumberCode(1));
    }
}
