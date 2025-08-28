package tom;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static java.nio.file.Path path;

    public Storage(java.nio.file.Path path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws TomException, IOException {
        File file = path.toFile();
        file.getParentFile().mkdirs();
        file.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Task> ls = new ArrayList<>();
        DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

        String line = null;
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
        return ls;
    }

    public static void writeLines(ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (Task l : ls) {
            fw.write(l.saveDesc() + "\n");
        }
        fw.close();
    }
}
