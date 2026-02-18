package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Represents a command that adds a specified task to the {@code TaskList}.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException {
        tasks.addTask(task);
        storage.save(tasks.getAllTasks(), tasks.getSize());
        this.response = "Got it. I've added this task:\n    " + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
}
