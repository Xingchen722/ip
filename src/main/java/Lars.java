import java.util.Scanner;

public class Lars {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Lars");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int num = 0;

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            if (command.equals("bye")) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------------------------------");
                break;

            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < num; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("------------------------------------------------------------");

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks[index].BeDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + tasks[index]);
                System.out.println("------------------------------------------------------------");

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks[index].NotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("    " + tasks[index]);
                System.out.println("------------------------------------------------------------");

            } else {
                if (num < tasks.length) {
                    tasks[num] = new Task(input);
                    num++;
                }
                System.out.println("    " + "added: " + input);
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
