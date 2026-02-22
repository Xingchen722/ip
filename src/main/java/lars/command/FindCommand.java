package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.TaskList;
import lars.ui.Ui;

public class FindCommand extends Command {
    private String s;

    public FindCommand(String s) {
        this.s = s;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LarsException {
        TaskList matchingTasks = tasks.findTasks(s);

        if (matchingTasks.getSize() == 0) {
            this.response = "Sorry😞, I couldn't find any tasks matching: " + s;
            return;
        }

        this.response = "Here are the matching tasks in your list:\n";
        for(int i = 0; i < matchingTasks.getSize(); i++) {
            Task t = matchingTasks.getTask(i);
            this.response += (i + 1) + "." + t.toString() + "\n";
        }
    }
}
