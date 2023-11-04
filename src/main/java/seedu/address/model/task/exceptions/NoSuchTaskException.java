package seedu.address.model.task.exceptions;

/**
 * Signals when the index does not point to a specific task in the tasklist
 */
public class NoSuchTaskException extends RuntimeException {
    public NoSuchTaskException(String e) {
        super(e);
    }
}
