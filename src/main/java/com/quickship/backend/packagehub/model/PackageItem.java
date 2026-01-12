package com.quickship.backend.packagehub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PackageItem {

    private String id;

    @NotBlank
    private String destination;

    @Positive
    private double weight;

    private String status;   // PENDING or SORTED
    private String deliveryType; // STANDARD or EXPRESS

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDeliveryType() { return deliveryType; }
    public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }
}
