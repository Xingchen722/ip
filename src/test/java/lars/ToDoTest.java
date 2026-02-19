package lars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import lars.task.Task;
import lars.task.TaskList;
import lars.task.Todo;

/**
 * Unit tests for TaskList behaviors using Todo tasks.
 */
public class ToDoTest {

    /**
     * Verifies addTask stores task and increments size.
     */
    @Test
    public void taskList_addTask_increasesSizeAndStoresTask() {
        TaskList tasks = new TaskList();
        Task t = new Todo("read book");

        tasks.addTask(t);

        assertEquals(1, tasks.getSize());
        assertSame(t, tasks.getTask(0));
    }

    /**
     * Verifies deleteTask removes task and shifts items.
     */
    @Test
    public void taskList_deleteTask_shiftsAndDecreasesSize() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("read book");
        Task t2 = new Todo("write notes");
        tasks.addTask(t1);
        tasks.addTask(t2);

        Task removed = tasks.deleteTask(0);

        assertSame(t1, removed);
        assertEquals(1, tasks.getSize());
        assertSame(t2, tasks.getTask(0));
    }

    /**
     * Verifies findTasks returns matching tasks only.
     */
    @Test
    public void taskList_findTasks_returnsMatchingTasks() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("read book");
        Task t2 = new Todo("write notes");
        Task t3 = new Todo("read papers");
        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        TaskList found = tasks.findTasks("read");

        assertEquals(2, found.getSize());
        assertSame(t1, found.getTask(0));
        assertSame(t3, found.getTask(1));
    }
}
