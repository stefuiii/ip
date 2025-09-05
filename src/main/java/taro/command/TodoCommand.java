package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.task.Task;
import taro.task.Todo;
import taro.ui.Ui;
public class TodoCommand implements Command {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new TaroException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(desc, false);
        tasks.add(t);
        storage.save(tasks);
        ui.showLine();
        ui.show(" Got it. I've added this task:");
        ui.show("   " + t);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return false;
    }
}
