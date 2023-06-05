package com.example.sw.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Util {

    public static long getUserId(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userid", Long.class);
    }

    public static boolean isExpired(String token, String secretKey) {
        // new Date() (현재시각) 기준으로 전이면 True, 아니면 false 라는 의미인듯 싶다
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    private static final long expireMs = 86400000; // 토큰 만료 시간 (하루)
    public static String createJwt(long userid, String secretKey) {
        Claims claims = Jwts.claims();
        claims.put("userid", userid);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireMs))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}
