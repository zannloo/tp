package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.profbook.Student;

public interface ReadOnlyProfBookGrp {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();
}
