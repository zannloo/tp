package seedu.address.model.path.exceptions;

/**
 * The {@code UnsupportedPathOperationException} class represents an
 * exception that is thrown when an operation is not supported or is
 * invalid based on the state of a path directory.
 */
public class UnsupportedPathOperationException extends Exception {
    /**
     * Constructs an {@code UnsupportedPathOperationException} with the specified error message.
     *
     * @param message The error message describing why the operation is not supported or invalid.
     */
    public UnsupportedPathOperationException(String message) {
        super(message);
    }
}
