package org.example.service;

import org.example.dto.UserDTO;
import org.example.dto.ShiftTypeDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class HospitalJdbcService {

    private final JdbcTemplate jdbc;

    public HospitalJdbcService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public String saveUser(UserDTO dto) {
        String id = UUID.randomUUID().toString();
        jdbc.update(
                "INSERT INTO users(id, username, logged_in, timezone, tenant_id) VALUES(?,?,?,?,?)",
                id, dto.username, dto.loggedIn, dto.timezone, dto.tenantId
        );
        return id;
    }

    public String saveShiftType(ShiftTypeDTO dto) {
        String id = UUID.randomUUID().toString();
        jdbc.update(
                "INSERT INTO shift_type(id, name, description, active, tenant_id) VALUES(?,?,?,?,?)",
                id, dto.name, dto.description, dto.active, dto.tenantId
        );
        return id;
    }

    public List<Map<String, Object>> getUsersByTenant(String tenantId) {
        return jdbc.queryForList("SELECT * FROM users WHERE tenant_id=?", tenantId);
    }

    public List<Map<String, Object>> getShiftsByTenant(String tenantId) {
        return jdbc.queryForList("SELECT * FROM shift WHERE tenant_id=?", tenantId);
    }

    public void updateUser(String id, String tenantId, String username, String timezone) {
        jdbc.update(
                "UPDATE users SET username=?, timezone=? WHERE id=? AND tenant_id=?",
                username, timezone, id, tenantId
        );
    }

    public List<Map<String, Object>> getUsersPaged(String tenantId, int page, int size, String sort) {
        int offset = page * size;
        String order = sort.equalsIgnoreCase("desc") ? "DESC" : "ASC";
        String sql =
                "SELECT * FROM users WHERE tenant_id=? ORDER BY username " + order + " LIMIT ? OFFSET ?";
        return jdbc.queryForList(sql, tenantId, size, offset);
    }
}
