package tmaxfintech.wf.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpHeaders;
import tmaxfintech.wf.config.jwt.JwtProperty;

import java.util.Date;

public class JwtUtility {

    public String createJwtToken(String username, Long id) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (JwtProperty.EXPRIATION_TIME)))
                .withClaim("id", id)
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(JwtProperty.SECRET));
    }

    public String getUsernameFromJwtToken(String jwtToken) {
        return JWT.require(Algorithm.HMAC512(JwtProperty.SECRET)).build().verify(jwtToken).getClaim("username").asString();
    }

    public String getJwtTokenFromHeader(HttpHeaders headers) {
        return headers.get("Authorization").get(0).replace(JwtProperty.TOKEN_PREFIX, "");
    }
}
