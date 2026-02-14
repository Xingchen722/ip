package lars;

import lars.exceptions.LarsException;
import lars.parser.Parser;
import lars.storage.Storage;
import lars.task.TaskList;
import lars.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parser_bye() throws LarsException {
        Storage so = new Storage("./data/test.txt");
        TaskList tl = new TaskList();
        Ui ui = new Ui() {
            @Override
            public String readCommand() {
                return "bye";
            }
            @Override
            public void showBye() {
            }
        };
        boolean isExit = Parser.parse(so, tl, ui);
        assertTrue(isExit, "Bye command should return true to exit the loop.");
    }

    @Test
    public void parser_invalid() throws LarsException {
        Storage so = new Storage("./data/test.txt");
        TaskList tl = new TaskList();
        Ui ui = new Ui() {
            @Override
            public String readCommand() {
                return "blahblah";
            }
        };

        assertThrows(LarsException.class, () -> {
            Parser.parse(so, tl, ui);
        }, "Undefined commands should throw LarsException.");
    }

    @Test
    public void parser_emptyTodo_exceptionThrown() {
        Storage so = new Storage("./data/test.txt");
        TaskList tl = new TaskList();
        Ui ui = new Ui() {
            @Override
            public String readCommand() {
                return "todo";
            }
        };
        assertThrows(LarsException.class, () -> {
            Parser.parse(so, tl, ui);
        }, "Empty todo description should throw LarsException.");
    }
}


