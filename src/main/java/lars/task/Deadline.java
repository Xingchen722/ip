package lars.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * It supports parsing input dates in "y-M-d" format or date-times in "y-M-d HHmm" format.
 */
public class Deadline extends Task {
    private LocalDateTime by = null;
    private String bySingle;
    private boolean hasTime = false; // record if there is time

    /**
     * Constructs a Deadline task and attempts to parse the 'by' string.
     * If the string matches "y-M-d HHmm", it is stored as a LocalDateTime with time.
     * If it matches "y-M-d", it is stored as the start of that day.
     * Otherwise, it is stored as a raw string.
     * * @param status   The description of the task.
     * @param byString The deadline date/time string provided by the user.
     */
    public Deadline(String status, String byString) {
        super(status);
        this.bySingle = byString;

        DateTimeFormatter withTime = DateTimeFormatter.ofPattern("y-M-d HHmm");
        DateTimeFormatter onlyDate = DateTimeFormatter.ofPattern("y-M-d");

        try {
            this.by = LocalDateTime.parse(byString, withTime);
            this.hasTime = true;
        } catch (DateTimeParseException e1) {
            try {
                LocalDate d = LocalDate.parse(byString, onlyDate);
                this.by = d.atStartOfDay();
                this.hasTime = false;
            } catch (DateTimeParseException e2) {
                this.by = null;
            }
        }
    }

    /**
     * Returns a string representation of the Deadline task for display.
     * If the date was successfully parsed, it formats it as "MMM d yyyy" (plus time if provided).
     * @return A formatted string showing the task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        if (by != null) {
            String pattern = hasTime ? "MMM d yyyy HH:mm" : "MMM d yyyy";
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(pattern)) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + bySingle + ")";
        }
    }

    /**
     * Returns a string representation of the Deadline task formatted for file storage.
     * @return A string in the format "D | status | description | formatted_date".
     */
    @Override
    public String toStorageString() {
        if (by != null ) {
            String pattern = hasTime ? "MMM d yyyy HH:mm" : "MMM d yyyy";
            return "D | " + super.toStorageString() + " | " + by.format(DateTimeFormatter.ofPattern(pattern));
        } else {
            return "D | " + super.toStorageString() + " | " + bySingle;
        }
    }
}