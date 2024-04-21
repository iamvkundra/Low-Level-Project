package org.example.model;

import java.util.Date;

public class ParkingTicket {
    private final String ticketId;
    private final Date issuedDate;
    private Date paidAt;
    private double fee;

    public ParkingTicket(String ticketId) {
        this.ticketId = ticketId;
        this.issuedDate = new Date();
    }

    public void makePayment(Double fee) {
        this.paidAt = new Date();
        this.fee = fee;
    }
}
