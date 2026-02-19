package lars;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import lars.command.Command;
import lars.command.ExitCommand;
import lars.exceptions.LarsException;
import lars.parser.Parser;

/**
 * Unit tests for Parser command parsing.
 */
public class ParserTest {

    /**
     * Verifies "bye" returns an ExitCommand.
     */
    @Test
    public void parser_bye() throws LarsException {
        Command c = Parser.parse("bye");

        assertTrue(c instanceof ExitCommand, "Bye command should return an instance of ExitCommand.");
        assertTrue(c.isExit(), "ExitCommand should return true for isExit().");
    }
    
    /**
     * Verifies unknown commands throw LarsException.
     */
    @Test
    public void parser_invalid() {
        assertThrows(LarsException.class, () -> {
            Parser.parse("blahblah");
        }, "Undefined commands should throw LarsException.");
    }

    /**
     * Verifies empty todo description throws LarsException.
     */
    @Test
    public void parser_emptyTodo_exceptionThrown() {
        assertThrows(LarsException.class, () -> {
            Parser.parse("todo");
        }, "Empty todo description should throw LarsException.");
    }

    /**
     * Verifies deadline without /by throws LarsException.
     */
    @Test
    public void parser_deadlineMissingBy_exceptionThrown() {
        assertThrows(LarsException.class, () -> {
            Parser.parse("deadline submit report");
        }, "Deadline missing /by should throw LarsException.");
    }
}
