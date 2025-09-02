package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be completed by a certain date.
 */
public class Deadline extends Task {
    protected LocalDateTime endDateAndTime;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        super.id = "D";
        this.endDateAndTime = by;
    }

    @Override
    public String saveDesc() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return super.saveDesc() + " | " + endDateAndTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }
}
