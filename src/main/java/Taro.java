import java.util.Scanner;
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

        while (true) {
            String input = sc.nextLine();

            if ("bye".equals(input.trim())) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
        }

        sc.close();
    }
}
