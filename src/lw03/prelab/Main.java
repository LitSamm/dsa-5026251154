package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        processPlaylist();
        processParticipants();
        processInventory();
    }

    private static void processPlaylist() {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("playlist.txt")
        );
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+", 2);
            String operation = parts[0];

            switch (operation) {
                case "ADD":
                    playlist.add(parts[1]);
                    break;
                case "INSERT":
                    String[] insertion = parts[1].split("\\s+", 2);
                    int index = Integer.parseInt(insertion[0]);
                    playlist.add(index, insertion[1]);
                    break;
                case "REMOVE":
                    playlist.remove(parts[1]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown playlist operation: " + operation);
            }
        }
        scanner.close();

        System.out.println("====== Problem 1 ======");
        System.out.println("Total songs: " + playlist.size());
        for (int index = 0; index < playlist.size(); index++) {
            System.out.println((index + 1) + ": " + playlist.get(index));
        }
        System.out.println();
    }

    private static void processParticipants() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("participants.txt")
        );
        while (scanner.hasNext()) {
            if (!participants.add(scanner.next())) {
                duplicateRegistrations++;
            }
        }
        scanner.close();

        System.out.println("====== Problem 2 ======");
        System.out.println("Unique participants: " + participants.size());
        int index = 1;
        for (String participant : participants) {
            System.out.println(index + ". " + participant);
            index++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
        System.out.println();
    }

    private static void processInventory() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("inventory.txt")
        );
        while (scanner.hasNext()) {
            String operation = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (operation.equals("ADD")) {
                int stock = inventory.containsKey(product) ? inventory.get(product) : 0;
                inventory.put(product, stock + quantity);
            } else if (operation.equals("SELL")) {
                if (!inventory.containsKey(product) || inventory.get(product) < quantity) {
                    failedSales++;
                } else {
                    inventory.put(product, inventory.get(product) - quantity);
                }
            } else {
                throw new IllegalArgumentException("Unknown inventory operation: " + operation);
            }
        }
        scanner.close();

        System.out.println("====== Problem 3 ======");
        for (Map.Entry<String, Integer> product : inventory.entrySet()) {
            System.out.println(product.getKey() + ": " + product.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

}
