package seedu.address.model.profbook.exceptions;

/**
 * Signals that the operation will result in duplicate Child (Children are considered duplicates if they have the same
 * id).
 */
public class DuplicateChildException extends RuntimeException {
    public DuplicateChildException(String e) {
        super("Operation would result in duplicate " + e);
    }
}
