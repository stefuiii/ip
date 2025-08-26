package taro.task;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description text description of the task
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Task(String description, boolean isCurrentTaskDone) {
        this.description = description;
        this.isDone = isCurrentTaskDone ? true : false;
    }


    /**
     * Marks this task as done.
     * Changes the {@code isDone} flag to {@code true}, indicating that the task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as undone.
     * Changes the {@code isDone} flag to {@code false}, indicating that the task has been completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    public String toFile() { return String.format("| %s | %s", isDone ? "1" : "0", description); }
}
