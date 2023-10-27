package com.example.gradesubmission.security.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gradesubmission.security.SecurityConstants;

@Component
public class JwtUtil {
    
    public String createJWT(String subject) {
        String token = JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        return token;
    }

    public String verifyAndGetSubject(String token) {
        return JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
            .build()
            .verify(token)
            .getSubject();
    }
}
