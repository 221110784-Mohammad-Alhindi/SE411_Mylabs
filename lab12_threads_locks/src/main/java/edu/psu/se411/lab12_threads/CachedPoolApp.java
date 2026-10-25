
package edu.psu.se411.lab12_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.CryptoAccount;
import edu.psu.se411.lab12_threads.model.Transaction;

public class CachedPoolApp {

    static Logger logger = LoggerFactory.getLogger(CachedPoolApp.class);

    public static Integer nbAccounts = 10;
    public static Double initialBalance = 10000.0;
    public static Integer nbTransactions = 1000000;
    
    public static void main(String[] args) {
        logger.info("Cached pool application is starting...");

        CryptoAccount[] accounts = new CryptoAccount[nbAccounts];

        for (int i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount(
                "acc_" + i, initialBalance);
        }

        System.out.printf("Initial money volume in system: %.2f%n%n",
                sumTotalMoneyInAccounts(accounts));

        ExecutorService executor = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < nbTransactions; i++) {
            int senderIndex = (int) (Math.random() * nbAccounts);
            int receiverIndex = (int) (Math.random() * nbAccounts);

            if (senderIndex == receiverIndex) {
                receiverIndex = (receiverIndex + 1) % nbAccounts;
            }

            CryptoAccount sender = accounts[senderIndex];
            CryptoAccount receiver = accounts[receiverIndex];

            Double amount = Math.random() * 100;
            Transaction transaction = new Transaction(
                "id_" + i, sender, receiver, amount);

            executor.execute(transaction);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                executor.shutdownNow();
                logger.error("Execution interrupted.", e);
                return;
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.printf("Final money volume in system: %.2f%n%n",
                sumTotalMoneyInAccounts(accounts));

        System.out.println("Total time using cached thread pool: "
                + (endTime - startTime) + " milliseconds");

        logger.info("Cached pool application is closing...");
    }

    private static Double sumTotalMoneyInAccounts(
            CryptoAccount[] accounts) {
        Double total = 0.0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }
}
