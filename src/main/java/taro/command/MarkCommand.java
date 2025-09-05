package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

public class MarkCommand implements Command {

    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new TaroException("Usage: mark <index> or unmark <index>");
        }
        int idx = Integer.parseInt(parts[1]);
        tasks.get(idx - 1).markAsDone();
        Task t = tasks.get(idx - 1);
        ui.showLine();
        ui.show(" Nice! I've marked this task as done:");
        ui.show("   " + t);
        storage.save(tasks);
        return false;
    }

}

