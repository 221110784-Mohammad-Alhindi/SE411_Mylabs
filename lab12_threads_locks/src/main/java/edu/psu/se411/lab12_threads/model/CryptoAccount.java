
package edu.psu.se411.lab12_threads.model;

import java.util.concurrent.locks.ReentrantLock;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {

    private String id;
    private Double balance;

    private final ReentrantLock lock = new ReentrantLock();

    public CryptoAccount(String id, Double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Account balance cannot be negative");
        }

        this.id = id;
        this.balance = balance;
    }

    public void addToBalance(Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount added to balance cannot be negative");
        }

        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void retrieveFromBalance(Double amount)
            throws InsufficientBalanceException {

        lock.lock();
        try {
            if (amount > balance) {
                throw new InsufficientBalanceException(id);
            }

            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public static void transfer(
            CryptoAccount sender,
            CryptoAccount receiver,
            Double amount) throws InsufficientBalanceException {

        if (sender == receiver) {
            return;
        }

        CryptoAccount first;
        CryptoAccount second;

        // Resource ordering: always lock accounts by ID.
        if (sender.id.compareTo(receiver.id) < 0) {
            first = sender;
            second = receiver;
        } else {
            first = receiver;
            second = sender;
        }

        first.lock.lock();
        try {
            second.lock.lock();
            try {
                if (amount < 0) {
                    throw new IllegalArgumentException(
                            "Transfer amount cannot be negative");
                }

                if (sender.balance < amount) {
                    throw new InsufficientBalanceException(sender.id);
                }

                sender.balance -= amount;
                receiver.balance += amount;

            } finally {
                second.lock.unlock();
            }
        } finally {
            first.lock.unlock();
        }
    }
}
