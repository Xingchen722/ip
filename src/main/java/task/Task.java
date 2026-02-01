package task;

public class Task {
    protected String task;
    protected boolean status;

    public Task(String task) {
        this.task = task;
        this.status = false;
    }

    public void BeDone() {
        this.status = true;
    }

    public void NotDone() {
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

    public String toStorageString() {
        return (status ? "1" : "0") + " | " + task;
    }
}
