package com.corporate.talentportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<Map<String, String>> users = new ArrayList<>();

    public UserController() {
        users.add(Map.of("username", "basic"));
        users.add(Map.of("username", "admin"));
    }

    // BASIC + ADMIN can view users
    @GetMapping
    @PreAuthorize("hasAnyRole('BASIC', 'ADMIN')")
    public List<Map<String, String>> getUsers() {
        return users;
    }

    // ONLY ADMIN can add users
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@RequestBody Map<String, String> request) {
        users.add(Map.of("username", request.get("username")));
        return "User added successfully";
    }
}
