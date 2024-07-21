package kim.aries.servicepassengeruser.service;

import kim.aries.servicepassengeruser.entity.PassengerUserEntity;
import kim.aries.servicepassengeruser.mapper.PassengerUserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public void loginOrRegister(String passengerPhone) {
        Map<String, Object> param = new HashMap<>();
        param.put("passenger_phone", passengerPhone);
        List<PassengerUserEntity> passengerUserEntities = passengerUserMapper.selectByMap(param);
        if (CollectionUtils.isEmpty(passengerUserEntities)) {
            System.out.println("手机号未注册过，需要创建");
            PassengerUserEntity passengerUserEntity = new PassengerUserEntity();
            passengerUserEntity.setPassengerPhone(passengerPhone);
            passengerUserEntity.setPassengerName("张三");
            passengerUserEntity.setPassengerGender(1);
            passengerUserEntity.setState(0);

            LocalDateTime now = LocalDateTime.now();
            passengerUserEntity.setGmtCreate(now);
            passengerUserEntity.setGmtModified(now);

            passengerUserMapper.insert(passengerUserEntity);
        } else {
            System.out.println("用户信息：" + passengerUserEntities.get(0).toString());
        }
        System.out.println("UserService.loginOrRegister，passengerPhone = " + passengerPhone);
    }
}
