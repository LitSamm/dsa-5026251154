package lw01.prelab;

public class ColourPrint extends PrintJob {
    public ColourPrint(String jobId, int pages) {
        super(jobId, pages);
    }

    @Override
    public int calculateCharge() {
        int firstTenPages = Math.min(getPages(), 10);
        int remainingPages = Math.max(getPages() - 10, 0);
        return 2_000 + firstTenPages * 1_500 + remainingPages * 1_000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}