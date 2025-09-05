package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.task.Task;
import taro.ui.Ui;

public class UnmarkCommand implements Command{

    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            throw new TaroException("Usage: mark <index> or unmark <index>");
        }
        int idx = Integer.parseInt(parts[1]);
        tasks.get(idx - 1).markAsUndone();
        Task t = tasks.get(idx - 1);
        ui.showLine();
        ui.show(" OK, I've marked this task as not done yet:");
        ui.show("   " + t);
        storage.save(tasks);
        return false;
    }

}

