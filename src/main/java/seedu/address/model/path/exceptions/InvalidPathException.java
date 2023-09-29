package seedu.address.model.path.exceptions;

/**
 * An exception thrown when an invalid path is encountered.
 */
public class InvalidPathException extends Exception {
    /**
     * Constructs an {@code InvalidPathException} with a message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public InvalidPathException(String message) {
        super(message);
    }
}
