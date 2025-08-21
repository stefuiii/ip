import java.util.Scanner;
import java.util.ArrayList;

public class Taro {
    private static final String Logo =
            "  /^ ^\\\n" +
                    " / 0 0 \\\n" +
                    " V\\ ^ /V\n" +
                    "  / - \\\n" +
                    " |    \\\n" +
                    " || (__V";
    private static final String LINE =
            "____________________________________________________________";

    public static void main(String[] args) {
        //Input from user
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Taro.");
        System.out.println(Logo);
        System.out.println("What I can do for you?");
        System.out.println(LINE);

        ArrayList<Task> toDoList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            // input 'bye' to exit
            if ("bye".equals(input.trim())) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            // input 'list' to list down all tasks
            if (input.equals("list")){
                System.out.println(LINE);
                System.out.println("  Here are tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + toDoList.get(i));
                }
                System.out.println(LINE);
            }

            if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                // input mark/unmark as a specific format to change status of task
                String[] parts = input.split("\\s+");
                int idx = Integer.parseInt(parts[1]); // 1-based
                Task t = toDoList.get(idx - 1);
                if (input.startsWith("mark ")) {
                    t.markAsDone();
                    System.out.println(LINE);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + t);
                    System.out.println(LINE);
                } else {
                    t.markAsUndone();
                    System.out.println(LINE);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + t);
                    System.out.println(LINE);
                }
            }

            if (input.startsWith("todo")) {
                String desc = input.length() > 4 ? input.substring(4).trim() : "";
                Task t = new Todo(desc);
                toDoList.add(t);
                System.out.println(LINE);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t);
                System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                System.out.println(LINE);
            } else if (input.startsWith("deadline")) {
                String body = input.length() > 8 ? input.substring(8).trim() : "";
                int byPos = body.indexOf("/by");
                String desc = body.substring(0, byPos).trim();
                String by   = body.substring(byPos + 3).trim(); // after "/by"
                Task t = new Deadline(desc, by);
                toDoList.add(t);
                System.out.println(LINE);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t);
                System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                System.out.println(LINE);
            } else if (input.startsWith("event")) {
                String body = input.length() > 5 ? input.substring(5).trim() : "";
                int fromPos = body.indexOf("/from");
                int toPos   = body.indexOf("/to");
                String desc = body.substring(0, fromPos).trim();
                String from = body.substring(fromPos + 5, toPos).trim();
                String to   = body.substring(toPos + 3).trim();
                Task t = new Event(desc, from, to);
                toDoList.add(t);
                System.out.println(LINE);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t);
                System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                System.out.println(LINE);
            }

        }

        sc.close();
    }
}
