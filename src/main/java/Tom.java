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
            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < ls.size(); i++) {
                    System.out.println((i+1) + ". " + "[" + ls.get(i).getStatusIcon() + "] "+ ls.get(i).description);
                }
            }
            else {
                String[] strings = input.split(" ");
                if(strings.length == 2 && strings[0].equals("mark") && Character.isDigit(strings[1].charAt(0))) {
                    Task cur = ls.get(Integer.valueOf(strings[1]));
                    cur.Mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + cur.description);
                }
                else if(strings.length == 2 && strings[0].equals("unmark") && Character.isDigit(strings[1].charAt(0))) {
                    Task cur = ls.get(Integer.valueOf(strings[1]));
                    cur.Unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + cur.description);
                }
                else {
                    ls.add(new Task(input));
                    System.out.println("added: " + input);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
