package taro;

import java.util.Scanner;

/**
 * This is the class for UI
 * All interactions and display would be handled here.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO =
            "  /^ ^\\\n / 0 0 \\\n V\\ ^ /V\n  / - \\\n |    \\\n || (__V";
    private final Scanner sc = new Scanner(System.in);
    private final StringBuilder buffer = new StringBuilder();
    /**
     * Displays the welcome message and logo when the program starts.
     */
    public String showWelcome() {
        return "Hello! I'm Taro\n"
                + "Your personal assistant bot\n"
                + "What can I do for you?";
    }

    private void addToBuffer(String msg) {
        buffer.append(msg).append("\n");
    }

    public String consumeBuffer() {
        String result = buffer.toString();
        buffer.setLength(0); // 清空
        return result;
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
    }

    /**
     * Prints the given message
     *
     * @param msg the message to display
     */
    public void show(String msg) {
        addToBuffer(msg);
    }

    /**
     * Prints a loading error message, prefixed with {@code [LOAD ERROR]}.
     *
     * @param msg the error details to display
     */
    public void showLoadingError(String msg) {
        addToBuffer("[LOAD ERROR] " + msg);
    }

    /**
     * Displays the farewell message when the program exits.
     */
    public void showBye() {
        addToBuffer("Bye. Hope to see you again soon!");
    }

}
