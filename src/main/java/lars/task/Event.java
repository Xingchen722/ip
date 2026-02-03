package lars.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs between two dates.
 * This class requires the 'from' and 'to' inputs to be valid ISO date strings (yyyy-MM-dd).
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event task with specific start and end dates.
     * @param status The description of the event.
     * @param from   The starting date of the event.
     * @param to     The ending date of the event.
     */
    public Event(String status, LocalDate from, LocalDate to) {
        super(status);
        this.from = from;
        this.to = to;
    }

    //[E][ ] project meeting (from: Mon 2pm to: 4pm)
    /**
     * Returns a string representation of the Event task for display.
     * The dates are formatted using the "MMM d yyyy" pattern.
     * @return A formatted string showing the task type, status, description, and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for file storage.
     * @return A string in the format "E | status | description | start_date | end_date".
     */
    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + this.from + " | " + this.to;
    }
}