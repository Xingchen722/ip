package lars.task;

import java.time.LocalDate;
import java.util.Arrays;

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
        assert index >= 0 && index < num : "Index out of bounds: " + index;
        Task t = tasks[index];
        for (int i = index; i < num - 1; i++) {
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
        assert index >= 0 && index < num : "Index out of bounds: " + index;
        return tasks[index];
    }

    /** @return All Task[]. */
    public Task[] getAllTasks() {
        return tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword.
     * @param s The keyword to search for in task descriptions.
     * @return A new {@code TaskList} containing all matching tasks.
     */
    public TaskList findTasks(String s) {
        assert s != null : "Task list should not be null";
        return new TaskList(
                Arrays.stream(tasks, 0, num)
                        .filter(t -> t != null && t.getTask().contains(s))
                        .toArray(Task[]::new)
        );
    }

    /**
     * Generates a reminder string listing all overdue and upcoming tasks.
     *
     * <p>Overdue tasks are those with a date before today.
     * Upcoming tasks are those due within the next 3 days (including today).</p>
     *
     * <p>Completed tasks (marked done) are ignored.</p>
     *
     * <p>The returned string is formatted as follows:</p>
     * <ul>
     *   <li>If no tasks are due, returns a friendly message: "Hi, there is no tasks due recently 👏"</li>
     *   <li>If there are overdue tasks, they are listed under "🚨 Urgent (Overdue):"</li>
     *   <li>If there are upcoming tasks, they are listed under "❗Upcoming (Next 3 days):"</li>
     * </ul>
     *
     * <p>Each task is numbered in the list for readability.</p>
     *
     * @return a formatted reminder string including overdue and upcoming tasks
     */
    public String getReminder() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(3);

        String upcomingTasks = "";
        String overdueTasks = "";
        int upcomingCount = 1;
        int overdueCount = 1;

        for (int i = 0; i < num; i++) {
            Task t = tasks[i];

            if (t == null || t.getStatus()) {
                continue;
            }

            LocalDate taskDate = t.getDate();
            if (taskDate != null) {
                if (taskDate.isBefore(today)) {
                    overdueTasks = overdueTasks + overdueCount + ". " + t + "\n";
                    overdueCount++;
                }
                if (!taskDate.isBefore(today) && !taskDate.isAfter(limit)) {
                    upcomingTasks = upcomingTasks + upcomingCount + ". " + t + "\n";
                    upcomingCount++;
                }
            }
        }
        String finalResponse = "Hi, Here is your Reminder: \n\n";
        if (overdueCount == 1 && upcomingCount == 1) {
            return "Hi, there is no tasks due recently 👏";
        }
        if (overdueCount > 1) {
            finalResponse += "🚨 Urgent (Overdue):\n" + overdueTasks + "\n";
        }

        if (upcomingCount > 1) {
            finalResponse += "❗Upcoming (Next 3 days):\n" + upcomingTasks;
        }
        return finalResponse;
    }
}
