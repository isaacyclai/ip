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
        ArrayList<String> ls = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")) {
                break;
            }
            else if(input.equals("list")) {
                for(int i = 0; i < ls.size(); i++) {
                    System.out.println((i+1) + ". " + ls.get(i));
                }
            }
            else {
                ls.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
