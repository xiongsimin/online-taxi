package kim.aries.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SIGN = "DSgdfh5.DSFDGD5";

    public static String generateJwtToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date time = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        builder.withExpiresAt(time);

        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "白羊");
        map.put("age", "26");
        System.out.println(generateJwtToken(map));
    }
}
