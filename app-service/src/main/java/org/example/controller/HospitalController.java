package org.example.controller;

import org.example.dto.UserDTO;
import org.example.dto.ShiftTypeDTO;
import org.example.service.HospitalJdbcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalJdbcService service;

    public HospitalController(HospitalJdbcService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDTO dto) {
        return service.saveUser(dto);
    }

    @PostMapping("/shift-types")
    public String addShiftType(@RequestBody ShiftTypeDTO dto) {
        return service.saveShiftType(dto);
    }

    @GetMapping("/users/{tenantId}")
    public List<Map<String, Object>> getUsers(@PathVariable String tenantId) {
        return service.getUsersByTenant(tenantId);
    }

    @GetMapping("/shifts/{tenantId}")
    public List<Map<String, Object>> getShifts(@PathVariable String tenantId) {
        return service.getShiftsByTenant(tenantId);
    }

    @PutMapping("/users/{tenantId}/{id}")
    public void updateUser(
            @PathVariable String tenantId,
            @PathVariable String id,
            @RequestParam String username,
            @RequestParam String timezone
    ) {
        service.updateUser(id, tenantId, username, timezone);
    }

    @GetMapping("/users/{tenantId}/paged")
    public List<Map<String, Object>> getUsersPaged(
            @PathVariable String tenantId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort
    ) {
        return service.getUsersPaged(tenantId, page, size, sort);
    }
}
