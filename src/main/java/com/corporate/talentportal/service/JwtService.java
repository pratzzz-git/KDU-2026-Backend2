package com.corporate.talentportal.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    "corporate-secret-key-which-is-at-least-32-bytes-long"
                            .getBytes()
            );

    public String generateToken(String username, List<String> roles) {

        return Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("roles", roles))
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 3600000)
                )
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
