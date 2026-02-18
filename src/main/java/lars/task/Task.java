package lars.task;

import java.time.LocalDate;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String task;
    protected boolean status;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initialized with an incomplete status (status = false).
     * @param task The description of the task.
     */
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

    public String writeStatus() {
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
        return "[" + writeStatus() + "] " + getTask();
    }

    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns a string representation used for saving the task to a file.
     * @return A formatted string containing status and description.
     */
    public String toStorageString() {
        return (status ? "1" : "0") + " | " + task;
    }
}
