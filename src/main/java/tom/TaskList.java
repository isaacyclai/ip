package tom;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be done by the user.
 */
public class TaskList {
    private ArrayList<Task> ls;

    public TaskList(ArrayList<Task> tasks) {
        this.ls = tasks;
    }

    public ArrayList<Task> getTasks() {
        return ls;
    }

    public void list() throws IOException {
        Ui.list(ls);
        Storage.writeLines(ls);
    }

    public void add(Task task) throws IOException {
        ls.add(task);
        Storage.writeLines(ls);
    }

    public void delete(int n) throws IOException {
        Task removed = ls.get(n-1);
        ls.remove(removed);
        Storage.writeLines(ls);
        Ui.delete(removed, ls);
    }

    public void mark(int n) throws IOException {
        Task cur = ls.get(n-1);
        cur.Mark();
        Storage.writeLines(ls);
        Ui.mark(cur);
    }

    public void unmark(int n) throws IOException {
        Task cur = ls.get(n-1);
        cur.Unmark();
        Storage.writeLines(ls);
        Ui.unmark(cur);
    }
}
