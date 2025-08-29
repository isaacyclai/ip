package tom;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Prints messages displayed to the user in the shell.
 */
public class Ui {
    /**
     * Prints initial greeting.
     */
    public void greet() {
        System.out.println("Hello! I'm Tom.\nWhat can I do for you?");
    }

    /**
     * Prints final goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message to be displayed when a new task is added to the TaskList.
     * @param task Task to be added.
     * @param ls Current TaskList.
     * @throws IOException If user input is invalid.
     */
    public void add(Task task, TaskList ls) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + ls.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints the current list of tasks.
     * @param ls Current TaskList.
     * @throws IOException If user input is invalid.
     */
    public static void list(ArrayList<Task> ls) throws IOException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i+1) + "." + ls.get(i).toString());
        }
    }

    /**
     * Prints message to be displayed when a task is deleted from the TaskList.
     * @param task Task to be deleted.
     * @param ls Current TaskList.
     * @throws IOException If user input is invalid.
     */
    public static void delete(Task task, ArrayList<Task> ls) throws IOException {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    /**
     * Prints message to be displayed when a task is marked.
     * @param task Task to be marked.
     * @throws IOException If user input is invalid.
     */
    public static void mark(Task task) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + task.description);
    }

    /**
     * Prints message to be displayed when a task is unmarked.
     * @param task Task to be unmarked.
     * @throws IOException If user input is invalid.
     */
    public static void unmark(Task task) throws IOException {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + task.description);
    }
}
