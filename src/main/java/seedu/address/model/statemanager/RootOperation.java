package seedu.address.model.statemanager;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;

public class RootOperation extends StateManager implements IChildOperation<Group> {

    private static final String LOGGING_PREFIX = "In Root Operations, ";

    private final Root baseDir;

    RootOperation(Root root) {
        super(root);
        this.baseDir = root;
        this.stateLogger(RootOperation.LOGGING_PREFIX);
    }

    /**
     * Adds the child to list of children
     *
     * @param id    - Unique identifier of the child
     * @param child - The child in question
     * @throws DuplicateChildException If attempting to add child with the same ID
     */
    @Override
    public void addChild(Id id, Group child) throws DuplicateChildException {
        this.stateLogger(RootOperation.LOGGING_PREFIX + "adding" + child.toString());
        this.baseDir.addChild(id, child);
    }

    /**
     * Deletes the child specified by the id
     *
     * @param id - Unique identifier of the child
     * @return The deleted Child
     * @throws NoSuchChildException If there is no such Child found
     */
    @Override
    public Group deleteChild(Id id) throws NoSuchChildException {
        this.stateLogger(RootOperation.LOGGING_PREFIX + "Deleting" + id.toString());
        return this.baseDir.deleteChild(id);
    }

    /**
     * Returns the child specified by the id
     *
     * @param id - Unique identifier of the child
     * @return The specified Child
     * @throws NoSuchChildException If there is no such Child found
     */
    @Override
    public Group getChild(Id id) throws NoSuchChildException {
        this.stateLogger(RootOperation.LOGGING_PREFIX + "getting" + id.toString());
        return this.baseDir.getChild(id);
    }

    /**
     * Returns a list of all current children
     *
     * @return list of all current children
     */
    @Override
    public Group[] getAllChildren() {
        this.stateLogger(RootOperation.LOGGING_PREFIX + "getting all children");
        return this.baseDir.getAllChildren().toArray(new Group[0]);
    }
}
