
package edu.psu.se411.lab11_threads;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        try {
            List<CryptoAccount> accounts = new ArrayList<>();

            // Create 10 accounts with 1,000 SAR each.
            for (int i = 1; i <= 10; i++) {
                accounts.add(new CryptoAccount("A" + i, 1000.0));
            }

            double initialTotal = calculateTotal(accounts);
            System.out.printf("Initial total balance: %.2f SAR%n",
                    initialTotal);

            List<Thread> threads = new ArrayList<>();

            // Create 10,000 threads to execute random transactions.
            for (int i = 0; i < 10000; i++) {
                CryptoAccount sender =
                        accounts.get((int) (Math.random() * accounts.size()));

                CryptoAccount receiver =
                        accounts.get((int) (Math.random() * accounts.size()));

                while (receiver == sender) {
                    receiver =
                            accounts.get((int) (Math.random() * accounts.size()));
                }

                double amount = Math.random() * 10 + 1;

                Transaction transaction = new Transaction(
                        "T" + (i + 1), sender, receiver, amount);

                Thread thread = new Thread(transaction::execute);
                threads.add(thread);
                thread.start();
            }

            // Wait until all transactions finish.
            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("\n=== Final Account Balances ===");
            for (CryptoAccount account : accounts) {
                System.out.printf("%s: %.2f SAR%n",
                        account.getId(), account.getBalance());
            }

            double finalTotal = calculateTotal(accounts);

            System.out.printf("%nFinal total balance: %.2f SAR%n",
                    finalTotal);

            if (Math.abs(initialTotal - finalTotal) < 0.01) {
                System.out.println("Total balance is consistent.");
            } else {
                System.out.println("Total balance is inconsistent!");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Transaction processing was interrupted.", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred.", e);
        } finally {
            logger.info("Application is closing...");
        }
    }

    private static double calculateTotal(List<CryptoAccount> accounts) {
        double total = 0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }
}
