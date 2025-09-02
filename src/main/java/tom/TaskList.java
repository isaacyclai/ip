package tom;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be done by the user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public void list() throws IOException {
        Ui.list(taskList);
        Storage.writeLines(taskList);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int n) {
        Task removed = taskList.get(n - 1);
        taskList.remove(removed);
        Ui.delete(removed, taskList);
    }

    public void mark(int n) {
        Task cur = taskList.get(n - 1);
        cur.mark();
        Ui.mark(cur);
    }

    public void unmark(int n) {
        Task cur = taskList.get(n - 1);
        cur.unmark();
        Ui.unmark(cur);
    }

    public void find(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.contains(keyword)) {
                found.add(t);
            }
        }
        Ui.find(found);
    }
}
