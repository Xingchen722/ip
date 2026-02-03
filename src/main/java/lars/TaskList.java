package lars;

import lars.task.Task;

public class TaskList {
    private Task[] tasks;
    private int num;

    public TaskList() {
        this.tasks = new Task[100];
        this.num = 0;
    }

    public TaskList(Task[] tasks) {
        this.tasks = tasks;
        this.num = 0;
        for (Task t : tasks) {
            if (t != null) {
                this.num++;
            }
        }
    }

    public void addTask(Task task) {
        this.tasks[num] = task;
        this.num++;
    }

    public Task deleteTask(int index) {
        Task t = tasks[index];
        for (int i = index; i < num -1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[num - 1] = null;
        num--;
        return t;
    }

    public int getSize() { return num; }
    public Task getTask(int index) { return tasks[index]; }
    public Task[] getAllTasks() { return tasks; }

}
