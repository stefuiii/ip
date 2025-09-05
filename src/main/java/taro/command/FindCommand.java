package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code find} command which searches for tasks in the current task list
 * whose string representation contains the specified keyword.
 * Example:
 *     find book
 * lists all tasks whose string representation contains "book".
 */
public class FindCommand implements Command {
    /** The raw user input string containing the find command. */
    private final String input;

    /**
     * Constructs a {@code FindCommand} with the specified input string.
     *
     * @param input the raw user input that starts with {@code find}
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code find} command.
     * <p>
     * Extracts the keyword from the input string, iterates through the {@link TaskList},
     * and displays all tasks that contain the keyword in their string representation.
     * If no tasks match, a message is displayed instead.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler (not used in this command)
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the keyword is missing or empty
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new TaroException("Usage: find <keyword>");
        }

        ui.showLine();
        ui.show("  Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(keyword)) {
                ui.show("  " + (count + 1) + ". " + t);
                count++;
            }
        }
        if (count == 0) {
            ui.show("  (nothing)");
        }
        ui.showLine();
        return false;
    }
}
