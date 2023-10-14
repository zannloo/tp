package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.profbook.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyProfBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();
}
