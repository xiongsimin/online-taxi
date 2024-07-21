package kim.aries.apipassenger.util;

import kim.aries.apipassenger.consts.RedisKeyConst;

public class RedisKeyUtil {
    public static String getPassengerVerificationCodeKey(String passengerPhone) {
        return RedisKeyConst.PASSENGER_VERIFICATION_CODE_KEY_PREFIX + passengerPhone;
    }
}
