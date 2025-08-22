import java.util.Scanner;
import java.util.ArrayList;
public class Tom {
    public static void main(String[] args) {
//        String logo = " _____ ____  __  __ \n" +
//                "|_   _/ __ \\|  \\/  |\n" +
//                "  | || |  | | \\  / |\n" +
//                "  | || |__| | |\\/| |\n" +
//                "  |_| \\____/|_|  |_|";
//        System.out.println("Hello from\n" + logo);
//        String line = "____________________________________________________________";
        System.out.println("Hello! I'm Tom.\nWhat can I do for you?");
        ArrayList<Task> ls = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] words = input.split("\\s+", 2);
            String command = words[0];
            switch(command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < ls.size(); i++) {
                        System.out.println((i+1) + "." + ls.get(i).toString());
                    }
                    break;
                case "mark":
                    Task cur = ls.get(Integer.valueOf(words[1]));
                    cur.Mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + cur.description);
                    break;
                case "unmark":
                    Task cur1 = ls.get(Integer.valueOf(words[1]));
                    cur1.Unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + cur1.description);
                    break;
                case "todo":
                    Todo todo = new Todo(words[1]);
                    ls.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                case "deadline":
                    String[] parts = words[1].split("/by");
                    Deadline dl = new Deadline(parts[0], parts[1]);
                    ls.add(dl);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + dl.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                case "event":
                    String[] parts1 = words[1].split("/from|/to");
                    Event e = new Event(parts1[0], parts1[1], parts1[2]);
                    ls.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                default:
                    String desc = String.join(" ", words);
                    ls.add(new Task(desc));
                    System.out.println("added: " + desc);
            }
            if(command.equals("bye")) {
                break;
            }
        }

    }
}
