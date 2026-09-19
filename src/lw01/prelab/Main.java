    package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String jobId = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new MonoPrint(jobId, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new ColourPrint(jobId, pages));
                }
            }
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
