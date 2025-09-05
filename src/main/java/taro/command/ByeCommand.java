package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.ui.Ui;
public class ByeCommand implements Command{
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        return true;
    }
}
