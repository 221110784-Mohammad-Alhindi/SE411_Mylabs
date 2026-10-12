
package edu.psu.se411.lab07_polymorphism;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07_polymorphism.model.Booking;
import edu.psu.se411.lab07_polymorphism.model.FlightBooking;
import edu.psu.se411.lab07_polymorphism.model.TrainBooking;
import edu.psu.se411.lab07_polymorphism.model.CarRentalBooking;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;

public class App {

    private static final Logger logger =
            LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Travel booking system is starting...");

        try {
            Booking flight = new FlightBooking(
                    "F001", "Ahmed Ali", "2026-11-10", "London", 500.0);
            ((FlightBooking) flight).setLuggageWeight(20.0);

            Booking train = new TrainBooking(
                    "T001", "Sara Ahmed", "2026-11-12", "Dammam",
                    TrainBooking.SeatClass.FIRST_CLASS);
            ((TrainBooking) train).setDistance(400);

            Booking car = new CarRentalBooking(
                    "C001", "Omar Khalid", "2026-11-15", "Riyadh", 150.0);
            ((CarRentalBooking) car).setRentalDays(3);

            List<Booking> bookings = Arrays.asList(flight, train, car);

            for (Booking booking : bookings) {
                double price = booking.computeTotalPrice();

                System.out.println(
                        "Booking ID: " + booking.getBookingId()
                        + " | Customer: " + booking.getCustomerName()
                        + " | Destination: " + booking.getDestinationCity()
                        + " | Total Price: " + price);
            }

            // Test a missing-information exception
            Booking incompleteFlight = new FlightBooking(
                    "F002", "Mona Saleh", "2026-11-20", "Paris", 400.0);

FlightBooking invalidFlight = new FlightBooking(
        "F003", "Ali Hassan", "2026-11-22", "Rome", 450.0);

invalidFlight.setLuggageWeight(45.0);

try {
    invalidFlight.computeTotalPrice();
} catch (MissingInformationException
        | InvalidArgumentException e) {
    logger.error("Invalid flight booking information.", e);
    System.out.println("Expected exception: " + e.getMessage());
}

TrainBooking invalidTrain = new TrainBooking(
        "T002", "Khalid Ahmed", "2026-11-25", "Jeddah",
        TrainBooking.SeatClass.STANDARD);

invalidTrain.setDistance(2500);

try {
    invalidTrain.computeTotalPrice();
} catch (MissingInformationException
        | InvalidArgumentException e) {
    logger.error("Invalid train booking information.", e);
    System.out.println("Expected exception: " + e.getMessage());
}

CarRentalBooking invalidCar = new CarRentalBooking(
        "C002", "Fahad Ali", "2026-11-28", "Dubai", 200.0);

invalidCar.setRentalDays(35);

try {
    invalidCar.computeTotalPrice();
} catch (MissingInformationException
        | InvalidArgumentException e) {
    logger.error("Invalid car rental booking information.", e);
    System.out.println("Expected exception: " + e.getMessage());
}

            try {
                incompleteFlight.computeTotalPrice();
            } catch (MissingInformationException
                    | InvalidArgumentException e) {
                logger.error("Unable to calculate booking price.", e);
                System.out.println("Expected exception: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.error("An unexpected error occurred.", e);
        } finally {
            logger.info("Travel booking system is stopping.");
        }
    }
}
