/*
 * Project setup note:
 * Summary report for AdminImportService NASA RSS import runs.
 */

package hr.algebra.service;

public final class AdminImportResult {

    private int parsedCount;
    private int insertedCount;
    private int skippedDuplicateCount;
    private int linkedTagCount;
    private int failedImageCount;

    public int getParsedCount() {
        return parsedCount;
    }

    public void setParsedCount(int parsedCount) {
        this.parsedCount = parsedCount;
    }

    public int getInsertedCount() {
        return insertedCount;
    }

    public int getSkippedDuplicateCount() {
        return skippedDuplicateCount;
    }

    public int getLinkedTagCount() {
        return linkedTagCount;
    }

    public int getFailedImageCount() {
        return failedImageCount;
    }

    void incrementInserted() {
        insertedCount++;
    }

    void incrementSkippedDuplicate() {
        skippedDuplicateCount++;
    }

    void incrementLinkedTag() {
        linkedTagCount++;
    }

    void incrementFailedImage() {
        failedImageCount++;
    }

    @Override
    public String toString() {
        return "AdminImportResult{"
                + "parsedCount=" + parsedCount
                + ", insertedCount=" + insertedCount
                + ", skippedDuplicateCount=" + skippedDuplicateCount
                + ", linkedTagCount=" + linkedTagCount
                + ", failedImageCount=" + failedImageCount
                + '}';
    }
}
