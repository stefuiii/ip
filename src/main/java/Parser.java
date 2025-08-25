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
            LocalTime start = LocalTime.parse(period[0]); // 默认解析 16:00
            LocalTime end   = LocalTime.parse(period[1]);

            return new Event(description, date, start, end, isCurrentTaskDone);

            default:
            return null;
        }

    }
}
