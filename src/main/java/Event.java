public class Event extends Task{
    protected final String from;
    protected final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + from + "-" + to;
    }
}
