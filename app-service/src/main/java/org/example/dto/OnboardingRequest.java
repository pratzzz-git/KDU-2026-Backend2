package org.example.dto;

import java.util.List;

public class OnboardingRequest {

    public String tenantId;

    public List<ShiftTypeDTO> shiftTypes;
    public List<UserDTO> users;

    // For simplicity we auto-create shifts (Morning, Afternoon, Evening)
}
