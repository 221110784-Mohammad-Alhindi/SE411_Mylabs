
package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.BookingConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;

public class TrainBooking extends Booking {

    public enum SeatClass {
        STANDARD,
        FIRST_CLASS
    }

    private SeatClass seatClass;
    private Integer distance;

    public TrainBooking(String bookingId, String customerName,
                        String travelDate, String destinationCity,
                        SeatClass seatClass) {
        super(bookingId, customerName, travelDate, destinationCity);

        if (seatClass == null) {
            throw new IllegalArgumentException(
                    "Seat class must be provided.");
        }

        this.seatClass = seatClass;
        this.distance = null;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public double computeTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (distance == null) {
            throw new MissingInformationException(
                    "Train distance has not been provided.");
        }

        if (distance < BookingConfig.MIN_TRAIN_DISTANCE
                || distance > BookingConfig.MAX_TRAIN_DISTANCE) {
            throw new InvalidArgumentException(
                    "Train distance must be between 1 and 2000 km.");
        }

        if (seatClass == SeatClass.STANDARD) {
            return distance * BookingConfig.TRAIN_STANDARD_RATE;
        }

        return distance * BookingConfig.TRAIN_FIRST_CLASS_RATE;
    }
}
