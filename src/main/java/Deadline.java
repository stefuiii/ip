public class Deadline extends Task{
    protected final String by;

    public Deadline(String description, String by, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + by;
    }
}
