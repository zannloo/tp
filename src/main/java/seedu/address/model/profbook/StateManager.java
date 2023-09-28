package seedu.address.model.profbook;

import java.util.List;

import seedu.address.model.profbook.exceptions.NoSuchChildException;

/**
 * Encapsulates logic manipulating data in ProfBook
 */
public class StateManager {

    private static final int GROUP_DEPTH = 1;

    private static final int STUDENT_DEPTH = 1;

    private final ProfBook profBook;

    public StateManager(ProfBook profBook) {
        this.profBook = profBook;
    }

    public Group getGroupFromPath(Path p) throws NoSuchChildException {
        if (p.size() != GROUP_DEPTH) {
            throw new NoSuchChildException(p.toString());
        }
        return this.profBook.getChild(p.getIdPath().get(0));
    }

    public Student getStudentFromPath(Path p) throws NoSuchChildException {
        if (p.size() != STUDENT_DEPTH) {
            throw new NoSuchChildException(p.toString());
        }
        List<Id> path = p.getIdPath();
        Group parent = this.profBook.getChild(path.get(0));
        return parent.getChild(path.get(1));
    }
}
