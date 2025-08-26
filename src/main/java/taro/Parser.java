package taro;

import taro.command.Command;
import taro.task.Deadline;
import taro.task.Event;
import taro.task.Task;
import taro.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    /**
     * Parses a line of text from the storage file and reconstructs the corresponding {@code Task} object.
     * The input line must follow the format used when saving tasks to file, i.e. fields separated by {@code " | "}.
     * @param line the raw text line from the data file
     * @return the corresponding {@link Todo}, {@link Deadline}, or {@link Event} object;
     *         {@code null} if the task type is unrecognized
     * @throws java.time.format.DateTimeParseException if the date/time fields cannot be parsed
     * @throws ArrayIndexOutOfBoundsException if the input line does not contain the expected number of fields
     */
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

    /**
     * Parses a user input string into a corresponding {@link Command} object that can be executed
     * on a {@link taro.TaskList}.
     *
     * Supported commands and their formats:
     *   {@code bye} &mdash; exits the program
     *   {@code list} &mdash; lists all tasks
     *   {@code mark <index>} &mdash; marks the task at the given index as done
     *   {@code unmark <index>} &mdash; marks the task at the given index as not done
     *   {@code delete <index>} &mdash; deletes the task at the given index
     *   {@code todo <description>} &mdash; adds a new {@code Todo}</li>
     *   {@code deadline <description> /by <yyyy-mm-dd>} &mdash; adds a new {@code Deadline}
     *   {@code event <description> /from <yyyy-mm-dd HH:mm> /to <HH:mm>} &mdash; adds a new {@code Event}
     *   {@code find <keyword>} &mdash; searches tasks by keyword in their string representation
     *
     *
     * @param input the raw user input string
     * @return a {@link Command} object encapsulating the action to be executed
     * @throws TaroException if the input is invalid, missing arguments, or the command type is unknown
     */
    public static Command parseCommand(String input) throws TaroException {
        input = input.trim();

        if (input.equals("bye")) {
            return (tasks, ui, storage) -> {
                ui.showLine();
                ui.showBye();
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

        if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new TaroException("Usage: find <keyword>");
            }
            return (tasks, ui, storage) -> {
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
            };
        }

        throw new TaroException("Unknown command: " + input);
    }
}
