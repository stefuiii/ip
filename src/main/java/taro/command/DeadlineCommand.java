package taro.command;

import java.time.LocalDate;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Deadline;
import taro.task.Task;
import taro.ui.Ui;


public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String body = input.substring(8).trim();
        int byPos = body.indexOf("/by");
        if (byPos == -1) {
            throw new TaroException("Missing /by in deadline command.");
        }
        String desc = body.substring(0, byPos).trim();
        String by = body.substring(byPos + 3).trim();
        LocalDate byTime = LocalDate.parse(by);
        Task t = new Deadline(desc, byTime, false);
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
