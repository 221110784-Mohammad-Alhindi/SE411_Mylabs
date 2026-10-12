
package edu.psu.se411.lab07_polymorphism;

public final class BookingConfig {

    private BookingConfig() {
    }

    public static final double EXTRA_LUGGAGE_RATE = 15.0;

    public static final double TRAIN_STANDARD_RATE = 0.20;
    public static final double TRAIN_FIRST_CLASS_RATE = 0.35;

    public static final double MAX_LUGGAGE_WEIGHT = 40.0;

    public static final int MIN_TRAIN_DISTANCE = 1;
    public static final int MAX_TRAIN_DISTANCE = 2000;

    public static final int MIN_RENTAL_DAYS = 1;
    public static final int MAX_RENTAL_DAYS = 30;
}
