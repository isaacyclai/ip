import java.util.Scanner;
import java.util.ArrayList;
public class Tom {
    public static void main(String[] args) throws TomException {
        System.out.println("Hello! I'm Tom.\nWhat can I do for you?");
        ArrayList<Task> ls = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] words = input.split("\\s+", 2);
            String command = words[0];
            switch(command) {
                case "bye":
                    if(words.length != 1) {
                        throw new TomException("Bye takes no description");
                    }
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    if(words.length != 1) {
                        throw new TomException("list takes no description");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < ls.size(); i++) {
                        System.out.println((i+1) + "." + ls.get(i).toString());
                    }
                    break;
                case "mark":
                    if (words.length != 2) {
                        throw new TomException("1 task required to mark");
                    }
                    if (!Character.isDigit(words[1].charAt(0))) {
                        throw new TomException("Task must be a positive integer");
                    }
                    Task cur = ls.get(Integer.valueOf(words[1]));
                    cur.Mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + cur.description);
                    break;
                case "unmark":
                    if (words.length != 2) {
                        throw new TomException("1 task required to unmark");
                    }
                    if (!Character.isDigit(words[1].charAt(0))) {
                        throw new TomException("Task must be a positive integer");
                    }
                    Task cur1 = ls.get(Integer.valueOf(words[1]));
                    cur1.Unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + cur1.description);
                    break;
                case "todo":
                    if (words.length != 2) {
                        throw new TomException("Todo requires a description");
                    }
                    Todo todo = new Todo(words[1]);
                    ls.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                case "deadline":
                    if (words.length != 2) {
                        throw new TomException("Deadline requires a date by which the task must be completed");
                    }
                    String[] parts = words[1].split("/by");
                    if (parts.length != 2) {
                        throw new TomException("Deadline requires a date by which the task must be completed");
                    }
                    Deadline dl = new Deadline(parts[0], parts[1]);
                    ls.add(dl);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + dl.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                case "event":
                    if (words.length != 2) {
                        throw new TomException("Event requires a description, start and end dates");
                    }
                    String[] parts1 = words[1].split("/from|/to");
                    if (parts1.length != 3) {
                        throw new TomException("Event requires a description, start and end dates");
                    }
                    Event e = new Event(parts1[0], parts1[1], parts1[2]);
                    ls.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                case "delete":
                    if (words.length != 2) {
                        throw new TomException("1 task required to delete");
                    }
                    if (!Character.isDigit(words[1].charAt(0))) {
                        throw new TomException("Task must be a positive integer");
                    }
                    Task removed = ls.get(Integer.valueOf(words[1]));
                    ls.remove(removed);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                default:
                    throw new TomException("Command not found!");
            }
            if(command.equals("bye")) {
                break;
            }
        }

    }
}
