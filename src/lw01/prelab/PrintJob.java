package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private final String jobId;
    private final int pages;

    public PrintJob(String jobId, int pages) {
        if (jobId == null || jobId.isEmpty()) {
            throw new IllegalArgumentException("Job ID must not be empty");
        }
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive");
        }
        this.jobId = jobId;
        this.pages = pages;
    }

    public String getId() {
        return jobId;
    }

    public int getPages() {
        return pages;
    }

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be positive");
        }
        return copies * calculateCharge();
    }

    public abstract String label();

    public String summary() {
        return getId() + " | " + label() + " | " + calculateCharge();
    }
}