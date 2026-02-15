package lars.exceptions;

/**
 * Represents exceptions specific to the LARS application.
 * Used to indicate errors in user commands or task operations.
 */
public class LarsException extends Exception {

    /**
     * Constructs a new LarsException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public LarsException(String message) {
        super(message);
    }
}
