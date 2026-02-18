package lars;

import lars.command.Command;
import lars.exceptions.LarsException;
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
    private String commandType;

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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (LarsException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public String getWelcome() {
        return ui.getWelcomeMessage();
    }
}
