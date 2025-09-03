package tom;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.util.Pair;

/**
 * Represents a chatbot to help with keeping track of tasks to be done.
 */
public class Tom {
    private static final String DEFAULT_FILE_PATH = "./data/tom.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Tom(java.nio.file.Path path) throws TomException, IOException {
        ui = new Ui();
        storage = new Storage(path);
        taskList = new TaskList(storage.load());
    }

    public Tom() throws TomException, IOException {
        this(Paths.get(DEFAULT_FILE_PATH));
    }

    public String run(String input) throws TomException, IOException {
        Parser parser = new Parser(input);
        Pair<Pair<String, String>, Pair<Optional<Integer>, Optional<Task>>> p = parser.parse();
        String command = p.getKey().getKey();
        int idx = p.getValue().getKey().orElse(-1);
        Task task = p.getValue().getValue().orElse(new Task("NA"));

        Storage.writeLines(taskList.getTasks());

        String response = "";

        switch(command) {
        case "bye":
            response = ui.bye();
            Storage.writeLines(taskList.getTasks());
            break;
        case "list":
            response = taskList.list();
            Storage.writeLines(taskList.getTasks());
            break;
        case "mark":
            response = taskList.mark(idx);
            Storage.writeLines(taskList.getTasks());
            break;
        case "unmark":
            response = taskList.unmark(idx);
            Storage.writeLines(taskList.getTasks());
            break;
        case "todo", "deadline", "event":
            response = taskList.add(task);
            Storage.writeLines(taskList.getTasks());
            break;
        case "delete":
            response = taskList.delete(idx);
            Storage.writeLines(taskList.getTasks());
            break;
        case "find":
            response = taskList.find(p.getKey().getValue());
            Storage.writeLines(taskList.getTasks());
            break;
        default:
            throw new TomException("Unknown command");
        }
        return response;
    }
}
