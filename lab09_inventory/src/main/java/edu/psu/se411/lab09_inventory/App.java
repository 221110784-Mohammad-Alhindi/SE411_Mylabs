
package edu.psu.se411.lab09_inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        try {
            Inventory<Item> inventory = new Inventory<>();

            // Add books
            inventory.addItem(new Book(
                    "B001", "Concurrency in Java", "Brian Goetz"));
            inventory.addItem(new Book(
                    "B002", "Effective Java", "Joshua Bloch"));
            inventory.addItem(new Book(
                    "B003", "Clean Code", "Robert Martin"));

            // Add electronic devices
            inventory.addItem(new ElectronicDevice(
                    "E001", "Laptop", "Computer"));
            inventory.addItem(new ElectronicDevice(
                    "E002", "Smartphone", "Mobile"));
            inventory.addItem(new ElectronicDevice(
                    "E003", "Tablet", "Mobile"));

            System.out.println("=== Complete Inventory ===");
            displayInventory(inventory);

            // Search by name
            System.out.println("\n=== Search by Name ===");
            List<Item> nameResults =
                    inventory.findItems(
                            new SearchByName("Concurrency in Java"));
            displayItems(nameResults);

            // Search by ID
            System.out.println("\n=== Search by ID ===");
            List<Item> idResults =
                    inventory.findItems(new SearchById("E001"));
            displayItems(idResults);

            // Search books by author
            System.out.println("\n=== Search by Author ===");
            List<Item> authorResults =
                    inventory.findItems(new SearchByAuthor("Joshua Bloch"));
            displayItems(authorResults);

            // Search electronic devices by category
            System.out.println("\n=== Search by Category ===");
            List<Item> categoryResults =
                    inventory.findItems(new SearchByCategory("Mobile"));
            displayItems(categoryResults);

            // Search using a lambda expression
            System.out.println("\n=== Search Using Lambda ===");
            List<Item> lambdaResults =
                    inventory.findItems(
                            item -> item.getName().startsWith("L"));
            displayItems(lambdaResults);

            // Demonstrate removing an item
            System.out.println("\n=== Remove Item ===");
            inventory.removeItem(inventory.getAllItems().get(0));
            displayInventory(inventory);

        } catch (Exception e) {
            logger.error("An unexpected error occurred.", e);
        } finally {
            logger.info("Application is closing...");
        }
    }

    public static void displayInventory(Inventory<?> inventory) {
        displayItems(inventory.getAllItems());
    }

    public static void displayItems(List<?> items) {
        for (Object item : items) {
            System.out.println(item);
        }
    }
}
