package tom;

/**
 * Represents a task with a description and a flag denoting if it has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String id;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = "T";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void Mark() {
        this.isDone = true;
    }
    public void Unmark() {
        this.isDone = false;
    }
    public String getId() {return this.id;}
    /**
     * Returns a description of the Task in the correct format to be stored in the text file.
     * @return Formatted string.
     */
    public String saveDesc() {
        return id + " | " + (isDone ? "1 | " : "0 | ") + description;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}