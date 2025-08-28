public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        super.id = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveDesc() {
        return super.saveDesc() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
