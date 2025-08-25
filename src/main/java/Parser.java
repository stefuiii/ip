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
            return new Deadline(description, parts[3], isCurrentTaskDone);
        case "E":
            String[] period = parts[3].split("-");
            String from = period[0];
            String to = period[1];
            return new Event(description, from, to, isCurrentTaskDone);
        default:
            return null;
        }

    }
}
