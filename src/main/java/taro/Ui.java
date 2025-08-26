package taro;

import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO =
            "  /^ ^\\\n / 0 0 \\\n V\\ ^ /V\n  / - \\\n |    \\\n || (__V";

    /**
     * Displays the welcome message and logo when the program starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Taro.");
        System.out.println(LOGO);
        System.out.println("What I can do for you?");
        showLine();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the user input as a string, or {@code null} if no more input is available
     */
    public String readCommand() {
        return sc.hasNextLine() ? sc.nextLine() : null;
    }

    /**
     * Prints a dash line used as a divider
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the given message
     *
     * @param msg the message to display
     */
    public void show(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints a loading error message, prefixed with {@code [LOAD ERROR]}.
     *
     * @param msg the error details to display
     */
    public void showLoadingError(String msg) {
        System.out.println("[LOAD ERROR] " + msg);
    }

    /**
     * Displays the farewell message when the program exits.
     */
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

}
