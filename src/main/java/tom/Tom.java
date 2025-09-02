package tom;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.util.Pair;

/**
 * Represents a chatbot to help with keeping track of tasks to be done.
 */
public class Tom {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Tom(java.nio.file.Path path) throws TomException, IOException {
        ui = new Ui();
        storage = new Storage(path);
        taskList = new TaskList(storage.load());
    }

    public void run() throws TomException, IOException {
        ui.greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            Parser parser = new Parser(input);
            Pair<Pair<String, String>, Pair<Optional<Integer>, Optional<Task>>> p = parser.parse();
            String command = p.getKey().getKey();
            int idx = p.getValue().getKey().orElse(-1);
            Task task = p.getValue().getValue().orElse(new Task("NA"));

            Storage.writeLines(taskList.getTasks());

            switch(command) {
            case "bye":
                Storage.writeLines(taskList.getTasks());
                ui.bye();
                break;
            case "list":
                taskList.list();
                Storage.writeLines(taskList.getTasks());
                break;
            case "mark":
                taskList.mark(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "unmark":
                taskList.unmark(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "todo", "deadline", "event":
                taskList.add(task);
                Storage.writeLines(taskList.getTasks());
                ui.add(task, taskList);
                break;
            case "delete":
                taskList.delete(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "find":
                taskList.find(p.getKey().getValue());
                Storage.writeLines(taskList.getTasks());
                break;
            default:
                throw new TomException("Unknown command");
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws TomException, IOException {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip", "data", "tom.txt");
        new Tom(path).run();
    }
}
