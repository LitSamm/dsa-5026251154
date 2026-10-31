package lw04.prelab;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("branches.txt");
        if (input == null) {
            throw new IllegalStateException("branches.txt was not found");
        }

        int[] branches;
        int[] targets;
        try (Scanner scanner = new Scanner(input)) {
            int branchCount = scanner.nextInt();
            branches = new int[branchCount];
            for (int i = 0; i < branchCount; i++) {
                branches[i] = scanner.nextInt();
            }

            int targetCount = scanner.nextInt();    
            targets = new int[targetCount];
            for (int i = 0; i < targetCount; i++) {
                targets[i] = scanner.nextInt();
            }
        }

        insertionSort(branches, 3);
        printBranches(branches);

        for (int target : targets) {
            int index = linearSearch(branches, target);
            if (index >= 0) {
                System.out.println(target + ": FOUND at index " + index);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    private static void insertionSort(int[] branches, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }
    }

    private static int linearSearch(int[] branches, int target) {
        for (int i = 0; i < branches.length; i++) {
            if (branches[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static void printBranches(int[] branches) {
        for (int i = 0; i < branches.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }
        System.out.println();
    }
}
