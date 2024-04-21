package org.example.vehicle;

import org.example.model.VehicleType;
import org.example.vehicle.Vehicle;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}
