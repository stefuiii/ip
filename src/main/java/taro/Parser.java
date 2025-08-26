package taro;

import taro.command.Command;
import taro.task.Deadline;
import taro.task.Event;
import taro.task.Task;
import taro.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isCurrentTaskDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isCurrentTaskDone);
        case "D":
            LocalDate byTime = LocalDate.parse(parts[3]);
            return new Deadline(description, byTime, isCurrentTaskDone);
        case "E":
            LocalDate date = LocalDate.parse(parts[3]); // yyyy-MM-dd

            String[] period = parts[4].split("-");
            LocalTime start = LocalTime.parse(period[0]);
            LocalTime end   = LocalTime.parse(period[1]);

            return new Event(description, date, start, end, isCurrentTaskDone);

            default:
            return null;
        }

    }

    public static Command parseCommand(String input) throws TaroException {
        input = input.trim();

        if (input.equals("bye")) {
            return (tasks, ui, storage) -> {
                ui.showLine();
                ui.show("Bye. Hope to see you again soon!");
                ui.showLine();
                return true;
            };
        }

        if (input.equals("list")) {
            return (tasks, ui, storage) -> {
                ui.showLine();
                tasks.list();
                ui.showLine();
                return false;
            };
        }

        if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            String[] parts = input.split("\\s+");
            if (parts.length < 2) throw new TaroException("Usage: mark <index> or unmark <index>");
            int idx = Integer.parseInt(parts[1]);
            boolean isMark = input.startsWith("mark ");
            return (tasks, ui, storage) -> {

                if (isMark) {
                    tasks.get(idx-1).markAsDone();
                } else {
                    tasks.get(idx-1).markAsUndone();
                }
                Task t =  tasks.get(idx-1);
                ui.showLine();
                if (isMark) {
                    ui.show(" Nice! I've marked this task as done:");
                } else {
                    ui.show(" OK, I've marked this task as not done yet:");
                }
                ui.show("   " + t);
                ui.showLine();
                storage.save(tasks);
                return false;
            };
        }

        if (input.startsWith("delete")) {
            String[] parts = input.split("\\s+");
            if (parts.length < 2) throw new TaroException("Usage: delete <index>");
            int idx = Integer.parseInt(parts[1]);
            return (tasks, ui, storage) -> {
                Task removed = tasks.delete(idx);
                ui.showLine();
                ui.show(" Noted. I've removed this task:");
                ui.show("   " + removed);
                ui.show(" Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                storage.save(tasks);
                return false;
            };
        }


        if (input.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) throw new TaroException("The description of a todo cannot be empty.");
            return (tasks, ui, storage) -> {
                Task t = new Todo(desc, false);
                tasks.add(t);
                storage.save(tasks);
                ui.showLine();
                ui.show(" Got it. I've added this task:");
                ui.show("   " + t);
                ui.show(" Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                return false;
            };
        }

        if (input.startsWith("deadline")) {
            String body = input.substring(8).trim();
            int byPos = body.indexOf("/by");
            if (byPos == -1) throw new TaroException("Missing /by in deadline command.");
            String desc = body.substring(0, byPos).trim();
            String by = body.substring(byPos + 3).trim();
            LocalDate byTime = LocalDate.parse(by);
            return (tasks, ui, storage) -> {
                Task t = new Deadline(desc, byTime, false);
                tasks.add(t);
                storage.save(tasks);
                ui.showLine();
                ui.show(" Got it. I've added this task:");
                ui.show("   " + t);
                ui.show(" Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                return false;
            };
        }

        if (input.startsWith("event")) {
            String body = input.substring(5).trim();
            int fromPos = body.indexOf("/from");
            int toPos = body.indexOf("/to");
            if (fromPos == -1 || toPos == -1) throw new TaroException("Event must have /from and /to.");
            String desc = body.substring(0, fromPos).trim();
            String fromRaw = body.substring(fromPos + 5, toPos).trim();
            String toRaw   = body.substring(toPos + 3).trim();

            String[] dateAndTime = fromRaw.split(" ", 2);
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            LocalTime start = LocalTime.parse(dateAndTime[1]);
            LocalTime end   = LocalTime.parse(toRaw);

            return (tasks, ui, storage) -> {
                Task t = new Event(desc, date, start, end, false);
                tasks.add(t);
                storage.save(tasks);
                ui.showLine();
                ui.show(" Got it. I've added this task:");
                ui.show("   " + t);
                ui.show(" Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                return false;
            };
        }

        throw new TaroException("Unknown command: " + input);
    }
}
