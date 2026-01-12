package com.quickship.backend.analytics.service;

import com.quickship.backend.packagehub.service.WarehouseService;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final WarehouseService warehouseService;

    public AnalyticsService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public double calculateRevenue() {
        return warehouseService.getAll().stream()
                .filter(p -> p.getStatus().equals("SORTED"))
                .mapToDouble(p -> p.getWeight() * 2.50)
                .sum();
    }
}
