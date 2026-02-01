import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File taskStorageFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskStorageFile = new File(filePath + "/lars.txt");
    }

    public void save(Task[] tasks, int num) throws LarsException {
        try {
            Files.createDirectories(Path.of(this.filePath));
            if (!taskStorageFile.exists()) {
                taskStorageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new LarsException("The storage file cannot be created: " + e.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(this.taskStorageFile)) {
            for (int i = 0; i < num; i++) {
                fileWriter.write(tasks[i].toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new LarsException("Failed to write to the file: " + e.getMessage());
        }
    }

    public int readTasks(Task[] tasks) throws LarsException {
        int count = 0;
        if (!taskStorageFile.exists()) {
            return 0;
        }
        try (Scanner s = new Scanner(this.taskStorageFile)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = switch (type) {
                    case "T" -> new task.Todo(description);
                    case "D" -> new task.Deadline(description, parts[3]);
                    case "E" -> new task.Event(description, parts[3], parts[4]);
                    default -> throw new LarsException("Damaged archived data was discovered!");
                };
                if (isDone) {
                    task.BeDone();
                }
                tasks[count++] = task;
            }
        } catch (IOException e) {
            throw new LarsException("Failed to read the file: " + e.getMessage());
        }
        return count;
    }
}
