package com.quickship.backend.security.controller;

import com.quickship.backend.security.model.LoginRequest;
import com.quickship.backend.security.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if (request.username == null || request.password == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        String role;

        if (request.username.equals("manager") && request.password.equals("pass")) {
            role = "MANAGER";
        } else if (request.username.equals("driver") && request.password.equals("pass")) {
            role = "DRIVER";
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateToken(request.username, role);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
