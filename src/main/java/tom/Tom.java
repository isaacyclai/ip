package tom;

import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a chatbot to help with keeping track of tasks to be done.
 */
public class Tom {
    private Storage storage;
    private TaskList ls;
    private Ui ui;

    public Tom(java.nio.file.Path path) throws TomException, IOException {
        ui = new Ui();
        storage = new Storage(path);
        ls = new TaskList(storage.load());
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

            Storage.writeLines(ls.getTasks());

            switch(command) {
            case "bye":
                Storage.writeLines(ls.getTasks());
                ui.bye();
                break;
            case "list":
                ls.list();
                Storage.writeLines(ls.getTasks());
                break;
            case "mark":
                ls.mark(idx);
                Storage.writeLines(ls.getTasks());
                break;
            case "unmark":
                ls.unmark(idx);
                Storage.writeLines(ls.getTasks());
                break;
            case "todo", "deadline", "event":
                ls.add(task);
                Storage.writeLines(ls.getTasks());
                ui.add(task, ls);
                break;
            case "delete":
                ls.delete(idx);
                Storage.writeLines(ls.getTasks());
                break;
            case "find":
                ls.find(p.getKey().getValue());
                Storage.writeLines(ls.getTasks());
                break;
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
