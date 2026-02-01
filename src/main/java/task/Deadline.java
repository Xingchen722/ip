package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String status, String by) {
        super(status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + by;
    }
}
