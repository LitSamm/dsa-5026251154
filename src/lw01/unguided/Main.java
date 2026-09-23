package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File dataFile = new File("src/lw01/unguided/washes.txt");
        if (!dataFile.exists()) {
            dataFile = new File("washes.txt");
        }
        if (!dataFile.exists()) {
            dataFile = new File("lw01/unguided/washes.txt");
        }

        Scanner scanner = new Scanner(dataFile);

        int totalRecords = scanner.nextInt();
        WashService[] washes = new WashService[totalRecords];
        int[] orderUnits = new int[totalRecords];

        for (int i = 0; i < totalRecords; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            orderUnits[i] = scanner.nextInt(); // Menyimpan jumlah units

            if (type.equals("MOTORCYCLE")) {
                washes[i] = new MotorcycleWash(id, days);
            } else {
                washes[i] = new CarWash(id, days);
            }
        }

        scanner.close();

        // Mengalikan dengan units saat mencetak agar sesuai dengan Expected Output
        for (int i = 0; i < washes.length; i++) {
            System.out.println(washes[i].getId() + " | " + washes[i].label() + " | " + washes[i].calculateCharge(orderUnits[i]));
        }
    }
}