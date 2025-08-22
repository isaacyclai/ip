import java.util.Scanner;
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
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
