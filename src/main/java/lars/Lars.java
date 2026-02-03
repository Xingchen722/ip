package lars;

import lars.Exceptions.LarsException;
import lars.parser.Parser;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;

public class Lars {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lars(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            //storage.load()用来得到数组
            tasks = new TaskList(storage.load());
        } catch (LarsException e) {
            ui.readError("Failed to load tasks." + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                isExit = Parser.parse(storage, tasks, ui);
            } catch (LarsException e) {
                ui.readError(e.getMessage());
            } catch (Exception e) {
                ui.readError("An unexpected error occurred: " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Lars("./data").run();
    }
}
