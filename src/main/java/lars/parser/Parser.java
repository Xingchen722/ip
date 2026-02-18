package lars.parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import lars.command.AddCommand;
import lars.command.Command;
import lars.command.DeleteCommand;
import lars.command.ExitCommand;
import lars.command.ListCommand;
import lars.command.MarkCommand;
import lars.command.RemindCommand;
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
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new LarsException("Please enter a command.");
        }
        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new LarsException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(parts[1].trim()));

        case "deadline":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new LarsException("Please provide a deadline description.");
            }
            String[] ddlParts = parts[1].split(" /by ", 2);
            if (ddlParts.length < 2 || ddlParts[0].trim().isEmpty() || ddlParts[1].trim().isEmpty()) {
                throw new LarsException("Please use: deadline <description> /by <yyyy-MM-dd or yyyy-MM-dd HHmm>.");
            }
            return new AddCommand(new Deadline(ddlParts[0].trim(), ddlParts[1].trim()));

        case "event":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new LarsException("Please provide an event description.");
            }
            String[] eventParts = parts[1].split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty()) {
                throw new LarsException("Please use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>.");
            }
            String status = eventParts[0].trim();
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new LarsException("Please use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>.");
            }
            LocalDate from;
            LocalDate to;
            try {
                from = LocalDate.parse(timeParts[0].trim());
                to = LocalDate.parse(timeParts[1].trim());
            } catch (DateTimeParseException e) {
                throw new LarsException("Event dates must be in yyyy-MM-dd format.");
            }
            if (from.isAfter(to)) {
                throw new LarsException("Event start date must be on or before the end date.");
            }
            return new AddCommand(new Event(status, from, to));

        case "delete":
            if (parts.length < 2) {
                throw new LarsException("Please specify a task index to delete.");
            }
            return new DeleteCommand(parseTaskIndex(parts[1]));

        case "mark":
            if (parts.length < 2) {
                throw new LarsException("Please specify a task index to mark.");
            }
            return new MarkCommand(parseTaskIndex(parts[1]), true);

        case "unmark":
            if (parts.length < 2) {
                throw new LarsException("Please specify a task index to unmark.");
            }
            return new MarkCommand(parseTaskIndex(parts[1]), false);

        case "bye":
            return new ExitCommand();

        case "remind":
            return new RemindCommand();
        default:
            throw new LarsException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseTaskIndex(String rawIndex) throws LarsException {
        String trimmed = rawIndex.trim();
        try {
            int index = Integer.parseInt(trimmed);
            if (index <= 0) {
                throw new LarsException("Please provide a valid task number (e.g., 1).");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new LarsException("Please provide a valid task number (e.g., 1).");
        }
    }
}
