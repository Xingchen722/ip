package task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String status, String from, String to) {
        super(status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + from + " | " + to;
    }
}
