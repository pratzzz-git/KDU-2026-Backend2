package com.quickship.backend.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiry}")
    public long expiry;
}
