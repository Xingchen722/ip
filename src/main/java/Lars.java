import java.util.Scanner;
public class Lars {
    public static void main(String[] args){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lars");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}
