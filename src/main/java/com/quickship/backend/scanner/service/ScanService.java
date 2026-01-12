package com.quickship.backend.scanner.service;

import com.quickship.backend.packagehub.model.PackageItem;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ScanService {

    @Async("scannerExecutor")
    public void scan(PackageItem item) {
        try {
            System.out.println(Thread.currentThread().getName()
                    + " scanning package " + item.getId());

            long delay = 3000;
            Thread.sleep(delay);

            item.setStatus("SORTED");

            System.out.println("Package " + item.getId() + " sorted");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
