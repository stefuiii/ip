package taro.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;

    public Event(String description, LocalDate date, LocalTime from, LocalTime to, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("h:mma");

        return "[E]" + super.toString() + " (from: " + date.format(fmtDate) + " " + from.format(fmtTime) + " to: " + to.format(fmtTime) + ")";
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + " | " + date + " | "+ from + "-" + to;
    }
}
