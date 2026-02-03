package lars.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by = null;
    private String bySingle;
    private boolean hasTime = false; // record if there is time

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

    @Override
    public String toString() {
        if (by != null) {
            String pattern = hasTime ? "MMM d yyyy HH:mm" : "MMM d yyyy";
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(pattern)) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + bySingle + ")";
        }
    }

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