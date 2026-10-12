
package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.BookingConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;

public class CarRentalBooking extends Booking {

    private double dailyRate;
    private Integer rentalDays;

    public CarRentalBooking(String bookingId, String customerName,
                            String travelDate, String destinationCity,
                            double dailyRate) {
        super(bookingId, customerName, travelDate, destinationCity);

        if (dailyRate < 0) {
            throw new IllegalArgumentException(
                    "Daily rental rate cannot be negative.");
        }

        this.dailyRate = dailyRate;
        this.rentalDays = null;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double computeTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (rentalDays == null) {
            throw new MissingInformationException(
                    "Number of rental days has not been provided.");
        }

        if (rentalDays < BookingConfig.MIN_RENTAL_DAYS
                || rentalDays > BookingConfig.MAX_RENTAL_DAYS) {
            throw new InvalidArgumentException(
                    "Rental days must be between 1 and 30.");
        }

        return dailyRate * rentalDays;
    }
}
