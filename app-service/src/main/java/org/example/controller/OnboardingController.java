package org.example.controller;

import org.example.dto.OnboardingRequest;
import org.example.service.OnboardingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class OnboardingController {

    private final OnboardingService service;

    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    @PostMapping("/onboard")
    public String onboard(@RequestBody OnboardingRequest req) {
        service.onboardHospital(req);
        return "Onboarding completed";
    }
}
