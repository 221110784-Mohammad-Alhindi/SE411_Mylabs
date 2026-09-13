package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;
import edu.psu.se411.exceptions.InsufficientFundsException;

public class App {

    public static void validateAge(int age) throws InvalidAgeException {

        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }

        System.out.println("Age valid message.");
    }

    public static void main(String[] args) {

        // Exercise 1
        int age = 20;

        try {
            validateAge(age);

        } catch (InvalidAgeException e) {
            System.out.println("Invalid age: " + e.getMessage());
        }

        // Exercise 2
        Wallet wallet = new Wallet(500.0);

        System.out.println("Initial wallet balance: " + wallet.getBalance());

        try {
            double withdrawn = wallet.withdraw(200.0);

            System.out.println("Withdrawal successful.");
            System.out.println("Withdrawn amount: " + withdrawn);
            System.out.println("Remaining balance: " + wallet.getBalance());

            wallet.withdraw(400.0);

        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }
}