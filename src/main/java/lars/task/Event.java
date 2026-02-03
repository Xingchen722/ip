package lars.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String status, LocalDate from, LocalDate to) {
        super(status);
        this.from = from;
        this.to = to;
    }

    //[E][ ] project meeting (from: Mon 2pm to: 4pm)
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + this.from + " | " + this.to;
    }
}