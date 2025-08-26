package taro.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, LocalDate by, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(fmt) + ")";
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + by.toString();
    }
}
