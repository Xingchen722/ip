package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents a command that marks or unmarks a task in the {@code TaskList}.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    /**
     * Represents a command that updates the completion status of a task.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new LarsException("Invalid task number!");
        }
        Task t = tasks.getTask(index);
        if (isMark) {
            t.markDone();
            this.response = "Nice! I've marked this task as done:\n    " + t;
        } else {
            t.markNotDone();
            this.response = "OK, I've marked this task as not done yet:\n    " + t;
        }
        storage.save(tasks.getAllTasks(), tasks.getSize());
    }
}
