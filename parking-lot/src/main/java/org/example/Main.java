package org.example;


import org.example.Utils.Mapper;
import org.example.model.ParkingFloor;
import org.example.model.ParkingSpot;

import org.example.model.ParkingTicket;
import org.example.service.ParkingLot;
import org.example.vehicle.Bike;
import org.example.vehicle.Car;
import org.example.vehicle.Vehicle;

public class Main {

    public static void main(String[] args) {
        //Entry point.
        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.createParkingFloor(1);

        //get a vehicle
        Vehicle carVehicle = new Car("JH-01-MK-0989");
        Vehicle bikeVehicle = new Bike("JH-01-MK-0909");
        ParkingTicket ticket1  = parkingLot.parkVehicle(carVehicle);
        ParkingTicket ticket2  = parkingLot.parkVehicle(bikeVehicle);

        parkingLot.unParkVehicle(ticket1);
        parkingLot.unParkVehicle(ticket2);

    }
}