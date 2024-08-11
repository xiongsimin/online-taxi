package kim.aries.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import kim.aries.internalcommon.dto.TokenResultDto;
import kim.aries.internalcommon.enums.EnumUserIdentity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SIGN = "DSgdfh5.DSFDGD5";

    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTITY = "identity";

    public static String generateJwtToken(String phone, EnumUserIdentity userIdentity) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, userIdentity.getCode());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date time = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        builder.withExpiresAt(time);

        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    public static TokenResultDto parseJwtToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim phoneClaim = decodedJWT.getClaim(JWT_KEY_PHONE);
        Claim identityClaim = decodedJWT.getClaim(JWT_KEY_IDENTITY);
        TokenResultDto tokenResultDto = new TokenResultDto();
        tokenResultDto.setPhone(phoneClaim.toString());
        tokenResultDto.setIdentity(identityClaim.toString());

        return tokenResultDto;
    }

    public static void main(String[] args) {
        String token = generateJwtToken("15000007795", EnumUserIdentity.PASSENGER);
        System.out.println("token = " + token);

        System.out.println("解析token = " + parseJwtToken(token));

        System.out.println("aries");
    }
}
