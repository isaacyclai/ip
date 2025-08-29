package tom;

import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    public void greet() {
        System.out.println("Hello! I'm tom.Tom.\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void add(Task task, TaskList ls) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + ls.getTasks().size() + " tasks in the list.");
    }

    public static void list(ArrayList<Task> ls) throws IOException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i+1) + "." + ls.get(i).toString());
        }
    }

    public static void delete(Task task, ArrayList<Task> ls) throws IOException {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    public static void mark(Task task) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + task.description);
    }

    public static void unmark(Task task) throws IOException {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + task.description);
    }
}
