package lars;

import java.util.Scanner;

import lars.task.Task;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void Line() {
        System.out.println("------------------------------------------------------------");
    }

    public void welcome() {
        Line();
        System.out.println("Hello! I'm lars.Lars");
        System.out.println("What can I do for you?");
        Line();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void readError(String msg) {
        System.out.println("OOPS!!! " + msg);
        Line();
    }

    public void showTaskDeleted(Task t, int size) {
        System.out.println("Noted. I've removed this lars.task:\n    " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
        Line();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Line();
    }

    public void showTaskHasMarked() {
        System.out.println("You have marked this lars.task");
        Line();
    }
    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this lars.task as done:");
        System.out.println("    " + t);
        Line();
    }

    public void showTaskHasUnmarked() {
        System.out.println("You have unmarked this lars.task");
        Line();
    }

    public void showTaskUnmarked(Task t) {
        System.out.println("OK, I've marked this lars.task as not done yet:");
        System.out.println("    " + t);
        Line();
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.getTask(i));
        }
        Line();
    }

    public void showTaskAdd(Task task, int num) {
        System.out.println("Got it. I've added this lars.task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + num + " tasks in the list.");
        Line();
    }
}
