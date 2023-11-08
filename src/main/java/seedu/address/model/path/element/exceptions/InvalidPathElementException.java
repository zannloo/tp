package seedu.address.model.path.element.exceptions;

/**
 * An exception thrown when an invalid path element is encountered.
 */
public class InvalidPathElementException extends Exception {

    /**
     * Constructs an {@code InvalidPathElementException} with the specified detail message.
     *
     * @param message The message explaining the reason for the exception.
     */
    public InvalidPathElementException() {
        super("Invalid path element provided.");
    }
}
