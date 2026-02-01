package task;

public class Todo extends Task {
    public Todo(String status) {
        super(status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }
}
