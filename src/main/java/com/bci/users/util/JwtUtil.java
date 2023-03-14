package com.bci.users.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String getAuthenticationJWT(String username, String email) {
        Map<String,String> claims = new HashMap<>();
        claims.put("user",username);
        claims.put("email",email);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
        return jwt;
    }

    public static String getEmailUser(String token) {
        String email = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token.replace("Bearer", ""))
            .getBody()
            .getSubject();
        return email;
    }





}
