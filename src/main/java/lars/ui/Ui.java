package lars.ui;

import java.util.Scanner;
import java.util.stream.IntStream;

import lars.task.Task;
import lars.task.TaskList;

/**
 * Handles all user interface interactions, including input reading and message printing.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a separator line.
     */
    public void printLine() {
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Returns the welcome message shown to the user.
     */
    // 在 Ui.java 中添加
    public String getWelcomeMessage() {
        return "Hello! I'm Lars\nWhat can I do for you?";
    }

    /**
     * Reads the next line of text entered by the user.
     *
     * @return The full command string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error detail to be printed.
     */
    public void readError(String msg) {
        System.out.println("OOPS!!! " + msg);
        printLine();
    }

    /**
     * Displays that a task has been deleted.
     *
     * @param t The Task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void showTaskDeleted(Task t, int size) {
        System.out.println("Noted. I've removed this lars.task:\n    " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Displays a goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays that a task has been marked.
     */
    public void showTaskHasMarked() {
        System.out.println("You have marked this lars.task");
        printLine();
    }

    /**
     * Displays that a task has been marked as done.
     *
     * @param t The Task that was marked.
     */
    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this lars.task as done:");
        System.out.println("    " + t);
        printLine();
    }

    /**
     * Displays that a task has been unmarked.
     */
    public void showTaskHasUnmarked() {
        System.out.println("You have unmarked this lars.task");
        printLine();
    }

    /**
     * Displays that a task has been unmarked as not done.
     *
     * @param t The Task that was unmarked.
     */
    public void showTaskUnmarked(Task t) {
        System.out.println("OK, I've marked this lars.task as not done yet:");
        System.out.println("    " + t);
        printLine();
    }

    /**
     * Lists all current tasks to the console in a numbered format.
     *
     * @param tasks The TaskList containing tasks to show.
     */
    public void showTaskList(TaskList tasks) {
        assert tasks != null : "Task cannot be null";
        System.out.println("Here are the tasks in your list:");
        IntStream.range(0, tasks.getSize())
                .forEach(i -> System.out.println("    " + (i + 1) + ". " + tasks.getTask(i)));
        printLine();
    }

    /**
     * Displays that a task has been added.
     *
     * @param task The Task that was added.
     * @param num The current number of tasks in the list.
     */
    public void showTaskAdd(Task task, int num) {
        assert task != null : "Task cannot be null";
        System.out.println("Got it. I've added this lars.task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + num + " tasks in the list.");
        printLine();
    }

    /**
     * Displays tasks matching a search query.
     *
     * @param tasks The TaskList containing matching tasks.
     */
    public void showFindingTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        IntStream.range(0, tasks.getSize())
                .forEach(i -> System.out.println("    " + (i + 1) + ". " + tasks.getTask(i)));
        printLine();
    }
}
