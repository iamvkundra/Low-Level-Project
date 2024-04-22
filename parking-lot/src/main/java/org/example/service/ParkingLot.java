package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Utils.Mapper;
import org.example.model.BikeSpot;
import org.example.model.CarSpot;
import org.example.model.ParkingFloor;
import org.example.model.ParkingSpot;
import org.example.model.ParkingTicket;
import org.example.model.TruckSpot;
import org.example.model.VehicleType;
import org.example.vehicle.Vehicle;

public class ParkingLot {
    private static volatile ParkingLot parkingLot;

    private final List<ParkingFloor> parkingFloors;

    private final Map<Integer, Map<String, ParkingTicket>> tickets;

    public ParkingLot() {
        this.parkingFloors = new ArrayList<>();
        this.tickets = new HashMap<>();
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

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        if (parkingFloors.isEmpty()) {
            System.out.println("These's no floor created or available");
            return null;
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
                ParkingTicket ticket = new ParkingTicket(vehicle);
                if (!tickets.containsKey(floor.getFloorId())) {
                    tickets.put(floor.getFloorId(), new HashMap<>());
                }

                tickets.get(floor.getFloorId()).putIfAbsent(ticket.getTicketId(), ticket);

                floor.parkVehicleToFloor(parkingSpot);
                System.out.println("Parking allotted: Vehicle: " + Mapper.toJson(vehicle, false)
                        + " Spot Details: " + Mapper.toJson(parkingSpot, false) + " ticket: ");
                return ticket;
            }
        }

        System.out.println("No spot available in any floor: vehicle details: " + Mapper.toJson(vehicle, false));
        return null;
    }

    public void unParkVehicle(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            System.out.println("Invalid Parking ticket");
            return;
        }
        for (ParkingFloor floor : parkingFloors) {
            if (floor.checkIsParked(parkingTicket.getVehicle())) {
                floor.removeVehicleFromFloor(parkingTicket.getVehicle());
                System.out.println("UN-PARKED VEHICLE: " + Mapper.toJson(parkingTicket.getVehicle(), false));
                return;
            }
        }
        System.out.println("Vehicle is not avaiable in the parking: " + Mapper.toJson(parkingTicket.getVehicle(), false));
    }
}
