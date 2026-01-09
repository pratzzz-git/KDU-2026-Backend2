package com.corporate.talentportal.controller;

import com.corporate.talentportal.model.User;
import com.corporate.talentportal.service.JwtService;
import com.corporate.talentportal.store.InMemoryUserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    private final InMemoryUserStore userStore;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(InMemoryUserStore userStore, JwtService jwtService) {
        this.userStore = userStore;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        User user = userStore.findByUsername(request.get("username"));

        if (user == null || !encoder.matches(
                request.get("password"),
                user.getPassword())
        ) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getUserName(),
                user.getRoles()
        );

        logger.info("User {} logged in successfully", user.getUserName());

        return Map.of("token", token);
    }
}
