package lw03.unguided;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();
        int rejectedAttempts = 0;

        Scanner registrationScanner = new Scanner(
                Main.class.getResourceAsStream("registrations.txt")
        );
        while (registrationScanner.hasNextLine()) {
            registeredStudents.add(registrationScanner.nextLine());
        }
        registrationScanner.close();

        System.out.println("===== Event Check-In Results =====");
        Scanner checkInScanner = new Scanner(
                Main.class.getResourceAsStream("checkins.txt")
        );
        while (checkInScanner.hasNextLine()) {
            String studentId = checkInScanner.nextLine();

            if (!registeredStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedInStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedInStudents.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }
        checkInScanner.close();

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + (registeredStudents.size() - checkedInStudents.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }

}
