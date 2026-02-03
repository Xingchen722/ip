package lars.storage;

import lars.Exceptions.LarsException;
import lars.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
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

    public Task[] load() throws LarsException {
        int count = 0;
        Task[] tasks = new Task[100];
        if (!taskStorageFile.exists()) {
            return tasks;
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

                Task task = null;
                switch (type) {
                    case "T":
                        task = new lars.task.Todo(description);
                        break;
                    case "D":
                        task = new lars.task.Deadline(description, parts[3]);
                        break;
                    case "E":
                        LocalDate fromDate = LocalDate.parse(parts[3]);
                        LocalDate toDate = LocalDate.parse(parts[4]);
                        task = new lars.task.Event(description, fromDate, toDate);
                        break;
                    default:
                        throw new LarsException("Damaged archived data was discovered!");
                }

                if (task != null) {
                    if (isDone) {
                        task.BeDone();
                    }
                    tasks[count++] = task;
                }
            }
        } catch (IOException e) {
            throw new LarsException("Failed to read the file: " + e.getMessage());
        }
        return tasks;
    }
}
