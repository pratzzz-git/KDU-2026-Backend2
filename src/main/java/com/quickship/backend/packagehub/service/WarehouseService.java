package com.quickship.backend.packagehub.service;

import com.quickship.backend.packagehub.model.PackageItem;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WarehouseService {

    private final Map<String, PackageItem> warehouse = new ConcurrentHashMap<>();

    public PackageItem addPackage(PackageItem item) {
        String id = UUID.randomUUID().toString();
        item.setId(id);
        item.setStatus("PENDING");

        warehouse.put(id, item);
        return item;
    }

    public long countByStatus(String status) {
        return warehouse.values().stream()
                .filter(p -> p.getStatus().equals(status))
                .count();
    }

    public Collection<PackageItem> getAll() {
        return warehouse.values();
    }
}
