package taro.command;

import taro.corecomp.TaskList;
import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.task.Event;
import taro.task.Task;
import taro.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand implements Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String body = input.substring(5).trim();
        int fromPos = body.indexOf("/from");
        int toPos = body.indexOf("/to");
        if (fromPos == -1 || toPos == -1) {
            throw new TaroException("Event must have /from and /to.");
        }
        String desc = body.substring(0, fromPos).trim();
        String fromRaw = body.substring(fromPos + 5, toPos).trim();
        String toRaw = body.substring(toPos + 3).trim();

        String[] dateAndTime = fromRaw.split(" ", 2);
        LocalDate date = LocalDate.parse(dateAndTime[0]);
        LocalTime start = LocalTime.parse(dateAndTime[1]);
        LocalTime end = LocalTime.parse(toRaw);

        Task t = new Event(desc, date, start, end, false);
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