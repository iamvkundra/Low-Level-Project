package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.vehicle.Vehicle;

@Data
@AllArgsConstructor
public class ParkingFloor {

    private final int floorId;

    private Map<VehicleType, List<ParkingSpot>> parkingSpots;

    private final int totalSpot;

    private int parkedVehicle;

    public ParkingFloor(int floorId, int numberOfSpots) {
        this.floorId = floorId;
        this.parkingSpots = new HashMap<>();
        this.parkingSpots.put(VehicleType.CAR, new ArrayList<ParkingSpot>());
        this.parkingSpots.put(VehicleType.BIKE, new ArrayList<ParkingSpot>());
        this.parkingSpots.put(VehicleType.TRUCK, new ArrayList<ParkingSpot>());
        this.totalSpot = numberOfSpots;
        this.parkedVehicle = 0;
    }

    public void parkVehicleToFloor(ParkingSpot spot) {
        if (isSpotAvailable()) {
            this.parkingSpots.get(spot.getParkingType()).add(spot);
            parkedVehicle++;
        }
    }

    public void removeVehicleFromFloor(Vehicle vehicle) {
        this.parkingSpots.get(vehicle.getVehicleType()).remove(vehicle);
        parkedVehicle--;
    }

    public boolean isSpotAvailable() {
        return parkedVehicle < totalSpot;
    }

    public boolean checkIsParked(Vehicle vehicle) {
        for (ParkingSpot spot : this.parkingSpots.getOrDefault(vehicle.getVehicleType(), new ArrayList<>())) {
            if (spot.getVehicle().getLicenseNumber().equals(vehicle.getLicenseNumber())) {
                return true;
            }
        }
        return false;
    }

}
