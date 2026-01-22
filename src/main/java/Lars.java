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

            } else if (command.equals("todo")) {
                tasks[num] = new Todo(parts[1]);
                num++;

                System.out.println("Got it. I've added this task:");
                System.out.println("    " + tasks[num - 1].toString());
                System.out.println("Now you have " + num + " tasks in the list.");
                System.out.println("------------------------------------------------------------");

            }else if (command.equals("deadline")) {
                String[] split = parts[1].split(" /by ");
                String status = split[0];
                String by = split[1];
                tasks[num] = new Deadline(status, by);
                num++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[num - 1].toString());
                System.out.println("Now you have " + num + " tasks in the list.");
                System.out.println("------------------------------------------------------------");

            }else if(command.equals("event")) {
                String[] split = parts[1].split(" /from ");
                String status = split[0];
                String[] split2 = split[1].split(" /to ");
                String from = split2[0];
                String to = split2[1];
                tasks[num] = new Event(status, from, to);
                num++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[num - 1].toString());
                System.out.println("Now you have " + num + " tasks in the list.");
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
