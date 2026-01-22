import java.util.Scanner;
public class Lars {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Lars");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int num = 0;
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------------------------------");
                break;
            } else if(input.equals("list")) {
                for (int i = 0; i < num; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("------------------------------------------------------------");
            } else {
                if (num < tasks.length) {
                    tasks[num] = input;
                    num++;
                }
                System.out.println("added: " + input);
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
