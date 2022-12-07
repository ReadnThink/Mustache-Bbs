package com.practicemustach.bbs2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }
    public static String createToken(String userName, String secretKey, long expiredTimeMs){
        Claims claims = Jwts.claims(); //일종의 Map이다 Claims로 정보를 담을 수 있다.
        claims.put("userName", userName); //userName을 claims에 담는다

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //시작시간
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs)) //만료시간
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes()) //어떤 알고리즘을 사용할지
                .compact();
    }
}
