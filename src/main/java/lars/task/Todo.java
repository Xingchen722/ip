package lars.task;

/**
 * Represents a "Todo" task.
 * A Todo task is a basic task with only a description and no specific date or time associated with it.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     * @param status The description of the todo task.
     */
    public Todo(String status) {
        super(status);
    }

    /**
     * Returns a string representation of the Todo task for display.
     * @return A string prefixed with "[T]" followed by the standard task representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task formatted for file storage.
     * @return A string in the format "T | status | description".
     */
    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }
}
