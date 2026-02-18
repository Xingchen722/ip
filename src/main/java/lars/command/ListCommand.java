package lars.command;
import java.util.stream.IntStream;

import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

/**
 * Command to list all tasks in the TaskList.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            this.response = "Your task list is empty!";
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.getSize())
                .forEach(i -> sb.append("    ")
                        .append(i + 1)
                        .append(". ")
                        .append(tasks.getTask(i))
                        .append("\n"));

        // Store the result in response for the GUI to fetch via getString()
        this.response = sb.toString().trim();
    }
}
