public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isCurrentTaskDone) {
        this.description = description;
        this.isDone = isCurrentTaskDone ? true : false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    public String toFile() { return String.format("| %s | %s", isDone ? "0" : "1", description); }
}
