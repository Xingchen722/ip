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

        label:
        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            try {
                switch (command) {
                    case "bye":
                        System.out.println("------------------------------------------------------------");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("------------------------------------------------------------");
                        break label;

                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < num; i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks[i]);
                        }
                        System.out.println("------------------------------------------------------------");

                        break;
                    case "mark": {
                        if (parts.length < 2) {
                            throw new LarsException("Please specify which task to mark.");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        //如果显示已经完成了，就提示一下
                        if(tasks[index].getStatus()) {
                            System.out.println("You have marked this task");
                            System.out.println("------------------------------------------------------------");
                            break;
                        }
                        tasks[index].BeDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + tasks[index]);
                        System.out.println("------------------------------------------------------------");
                        break;
                    }
                    case "unmark": {
                        if (parts.length < 2) {
                            throw new LarsException("Please specify which task to mark.");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        //如果显示已经改为未完成了，就提示
                        if(!tasks[index].getStatus()) {
                            System.out.println("You have unmarked this task");
                            System.out.println("------------------------------------------------------------");
                            break;
                        }
                        tasks[index].NotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    " + tasks[index]);
                        System.out.println("------------------------------------------------------------");
                        break;
                    }
                    case "todo":
                        if (parts.length < 2) {
                            throw new LarsException("The description of a todo cannot be empty.");
                        }
                        tasks[num] = new Todo(parts[1]);
                        num++;

                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + tasks[num - 1].toString());
                        System.out.println("Now you have " + num + " tasks in the list.");
                        System.out.println("------------------------------------------------------------");
                        break;
                    case "deadline": {
                        if (parts.length < 2) {
                            throw new
                                    LarsException("The description of a deadline cannot be empty. You can write like 'deadline return book /by Sunday'");
                        }
                        String[] split = parts[1].split(" /by ");
                        String status = split[0];
                        String by = split[1];
                        tasks[num] = new Deadline(status, by);
                        num++;

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[num - 1].toString());
                        System.out.println("Now you have " + num + " tasks in the list.");
                        System.out.println("------------------------------------------------------------");
                        break;
                    }
                    case "event": {
                        if (parts.length < 2) {
                            throw new
                                    LarsException("The description of a event cannot be empty. You can write like 'event project meeting /from Mon 2pm /to 4pm'");
                        }
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
                        break;
                    }
                    default:
                        throw new LarsException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (LarsException e) {
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
