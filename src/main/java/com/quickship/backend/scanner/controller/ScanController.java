package com.quickship.backend.scanner.controller;

import com.quickship.backend.packagehub.service.WarehouseService;
import com.quickship.backend.scanner.service.ScanService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scan")
public class ScanController {

    private final WarehouseService warehouseService;
    private final ScanService scanService;

    public ScanController(WarehouseService warehouseService, ScanService scanService) {
        this.warehouseService = warehouseService;
        this.scanService = scanService;
    }

    @PostMapping("/start")
    @PreAuthorize("hasRole('MANAGER')")
    public String startScan() {
        warehouseService.getAll().forEach(scanService::scan);
        return "Scan started";
    }
}
