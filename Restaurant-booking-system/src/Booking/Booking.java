package Booking;

import java.util.Date;

public class Booking {
    public String bookingId;
    public String userId;
    public String restrauntId;
    Date bookedAt;

    BookingStatus bookingStatus;
    int fromTime;
    int endTime;

}

enum  BookingStatus{
    RESERVED,
    CONFIRMED,
    CANCELLED
}

enum Weekday {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THRUSDAY,
    FRIDAY,
    SATURDAY

}

