
package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.BookingConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;

public class FlightBooking extends Booking {

    private double basePrice;
    private Double luggageWeight;

    public FlightBooking(String bookingId, String customerName,
                         String travelDate, String destinationCity,
                         double basePrice) {
        super(bookingId, customerName, travelDate, destinationCity);

        if (basePrice < 0) {
            throw new IllegalArgumentException(
                    "Base ticket price cannot be negative.");
        }

        this.basePrice = basePrice;
        this.luggageWeight = null;
    }

    public void setLuggageWeight(double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double computeTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (luggageWeight == null) {
            throw new MissingInformationException(
                    "Luggage weight has not been provided.");
        }

        if (luggageWeight < 0
                || luggageWeight > BookingConfig.MAX_LUGGAGE_WEIGHT) {
            throw new InvalidArgumentException(
                    "Luggage weight must be between 0 and 40 kg.");
        }

        return basePrice
                + (luggageWeight * BookingConfig.EXTRA_LUGGAGE_RATE);
    }
}
