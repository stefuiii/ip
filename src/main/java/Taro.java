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

        ArrayList<String> toDoList = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();

            if ("bye".equals(input.trim())) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (input.equals("list")){
                System.out.println(LINE);
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + toDoList.get(i));
                }
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println(input);
                System.out.println(LINE);
                toDoList.add(input);
            }
        }

        sc.close();
    }
}
