import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Tom {
    public static void main(String[] args) throws TomException, IOException {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip", "data", "tom.txt");
        File file = path.toFile();
        file.getParentFile().mkdirs();
        file.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Task> ls = new ArrayList<>();
        String line = null;

        DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|"); // type | marked | desc | by/from | to
            if (arr.length == 3) {
                Todo tmp = new Todo(arr[2].strip());
                if (arr[1].strip().equals("1")) {
                    tmp.Mark();
                }
                ls.add(tmp);
            }
            else if (arr.length == 4) {
                LocalDateTime by = LocalDateTime.parse(arr[3].strip(), output_formatter);
                Deadline tmp = new Deadline(arr[2].strip(), by);
                if (arr[1].strip().equals("1")) {
                    tmp.Mark();
                }
                ls.add(tmp);
            }
            else if (arr.length == 5) {
                LocalDateTime from = LocalDateTime.parse(arr[3].strip(), output_formatter);
                LocalDateTime to = LocalDateTime.parse(arr[4].strip(), output_formatter);
                Event tmp = new Event(arr[2].strip(), from, to);
                if (arr[1].strip().equals("1")) {
                    tmp.Mark();
                }
                ls.add(tmp);
            }
            else {
                throw new TomException("Line is not in the correct format");
            }
        }

        System.out.println("Hello! I'm Tom.\nWhat can I do for you?");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] words = input.split("\\s+", 2);
            String command = words[0];
            switch(command) {
                case "bye":
                    if (words.length != 1) {
                        throw new TomException("Bye takes no description");
                    }
                    writeLines(file, ls);
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    if (words.length != 1) {
                        throw new TomException("list takes no description");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println((i+1) + "." + ls.get(i).toString());
                    }
                    writeLines(file, ls);
                    break;
                case "mark":
                    if (words.length != 2) {
                        throw new TomException("1 task required to mark");
                    }
                    if (!Character.isDigit(words[1].charAt(0))) {
                        throw new TomException("Task must be a positive integer");
                    }
                    Task cur = ls.get(Integer.parseInt(words[1])-1);
                    cur.Mark();
                    writeLines(file, ls);
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
                    Task cur1 = ls.get(Integer.parseInt(words[1])-1);
                    cur1.Unmark();
                    writeLines(file, ls);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + cur1.description);
                    break;
                case "todo":
                    if (words.length != 2) {
                        throw new TomException("Todo requires a description");
                    }
                    Todo todo = new Todo(words[1].strip());
                    ls.add(todo);
                    writeLines(file, ls);
                    printAddMessage(todo, ls.size());
                    break;
                case "deadline":
                    if (words.length != 2) {
                        throw new TomException("Deadline requires a date by which the task must be completed");
                    }
                    String[] parts = words[1].split("/by");
                    if (parts.length != 2) {
                        throw new TomException("Deadline requires a date by which the task must be completed");
                    }
                    LocalDateTime by = LocalDateTime.parse(parts[1].strip(), input_formatter);
                    Deadline dl = new Deadline(parts[0].strip(), by);
                    ls.add(dl);
                    writeLines(file, ls);
                    printAddMessage(dl, ls.size());
                    break;
                case "event":
                    if (words.length != 2) {
                        throw new TomException("Event requires a description, start and end dates");
                    }
                    String[] parts1 = words[1].split("/from|/to");
                    if (parts1.length != 3) {
                        throw new TomException("Event requires a description, start and end dates");
                    }
                    LocalDateTime from = LocalDateTime.parse(parts1[1].strip(), input_formatter);
                    LocalDateTime to = LocalDateTime.parse(parts1[2].strip(), input_formatter);
                    Event e = new Event(parts1[0].strip(), from, to);
                    ls.add(e);
                    writeLines(file, ls);
                    printAddMessage(e, ls.size());
                    break;
                case "delete":
                    if (words.length != 2) {
                        throw new TomException("1 task required to delete");
                    }
                    if (!Character.isDigit(words[1].charAt(0))) {
                        throw new TomException("Task must be a positive integer");
                    }
                    Task removed = ls.get(Integer.parseInt(words[1])-1);
                    ls.remove(removed);
                    writeLines(file, ls);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed.toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    break;
                default:
                    throw new TomException("Command not found!");
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void printAddMessage(Task task, Integer tasks) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks + " tasks in the list.");
    }

    public static void writeLines(File file, ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task l : ls) {
            fw.write(l.saveDesc() + "\n");
        }
        fw.close();
    }
}
