import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO =
            "  /^ ^\\\n / 0 0 \\\n V\\ ^ /V\n  / - \\\n |    \\\n || (__V";

    public void showWelcome() {
        System.out.println("Hello! I'm Taro.");
        System.out.println(LOGO);
        System.out.println("What I can do for you?");
        showLine();
    }

    public String readCommand() {
        return sc.hasNextLine() ? sc.nextLine() : null;
    }

    public void showLine() { System.out.println(LINE); }
    public void show(String msg) { System.out.println(msg); }
    public void showError(String msg) { System.out.println("    " + msg); }
    public void showLoadingError(String msg) {
        System.out.println("[LOAD ERROR] " + msg);
    }
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }
    public void close() { sc.close(); }
}
