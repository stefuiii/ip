package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaskList;
import taro.ui.Ui;

/**
 * Represents the {@code list} command which displays all tasks
 * currently stored in the task list.
 * The command input must be exactly:
 *     list
 * displays all tasks along with their status and details.
 */
public class ListCommand implements Command {

    /**
     * Executes the {@code list} command.
     * Displays all tasks in the {@link TaskList} through the {@link Ui}.
     * No modification is made to the {@link TaskList} or {@link Storage}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler; not used in this command
     * @return {@code false} as the program should not terminate after execution
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        tasks.list(ui);
        return false;
    }
}
