package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.task.Task;
import taro.ui.Ui;
public class DeleteCommand implements Command{
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new TaroException("Usage: delete <index>");
        }
        int idx = Integer.parseInt(parts[1]);

        Task removed = tasks.delete(idx);
        ui.showLine();
        ui.show(" Noted. I've removed this task:");
        ui.show("   " + removed);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks);
        return false;
    }
}
