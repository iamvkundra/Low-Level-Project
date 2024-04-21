package org.example.model;

import java.util.UUID;

import lombok.Data;
import org.example.vehicle.Vehicle;

@Data
public class ParkingSpot {
    private String id;

    private Integer floorId;

    private boolean isOccupied;

    private Vehicle vehicle = null;

    private VehicleType parkingType;

    public ParkingSpot(int floorId, Vehicle vehicle) {
        this.id = UUID.randomUUID().toString();
        occupySpot(vehicle);
        this.floorId = floorId;
    }

    public void occupySpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.parkingType = vehicle.getVehicleType();
        this.isOccupied = true;
    }
}
