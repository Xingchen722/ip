package lars;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lars.command.Command;
import lars.command.ExitCommand;
import lars.exceptions.LarsException;
import lars.parser.Parser;

public class ParserTest {

    @Test
    public void parser_bye() throws LarsException {
        Command c = Parser.parse("bye");

        assertTrue(c instanceof ExitCommand, "Bye command should return an instance of ExitCommand.");
        assertTrue(c.isExit(), "ExitCommand should return true for isExit().");
    }

    @Test
    public void parser_invalid() {
        assertThrows(LarsException.class, () -> {
            Parser.parse("blahblah");
        }, "Undefined commands should throw LarsException.");
    }

    @Test
    public void parser_emptyTodo_exceptionThrown() {
        assertThrows(LarsException.class, () -> {
            Parser.parse("todo");
        }, "Empty todo description should throw LarsException.");
    }
}
