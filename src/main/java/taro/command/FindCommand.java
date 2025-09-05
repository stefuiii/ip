package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.task.Event;
import taro.task.Task;
import taro.ui.Ui;
public class FindCommand implements Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
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
