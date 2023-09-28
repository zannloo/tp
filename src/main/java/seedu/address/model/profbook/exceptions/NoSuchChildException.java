package seedu.address.model.profbook;

import seedu.address.model.taskmanager.NoSuchTaskException;

public class NoSuchChildException extends RuntimeException{
    public NoSuchChildException(String e) {
        super("No such child found: " + e);
    }
}
