package org.example.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;
import org.example.vehicle.Vehicle;

@Data
public class ParkingTicket {
    private final String ticketId;
    private final Date issuedDate;

    private Vehicle vehicle;

    private Date paidAt;
    private double fee;

    public ParkingTicket(Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.issuedDate = new Date();
        this.vehicle = vehicle;
    }

    public void makePayment(Double fee) {
        this.paidAt = new Date();
        this.fee = fee;
    }
}
