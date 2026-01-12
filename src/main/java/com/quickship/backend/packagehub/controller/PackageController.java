package com.quickship.backend.packagehub.controller;

import com.quickship.backend.packagehub.model.PackageItem;
import com.quickship.backend.packagehub.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final WarehouseService warehouseService;

    public PackageController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addPackage(@Valid @RequestBody PackageItem item) {
        PackageItem saved = warehouseService.addPackage(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
