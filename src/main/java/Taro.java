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
                continue;
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
                    continue;
                } else {
                    t.markAsUndone();
                    System.out.println(LINE);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + t);
                    System.out.println(LINE);
                    continue;
                }
            } else if (input.startsWith("delete")) {
                String[] parts = input.split("\\s+");
                int idx = Integer.parseInt(parts[1]); // 1-based

                Task removed = toDoList.remove(idx - 1);

                System.out.println(LINE);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removed);
                System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                System.out.println(LINE);
                continue;
            }


            try {
                if (input.startsWith("todo")) {
                    String desc = input.length() > 4 ? input.substring(4).trim() : "";
                    if (desc.isEmpty()) {
                        throw new TaroException("Oops! No description of your todo. Plz re-add your todo with decription!");
                    }
                    Task t = new Todo(desc, false);
                    toDoList.add(t);
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("deadline")) {
                    String body = input.length() > 8 ? input.substring(8).trim() : "";
                    if (body.isEmpty()) {
                        throw new TaroException("A deadline needs a description and '/by <when>'.");
                    }

                    int byPos = body.indexOf("/by");
                    String desc = body.substring(0, byPos).trim();
                    String by = body.substring(byPos + 3).trim();

                    if (desc.isEmpty()) {
                        throw new TaroException("The description of a deadline cannot be empty. ");
                    }
                    if (by.isEmpty()) {
                        throw new TaroException("The '/by' part of deadline timing is incomplete. Plz provide a complete timing");
                    }
                    Task t = new Deadline(desc, by, false);
                    toDoList.add(t);
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("event")) {
                    String body = input.length() > 5 ? input.substring(5).trim() : "";
                    if (body.isEmpty()) {
                        throw new TaroException("An event needs a description and '/from <start>' '/to <end>'.");
                    }
                    int fromPos = body.indexOf("/from");
                    int toPos = body.indexOf("/to");

                    if (fromPos == -1) {
                        throw new TaroException("Missing '/from'. Usage: event <desc> /from <start> /to <end>");
                    }
                    if (toPos == -1) {
                        throw new TaroException("Missing '/to'. Usage: event <desc> /from <start> /to <end>");
                    }
                    if (toPos < fromPos) {
                        throw new TaroException("'/to' must come after '/from'. Usage: event <desc> /from <start> /to <end>");
                    }

                    String desc = body.substring(0, fromPos).trim();
                    String from = body.substring(fromPos + 5, toPos).trim();
                    String to = body.substring(toPos + 3).trim();
                    Task t = new Event(desc, from, to, false);
                    toDoList.add(t);
                    System.out.println(LINE);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else {
                    throw new TaroException("Sorry Idk what you are saying......plz follow the input format and try again");
                }
            } catch (TaroException e) {
                System.out.println(LINE);
                System.out.println("    " + e.getMessage());
                System.out.println(LINE);
            }

        }

        sc.close();
    }
}
