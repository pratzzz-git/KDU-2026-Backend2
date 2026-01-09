package com.corporate.talentportal.store;

import com.corporate.talentportal.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStore {

    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserStore() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        users.put("basic",
                new User(
                        "basic",
                        encoder.encode("password"),
                        "basic@corp.com",
                        List.of("ROLE_BASIC")
                )
        );

        users.put("admin",
                new User(
                        "admin",
                        encoder.encode("password"),
                        "admin@corp.com",
                        List.of("ROLE_ADMIN")
                )
        );
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}
