package kim.aries.apipassenger.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    public String generatorCode(String passengerPhone) {
        System.out.println("调用验证码服务，拿到验证码");

        System.out.println("验证码存入redis");

        System.out.println("异步发送验证码");
        // 返回结果
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("message1", "success");

        return jsonObject.toJSONString();
    }
}
