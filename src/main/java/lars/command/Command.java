package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected String response;

    /**
     * Executes the command logic.
     * @param tasks The current task list.
     * @param ui The user interface for feedback.
     * @param storage The storage handler.
     * @throws LarsException If execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException;

    /**
     * Returns the text response after execution.
     * @return Formatted response string.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Indicates if this command should terminate the application.
     * @return true if it is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the response message to be displayed in the GUI.
     */
    public String getString() {
        return response;
    }
}
