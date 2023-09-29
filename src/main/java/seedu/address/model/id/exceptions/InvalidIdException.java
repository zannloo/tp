package seedu.address.model.id.exceptions;

/**
 * The {@code InvalidIdException} class represents an exception that is thrown when an invalid ID is encountered.
 * It is used to indicate that an ID does not match the expected format or criteria.
 */
public class InvalidIdException extends Exception {
    /**
     * Constructs an {@code InvalidIdException} with the specified error message.
     *
     * @param message The error message describing the reason for the exception.
     */
    public InvalidIdException(String message) {
        super(message);
    }
}
