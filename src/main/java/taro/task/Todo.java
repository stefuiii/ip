package taro.task;
public class Todo extends Task{
    /**
     * Constructs a {@code Todo} task with the given description and completion status.
     *
     * @param description text description of the todo task
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Todo(String description, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T " + super.toFile();
    }

}
