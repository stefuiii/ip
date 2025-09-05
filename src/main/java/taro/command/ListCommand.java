package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaskList;
import taro.ui.Ui;
public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        tasks.list(ui);
        return false;
    }
}
