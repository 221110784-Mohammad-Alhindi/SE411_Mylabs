
package edu.spu.se411.lab08;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger =
            LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Application is starting...");

        try {
            Sensor temp = new Sensor("Temperature");
            Sensor humidity = new Sensor("Humidity");

            DashboardObserver dashboard = new DashboardObserver();
            LoggerObserver logObserver = new LoggerObserver();

            temp.register(dashboard);
            temp.register(logObserver);

            humidity.register(dashboard);
            humidity.register(logObserver);

            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                temp.setReading(20 + random.nextDouble() * 15);
                humidity.setReading(40 + random.nextDouble() * 20);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Sensor simulation was interrupted.", e);
                    break;
                }
            }

            // Test cloning: the clone has its own observer list.
            Sensor clonedTemp = temp.clone();

            System.out.println("\n--- Testing cloned sensor ---");
            System.out.println("Original sensor: " + temp.getSensorType());
            System.out.println("Cloned sensor: " + clonedTemp.getSensorType());

            clonedTemp.setReading(25.0);

            clonedTemp.register(dashboard);
            clonedTemp.setReading(26.0);

            temp.unregister(dashboard);
            System.out.println("\nDashboard unregistered from original temperature sensor.");
            temp.setReading(30.0);

        } catch (Exception e) {
            logger.error("An unexpected error occurred.", e);
        } finally {
            logger.info("Application is stopping.");
        }
    }
}
