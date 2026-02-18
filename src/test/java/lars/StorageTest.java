package lars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lars.exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.Todo;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void storageSaveTest() throws LarsException {
        Storage so = new Storage(tempDir.toString());

        Task[] tasks = new Task[1];
        tasks[0] = new Todo("read book");
        so.save(tasks, tasks.length);

        Task[] loadTask = so.load();
        assertNotNull(loadTask[0]);
        assertEquals("read book", loadTask[0].getTask());
    }
}
