package kim.aries.apipassenger.remote;

import kim.aries.internalcommon.dto.CommonResponseResultDto;
import kim.aries.internalcommon.dto.verificationcode.NumberCodeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-verification-code")
public interface ServiceVerificationCodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/number-code/{size}")
    CommonResponseResultDto<NumberCodeDto> getNumberCode(@PathVariable("size") int size);
}
