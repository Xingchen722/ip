package lars.command;

import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Set the farewell message for the GUI
        this.response = "Bye. Hope to see you again soon!";
    }

    /**
     * Signals that the application should terminate.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
