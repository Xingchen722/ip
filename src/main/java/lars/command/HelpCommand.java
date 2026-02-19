package lars.command;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.response =
                "Here are the commands you can use:\n\n" +

                        "1. list\n\n" +

                        "2. todo <description>\n" +
                        "   Example:\n" +
                        "     todo read book\n\n" +

                        "3. deadline <description> /by <date>\n" +
                        "   Examples:\n" +
                        "     1. deadline submit report /by 2026-02-15\n" +
                        "     2. deadline return library book /by 2026-02-15 1800\n" +
                        "     3. deadline return library book /by Sunday\n\n" +

                        "4. event <description> /from <start> /to <end>\n" +
                        "   Example:\n" +
                        "     event meeting /from 2pm /to 4pm\n\n" +

                        "5. mark <number>\n" +
                        "6. unmark <number>\n" +
                        "7. delete <number>\n" +
                        "8. remind (or press ⏰)\n" +
                        "9. bye";
    }
}
