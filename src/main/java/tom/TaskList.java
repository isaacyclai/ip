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

    public String list() throws IOException {
        Storage.writeLines(taskList);
        return Ui.list(taskList);
    }

    public String add(Task task) {
        taskList.add(task);
        return Ui.add(task, this);
    }

    public String delete(int n) {
        assert n > 0 : "Index should be positive!";
        Task removed = taskList.get(n - 1);
        taskList.remove(removed);
        return Ui.delete(removed, taskList);
    }

    public String mark(int n) {
        assert n > 0 : "Index should be positive!";
        Task cur = taskList.get(n - 1);
        cur.mark();
        return Ui.mark(cur);
    }

    public String unmark(int n) {
        assert n > 0 : "Index should be positive!";
        Task cur = taskList.get(n - 1);
        cur.unmark();
        return Ui.unmark(cur);
    }

    public String prioritise(int n) {
        assert n > 0 : "Index should be positive!";
        Task cur = taskList.get(n - 1);
        cur.prioritise();
        return Ui.prioritise(cur);
    }
    public String find(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.contains(keyword)) {
                found.add(t);
            }
        }
        return Ui.find(found);
    }
}
