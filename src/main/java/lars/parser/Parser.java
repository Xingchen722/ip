package lars.parser;

import lars.Exceptions.LarsException;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;
import lars.task.Deadline;
import lars.task.Event;
import lars.task.Task;
import lars.task.Todo;
import java.time.LocalDate;

/**
 * Handles the interpretation of user input and executes task-related logic.
 */
public class Parser {
    /**
     * Parses the user command and interacts with the storage and task list.
     * * @param storage The storage handler for saving/loading data.
     *
     * @param tasks The current list of tasks to be modified.
     * @param ui    The user interface for displaying feedback.
     * @return true if the "bye" command is received, false otherwise.
     * @throws LarsException If the command is invalid or parameters are missing.
     */
    public static boolean parse(Storage storage, TaskList tasks, Ui ui) throws LarsException {
        String input = ui.readCommand();
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        switch (command) {
        case "bye":
            ui.showBye(); // lars.ui.Ui
            return true;
        case "list":
            ui.showTaskList(tasks); // lars.ui.Ui
            break;
        case "mark":
            if (parts.length < 2) {
                throw new LarsException("Please specify which lars.task to mark.");
            }
            int index = Integer.parseInt(parts[1]) - 1;
            if (tasks.getTask(index).getStatus()) {
                ui.showTaskHasMarked(); // lars.ui.Ui
                break;
            }
            tasks.getTask(index).BeDone(); // change status
            storage.save(tasks.getAllTasks(), tasks.getSize());
            ui.showTaskMarked(tasks.getTask(index)); // lars.ui.Ui
            break;
        case "unmark":
            if (parts.length < 2) {
                throw new LarsException("Please specify which lars.task to mark.");
            }
            int index2 = Integer.parseInt(parts[1]) - 1;
            if (!tasks.getTask(index2).getStatus()) {
                ui.showTaskHasUnmarked();
                break;
            }
            tasks.getTask(index2).NotDone();
            storage.save(tasks.getAllTasks(), tasks.getSize());
            ui.showTaskUnmarked(tasks.getTask(index2));
            break;
        case "todo":
            if (parts.length < 2) {
                throw new LarsException("The description of a todo cannot be empty.");
            }
            Task ti = new Todo(parts[1]);
            tasks.addTask(ti);
            storage.save(tasks.getAllTasks(), tasks.getSize());
            ui.showTaskAdd(ti, tasks.getSize());
            break;
        case "deadline":
            if (parts.length < 2) {
                throw new LarsException("The description of a deadline cannot be empty. You can write like 'deadline return book /by Sunday'");
            }
            String[] split = parts[1].split(" /by ");
            String status = split[0];
            String by = split[1];
            Task d = new Deadline(status, by);
            tasks.addTask(d);
            storage.save(tasks.getAllTasks(), tasks.getSize());
            ui.showTaskAdd(d, tasks.getSize());
            break;
        case "event":
            if (parts.length < 2) {
                throw new LarsException("The description of a event cannot be empty. You can write like 'event project meeting /from Mon 2pm /to 4pm'");
            }
            String[] split1 = parts[1].split(" /from ");
            String status1 = split1[0];
            String[] split2 = split1[1].split(" /to ");
            String from = split2[0];
            String to = split2[1];

            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);

            Task e = new Event(status1, fromDate, toDate);
            tasks.addTask(e);
            storage.save(tasks.getAllTasks(), tasks.getSize());
            ui.showTaskAdd(e, tasks.getSize());
            break;
        case "delete":
            if (parts.length < 2) {
                throw new LarsException("Please specify which lars.task to mark.");
            }
            int index1 = Integer.parseInt(parts[1]) - 1;
            if (index1 < 0 || index1 >= tasks.getSize()) {
                throw new LarsException("Invalid lars.task number!");
            }
            Task removed = tasks.deleteTask(index1);
            storage.save(tasks.getAllTasks(), tasks.getSize()); // lars.storage.Storage
            ui.showTaskDeleted(removed, tasks.getSize()); // lars.ui.Ui
            break;
        case "find":
            if (parts.length < 2) {
                throw new LarsException("Please specify what you want to find.");
            }
//            storage.save(tasks.findTasks(parts[1]).getAllTasks(), tasks.getSize());
            ui.showFindingTasks(tasks.findTasks(parts[1]));
            break;
        default:
            throw new LarsException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
