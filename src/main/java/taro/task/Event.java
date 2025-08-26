package taro.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;

    /**
     * Constructs an {@code Event} task with the given description, date, time period, and completion status.
     *
     * @param description text description of the event task
     * @param date the date on which the event occurs
     * @param from the starting time of the event
     * @param to the ending time of the event
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Event(String description, LocalDate date, LocalTime from,
                 LocalTime to, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString()
                + " (from: " + date.format(fmtDate) + " " + from.format(fmtTime) + " to: "
                + to.format(fmtTime) + ")";
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + " | " + date + " | "+ from + "-" + to;
    }
}
