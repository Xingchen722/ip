package lars;

import lars.command.Command;
import lars.command.ExitCommand;
import lars.exceptions.LarsException;
import lars.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parser_bye() throws LarsException {
        // New structure: Parser.parse only accepts string instructions
        Command c = Parser.parse("bye");

        // 检查返回的是否是 ExitCommand，并且 isExit() 是否为 true
        assertTrue(c instanceof ExitCommand, "Bye command should return an instance of ExitCommand.");
        assertTrue(c.isExit(), "ExitCommand should return true for isExit().");
    }

    @Test
    public void parser_invalid() {
        // Check whether the unknown instruction throws an exception
        assertThrows(LarsException.class, () -> {
            Parser.parse("blahblah");
        }, "Undefined commands should throw LarsException.");
    }

    @Test
    public void parser_emptyTodo_exceptionThrown() {
        // Check whether an empty todo throws an exception
        assertThrows(LarsException.class, () -> {
            Parser.parse("todo");
        }, "Empty todo description should throw LarsException.");
    }
}