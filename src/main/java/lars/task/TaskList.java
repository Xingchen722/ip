package lars.task;

/**
 * Represents a collection of tasks with operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int num;

    /**
     * Initializes an empty TaskList with a default capacity of 100.
     */
    public TaskList() {
        this.tasks = new Task[100];
        this.num = 0;
    }

    /**
     * Initializes a TaskList with an existing array of tasks.
     * @param tasks An array of Task objects.
     */
    public TaskList(Task[] tasks) {
        this.tasks = tasks;
        this.num = 0;
        for (Task t : tasks) {
            if (t != null) {
                this.num++;
            }
        }
    }

    /**
     * Adds a new task to the list and increments the count.
     * @param task The Task object to add.
     */
    public void addTask(Task task) {
        this.tasks[num] = task;
        this.num++;
    }

    /**
     * Removes a task from the list and shifts subsequent tasks to the left.
     * @param index The zero-based index of the task to delete.
     * @return The removed Task object.
     */
    public Task deleteTask(int index) {
        Task t = tasks[index];
        for (int i = index; i < num -1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[num - 1] = null;
        num--;
        return t;
    }

    /** @return The number of tasks currently in the list. */
    public int getSize() {
        return num;
    }

    /** @return The Task at the specified index. */
    public Task getTask(int index) {
        return tasks[index];
    }

    /** @return All Task[]. */
    public Task[] getAllTasks() {
        return tasks;
    }

    public TaskList findTasks(String s) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < num; i++) {
            if (tasks[i].getTask().contains(s)) {
                matchingTasks.addTask(tasks[i]);
            }
        }
        return matchingTasks;
    }
}
