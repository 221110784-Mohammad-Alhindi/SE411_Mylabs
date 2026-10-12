
package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;

public abstract class Booking {

    private String bookingId;
    private String customerName;
    private String travelDate;
    private String destinationCity;

    public Booking(String bookingId, String customerName,
                   String travelDate, String destinationCity) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public abstract double computeTotalPrice()
            throws MissingInformationException, InvalidArgumentException;
}
