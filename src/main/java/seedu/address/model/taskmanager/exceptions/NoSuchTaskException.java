package seedu.address.model.taskmanager.exceptions;

/**
 * Signals when the index does not point to a specific task in the tasklist
 */
public class NoSuchTaskException extends Exception {
    public NoSuchTaskException(String e) {
        super(e);
    }
}
