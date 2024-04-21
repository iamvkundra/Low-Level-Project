package org.example.vehicle;

import org.example.model.VehicleType;

public class Bike extends Vehicle {
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleType.BIKE);
    }
}
