package lars.parser;
import java.time.LocalDate;

import lars.command.AddCommand;
import lars.command.Command;
import lars.command.DeleteCommand;
import lars.command.ExitCommand;
import lars.command.ListCommand;
import lars.command.MarkCommand;
import lars.exceptions.LarsException;
import lars.task.Deadline;
import lars.task.Event;
import lars.task.Todo;

/**
 * Interprets user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses user input strings into executable {@code Command} objects.
     * This class is responsible for interpreting raw text input
     * and converting it into the appropriate command type.
     */
    public static Command parse(String fullCommand) throws LarsException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "todo":
            if (parts.length < 2) {
                throw new LarsException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(parts[1]));

        case "deadline":
            if (parts.length < 2) {
                throw new LarsException("Deadline details are missing.");
            }
            String[] ddlParts = parts[1].split(" /by ");
            return new AddCommand(new Deadline(ddlParts[0], ddlParts[1]));

        case "event":
            if (parts.length < 2) {
                throw new LarsException("Event details are missing.");
            }
            String[] eventParts = parts[1].split(" /from ");
            String status = eventParts[0];
            String[] timeParts = eventParts[1].split(" /to ");
            return new AddCommand(
                    new Event(status, LocalDate.parse(timeParts[0]), LocalDate.parse(timeParts[1])));

        case "delete":
            if (parts.length < 2) {
                throw new LarsException("Please specify a task index to delete.");
            }
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);

        case "mark":
            return new MarkCommand(Integer.parseInt(parts[1]) - 1, true);

        case "unmark":
            return new MarkCommand(Integer.parseInt(parts[1]) - 1, false);

        case "bye":
            return new ExitCommand();
        default:
            throw new LarsException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
