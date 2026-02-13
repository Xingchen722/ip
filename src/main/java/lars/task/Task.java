package lars.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String task;
    protected boolean status;

    public Task(String task) {
        this.task = task;
        this.status = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.status = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotDone() {
        this.status = false;
    }

    public String WriteStatus() {
        return status ? "X" : " ";
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "[" + WriteStatus() + "] " + getTask();
    }

    /**
     * Returns a string representation used for saving the task to a file.
     * @return A formatted string containing status and description.
     */
    public String toStorageString() {
        return (status ? "1" : "0") + " | " + task;
    }
}
