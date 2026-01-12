package com.quickship.backend.analytics.controller;

import com.quickship.backend.analytics.service.AnalyticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/revenue")
    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    public Map<String, Double> getRevenue() {
        double revenue = analyticsService.calculateRevenue();
        return Map.of("revenue", revenue);
    }
}
