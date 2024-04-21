package org.example.vehicle;

import lombok.Data;
import org.example.model.VehicleType;

@Data
abstract public class Vehicle {
    private final String licenseNumber;

    private final VehicleType vehicleType;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }
}

