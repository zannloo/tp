package seedu.address.model.profbook.exceptions;

/**
 * Signals when there is no such child present in current class
 */
public class NoSuchChildException extends RuntimeException {
    public NoSuchChildException(String e) {
        super("No such child found: " + e);
    }
}
