package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents a command that generates a reminder message
 * for upcoming and overdue tasks in the TaskList.
 *
 * <p>When executed, this command fetches the reminder string
 * from the TaskList and stores it in the {@code response} field.</p>
 */
public class RemindCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException {
        this.response = tasks.getReminder();
    }
}
