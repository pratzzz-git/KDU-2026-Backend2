package org.example.service;

import org.example.dto.OnboardingRequest;
import org.example.dto.ShiftTypeDTO;
import org.example.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OnboardingService {

    private final JdbcTemplate jdbc;

    public OnboardingService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void onboardHospital(OnboardingRequest req) {

        for (ShiftTypeDTO st : req.shiftTypes) {
            String id = UUID.randomUUID().toString();

            jdbc.update(
                    "INSERT INTO shift_type(id, name, description, active, tenant_id) VALUES(?,?,?,?,?)",
                    id, st.name, st.description, st.active, req.tenantId
            );
        }


        for (UserDTO u : req.users) {
            String id = UUID.randomUUID().toString();

            jdbc.update(
                    "INSERT INTO users(id, username, logged_in, timezone, tenant_id) VALUES(?,?,?,?,?)",
                    id, u.username, u.loggedIn, u.timezone, req.tenantId
            );
        }
   jdbc.update("INSERT INTO users(id, username) VALUES (?,?)",
                UUID.randomUUID().toString(), null);
    }
}
