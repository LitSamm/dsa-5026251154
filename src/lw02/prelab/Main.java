package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        File file = new File("src/lw02/prelab/transactions.txt");
        if (!file.exists()) {
            file = new File("lw02/prelab/transactions.txt");
        }
        if (!file.exists()) {
            file = new File("transactions.txt");
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                String type = scanner.next();
                String amount = scanner.next();
                String[] transaction = {name, type, amount};

                transactions.add(transaction);

                if (findCustomer(customers, name) == null) {
                    customers.add(new String[] {name, "0"});
                }
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);
        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String[] customer = findCustomer(customers, transaction[0]);
            int amount = Integer.parseInt(transaction[2]);
            int balance = Integer.parseInt(customer[1]);

            if (transaction[1].equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (transaction[1].equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(transaction);
                } else {
                    customer[1] = String.valueOf(balance - amount);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] transaction = failedTransactions.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                return customer;
            }
        }
        return null;
    }
}