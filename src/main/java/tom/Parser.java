package tom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.util.Pair;

/**
 * Processes the user's input.
 */
public class Parser {
    private final String command;
    private final String[] words;

    public Parser(String input) {
        String[] line = input.split("\\s+", 2);
        this.command = line[0].strip();
        this.words = line;
    }

    /**
     * Returns a pair consisting of two pairs.
     * The first pair contains the command given by the user and the rest of the input.
     * The second pair consists of two Optionals. The first Optional is the task number for
     * add/delete/mark/unmark commands, while the second is the new task to be added for the other commands.
     * @return Command, task number, and new task.
     * @throws TomException If command is invalid.
     */
    public Pair<Pair<String, String>, Pair<Optional<Integer>, Optional<Task>>> parse() throws TomException {
        Optional<Task> task = Optional.empty();
        Optional<Integer> idx = Optional.empty();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String args = "";
        switch(command) {
        case "bye":
            if (words.length != 1) {
                throw new TomException("Bye takes no description");
            }
            break;
        case "list":
            if (words.length != 1) {
                throw new TomException("list takes no description");
            }
            break;
        case "mark":
            if (words.length != 2) {
                throw new TomException("1 task required to mark");
            }
            if (!Character.isDigit(words[1].strip().charAt(0))) {
                throw new TomException("Task must be a positive integer");
            }
            idx = Optional.of(Integer.parseInt(words[1].strip()));
            break;
        case "unmark":
            if (words.length != 2) {
                throw new TomException("1 task required to unmark");
            }
            if (!Character.isDigit(words[1].strip().charAt(0))) {
                throw new TomException("Task must be a positive integer");
            }
            idx = Optional.of(Integer.parseInt(words[1].strip()));
            break;
        case "prioritise":
            if (words.length != 2) {
                throw new TomException("1 task required to prioritise");
            }
            if (!Character.isDigit(words[1].strip().charAt(0))) {
                throw new TomException("Task must be a positive integer");
            }
            idx = Optional.of(Integer.parseInt(words[1].strip()));
            break;
        case "todo":
            if (words.length != 2) {
                throw new TomException("Todo requires a description");
            }
            task = Optional.of(new Todo(words[1].strip()));
            break;
        case "deadline":
            if (words.length != 2) {
                throw new TomException("Deadline requires a date by which the task must be completed");
            }
            String[] parts = words[1].split("/by");
            if (parts.length != 2) {
                throw new TomException("Deadline requires a date by which the task must be completed");
            }
            LocalDateTime by = LocalDateTime.parse(parts[1].strip(), inputFormatter);
            task = Optional.of(new Deadline(parts[0].strip(), by));
            break;
        case "event":
            if (words.length != 2) {
                throw new TomException("Event requires a description, start and end dates");
            }
            String[] parts1 = words[1].split("/from|/to");
            if (parts1.length != 3) {
                throw new TomException("Event requires a description, start and end dates");
            }
            LocalDateTime from = LocalDateTime.parse(parts1[1].strip(), inputFormatter);
            LocalDateTime to = LocalDateTime.parse(parts1[2].strip(), inputFormatter);
            task = Optional.of(new Event(parts1[0].strip(), from, to));
            break;
        case "delete":
            if (words.length != 2) {
                throw new TomException("1 task required to delete");
            }
            if (!Character.isDigit(words[1].strip().charAt(0))) {
                throw new TomException("Task must be a positive integer");
            }
            idx = Optional.of(Integer.parseInt(words[1].strip()));
            break;
        case "find":
            args = words[1].strip();
            break;
        default:
            throw new TomException("Command not found!");
        }
        return new Pair<>(new Pair<>(command, args), new Pair<>(idx, task));
    }
}
