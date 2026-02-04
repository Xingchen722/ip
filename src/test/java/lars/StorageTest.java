package lars;

import lars.Exceptions.LarsException;
import lars.storage.Storage;
import lars.task.Task;
import lars.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void StorageSaveTest() throws LarsException {
        Storage so = new Storage(tempDir.toString());

        Task[] tasks = new Task[1];
        tasks[0] = new Todo("read book");
        so.save(tasks, tasks.length);

        Task[] loadTask = so.load();
        assertNotNull(loadTask[0]);
        assertEquals("read book", loadTask[0].getTask());
    }
}
