package lars.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import lars.exceptions.LarsException;
import lars.task.Task;

/**
 * Handles loading tasks from and saving tasks to a local file.
 */
public class Storage {
    private static final String FILE_NAME = "lars.txt";
    private static final String STORAGE_DELIMITER = " \\| ";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";

    private final File taskStorageFile;

    /**
     * Constructs a new Storage object that handles saving and loading tasks
     * to a file at the specified path.
     *
     * @param filePath The directory path where the task storage file will be located.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.taskStorageFile = new File(filePath + FILE_NAME);
    }

    /**
     * Saves the provided task array into a text file.
     * @param tasks The array of tasks to save.
     * @param num   The number of valid tasks in the array.
     * @throws LarsException If an I/O error occurs during file creation or writing.
     */
    public void save(Task[] tasks, int num) throws LarsException {
        assert tasks != null : "Tasks array should not be null";

        prepareFile();

        try (FileWriter fileWriter = new FileWriter(this.taskStorageFile)) {
            for (int i = 0; i < num; i++) {
                if (tasks[i] != null) {
                    fileWriter.write(tasks[i].toStorageString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new LarsException("Failed to write to the file: " + e.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(this.taskStorageFile)) {
            for (int i = 0; i < num; i++) {
                fileWriter.write(tasks[i].toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new LarsException("Failed to write to the file: " + e.getMessage());
        }
    }

    private void prepareFile() throws LarsException {
        try {
            File parent = taskStorageFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!taskStorageFile.exists()) {
                taskStorageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new LarsException("The storage file cannot be created: " + e.getMessage());
        }
    }

    /**
     * Reads task data from the storage file and reconstructs Task objects.
     * @return An array of Task objects loaded from the file.
     * @throws LarsException If the file is corrupted or cannot be read.
     */
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
                tasks[count++] = parseTaskFromStorage(line);
            }
        } catch (IOException e) {
            throw new LarsException("Failed to read the file: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTaskFromStorage(String line) throws LarsException {
        String[] parts = line.split(STORAGE_DELIMITER);
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case TODO_TYPE:
            task = new lars.task.Todo(description);
            break;
        case DEADLINE_TYPE:
            task = new lars.task.Deadline(description, parts[3]);
            break;
        case EVENT_TYPE:
            LocalDate fromDate = LocalDate.parse(parts[3]);
            LocalDate toDate = LocalDate.parse(parts[4]);
            task = new lars.task.Event(description, fromDate, toDate);
            break;
        default:
            throw new LarsException("Damaged archived data was discovered!");
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }
}
