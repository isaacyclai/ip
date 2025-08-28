public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.id = "D";
        this.by = by;
    }

    @Override
    public String saveDesc() {
        return super.saveDesc() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}