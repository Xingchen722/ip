package lars.parser;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.ui.Ui;
import lars.task.TaskList;
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
    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";

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
            handleMark(parts, tasks, storage, ui);
            break;
        case "unmark":
            handleUnMark(parts, tasks, storage, ui);
            break;
        case "todo":
            handleTodo(parts, tasks, storage, ui);
            break;
        case "deadline":
            handleDdl(parts, tasks, storage, ui);
            break;
        case "event":
            handleEvent(parts, tasks, storage, ui);
            break;
        case "delete":
            handleDelete(parts, tasks, storage, ui);
            break;
        case "find":
            handleFind(parts, tasks, storage, ui);
            break;
        default:
            throw new LarsException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    public static void handleMark(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException{
        if (parts.length < 2) {
            throw new LarsException("Please specify which lars.task to mark.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        if (tasks.getTask(index).getStatus()) {
            ui.showTaskHasMarked();
        }
        tasks.getTask(index).markDone();
        storage.save(tasks.getAllTasks(), tasks.getSize());
        ui.showTaskMarked(tasks.getTask(index));
    }

    public static void handleUnMark(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException {
        if (parts.length < 2) {
            throw new LarsException("Please specify which lars.task to mark.");
        }
        int index2 = Integer.parseInt(parts[1]) - 1;
        if (!tasks.getTask(index2).getStatus()) {
            ui.showTaskHasUnmarked();
        }
        tasks.getTask(index2).markNotDone();
        storage.save(tasks.getAllTasks(), tasks.getSize());
        ui.showTaskUnmarked(tasks.getTask(index2));
    }

    public static void handleTodo(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException{
        if (parts.length < 2) {
            throw new LarsException("The description of a todo cannot be empty.");
        }
        Task ti = new Todo(parts[1]);
        tasks.addTask(ti);
        storage.save(tasks.getAllTasks(), tasks.getSize());
        ui.showTaskAdd(ti, tasks.getSize());
    }

    public static void handleDdl(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException {
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
    }

    public static void handleEvent(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException {
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
    }

    public static void handleDelete(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException {
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
    }

    public static void handleFind(String[] parts, TaskList tasks, Storage storage, Ui ui) throws LarsException {
        if (parts.length < 2) {
            throw new LarsException("Please specify what you want to find.");
        }
        ui.showFindingTasks(tasks.findTasks(parts[1]));
    }
}
