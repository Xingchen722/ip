package lars;

import lars.Exceptions.LarsException;
import lars.parser.Parser;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * The main class for the Lars task management application.
 * It initializes the UI, storage, and task list components and starts the program loop.
 */
public class Lars {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Lars instance.
     * *@param filePath The directory path where task data is stored.
     */
    public Lars(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LarsException e) {
            ui.readError("Failed to load tasks." + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                isExit = Parser.parse(storage, tasks, ui);
            } catch (LarsException e) {
                ui.readError(e.getMessage());
            } catch (Exception e) {
                ui.readError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Main entry point for the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Lars("./data").run();
    }
}
