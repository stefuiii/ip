public class Todo extends Task{
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
