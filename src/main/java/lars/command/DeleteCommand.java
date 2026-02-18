package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * <p>
 * The task is identified by its zero-based index in the {@code TaskList}.
 * If the index is invalid, a {@code LarsException} will be thrown.
 * </p>
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new LarsException("Invalid task number!");
        }
        Task removed = tasks.deleteTask(index);
        storage.save(tasks.getAllTasks(), tasks.getSize());
        this.response = "Noted. I've removed this task:\n    " + removed
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
