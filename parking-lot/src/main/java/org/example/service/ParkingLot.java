package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.Utils.Mapper;
import org.example.model.BikeSpot;
import org.example.model.CarSpot;
import org.example.model.ParkingFloor;
import org.example.model.ParkingSpot;
import org.example.model.TruckSpot;
import org.example.model.VehicleType;
import org.example.vehicle.Vehicle;

public class ParkingLot {
    private static volatile ParkingLot parkingLot;

    private final List<ParkingFloor> parkingFloors;

    public ParkingLot() {
        this.parkingFloors = new ArrayList<>();
    }
    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    public ParkingFloor createParkingFloor(int totalSlots) {
        ParkingFloor floor = new ParkingFloor(parkingFloors.size() + 1, totalSlots);
        parkingFloors.add(floor);
        System.out.println("Created parking floor: " + Mapper.toJson(floor, false));
        return floor;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (parkingFloors.isEmpty()) {
            System.out.println("These's no floor created or available");
            return;
        }

        for (ParkingFloor floor : parkingFloors) {
            ParkingSpot parkingSpot = null;
            if (floor.isSpotAvailable()) {
                if (vehicle.getVehicleType() == VehicleType.CAR) {
                    parkingSpot = new CarSpot(floor.getFloorId(), vehicle);
                } else if (vehicle.getVehicleType() == VehicleType.BIKE) {
                    parkingSpot = new BikeSpot(floor.getFloorId(), vehicle);
                } else if (vehicle.getVehicleType() == VehicleType.TRUCK) {
                    parkingSpot = new TruckSpot(floor.getFloorId(), vehicle);
                }
                floor.parkVehicleToFloor(parkingSpot);
                System.out.println("Parking allotted: Vehicle: " + Mapper.toJson(vehicle, false)
                        + " Spot Details: " + Mapper.toJson(parkingSpot, false));
                return;
            }
        }

        System.out.println("No spot available in any floor: vehicle details: " + Mapper.toJson(vehicle, false));
        return;
    }

    public void unParkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.checkIsParked(vehicle)) {
                floor.removeVehicleFromFloor(vehicle);
                System.out.println("UN-PARKED VEHICLE: " + Mapper.toJson(vehicle, false));
                return;
            }
        }
        System.out.println("Vehicle is not avaiable in the parking: " + Mapper.toJson(vehicle, false));
    }
}
