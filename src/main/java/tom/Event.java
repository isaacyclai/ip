package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        super.id = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveDesc() {
        DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return super.saveDesc() + " | " + from.format(output_formatter) + " | " + to.format(output_formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(output_formatter) + " to: " + to.format(output_formatter) + ")";
    }
}
