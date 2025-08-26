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

    public static Task parseCommand(String input) throws TaroException {







        if (input.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) {
                throw new TaroException("The description of a todo cannot be empty.");
            }
            return new Todo(desc, false);
        } else if (input.startsWith("deadline")) {
            String body = input.substring(8).trim();
            int byPos = body.indexOf("/by");
            if (byPos == -1) throw new TaroException("Missing /by in deadline command.");
            String desc = body.substring(0, byPos).trim();
            String by = body.substring(byPos + 3).trim();
            LocalDate byTime = LocalDate.parse(by); // yyyy-MM-dd
            return new Deadline(desc, byTime, false);
        } else if (input.startsWith("event")) {
            String body = input.substring(5).trim();
            int fromPos = body.indexOf("/from");
            int toPos = body.indexOf("/to");
            if (fromPos == -1 || toPos == -1) throw new TaroException("Event must have /from and /to.");
            String desc = body.substring(0, fromPos).trim();
            String fromRaw = body.substring(fromPos + 5, toPos).trim(); // yyyy-MM-dd HHmm
            String toRaw   = body.substring(toPos + 3).trim();

            String[] dateAndTime = fromRaw.split(" ", 2);
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            LocalTime start = LocalTime.parse(dateAndTime[1]);
            LocalTime end   = LocalTime.parse(toRaw);
            return new Event(desc, date, start, end, false);
        } else {
            throw new TaroException("Unknown command: " + input);
        }
    }
}
