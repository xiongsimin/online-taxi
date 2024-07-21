package kim.aries.servicepassengeruser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("passenger_user")
public class PassengerUserEntity {
    private Long id;
    private String passengerPhone;
    private String passengerName;
    private Integer passengerGender;
    private Integer state;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
