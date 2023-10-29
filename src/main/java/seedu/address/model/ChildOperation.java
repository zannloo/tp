package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.ChildrenAndTaskListManager;
import seedu.address.model.profbook.ChildrenManager;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.TaskListManager;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.taskmanager.Task;

/**
 * Encapsulates the logic to perform a generic child operation for child manager
 *
 * @param <T> The type of child that is required
 */
public class ChildOperation<T extends IChildElement> implements IChildOperation<T> {

    private final ChildrenManager<T> baseDir;

    private final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    public ChildOperation(ChildrenManager<T> baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * Adds the child to list of children
     *
     * @param id    - Unique identifier of the child
     * @param child - The child in question
     * @throws DuplicateChildException If attempting to add child with the same ID
     */
    @Override
    public void addChild(Id id, T child) throws DuplicateChildException {
        this.baseDir.addChild(id, child);
    }

    /**
     * Checks if the child is present
     *
     * @param id - Unique identifier of the child
     * @return true if child is present
     */
    @Override
    public boolean hasChild(Id id) {
        return this.baseDir.hasChild(id);
    }

    /**
     * Deletes the child specified by the id
     *
     * @param id - Unique identifier of the child
     * @return The deleted Child
     * @throws NoSuchChildException If there is no such Child found
     */
    @Override
    public T deleteChild(Id id) throws NoSuchChildException {
        this.logger.info("deleting" + id.toString());
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
    public T getChild(Id id) throws NoSuchChildException {
        this.logger.info("getting" + id.toString());
        return this.baseDir.getChild(id);
    }

    /**
     * Updates the child with a new child of the same id
     *
     * @param id    - Unique identifier of the child
     * @param child - The new child to replace old child
     * @throws NoSuchChildException If there is no such Child found
     */
    @Override
    public void updateChild(Id id, T child) throws NoSuchChildException {
        this.baseDir.deleteChild(id);
        try {
            this.baseDir.addChild(id, child);
        } catch (DuplicateChildException e) {
            this.logger.warning("In updateChild, unexpected duplicate error");
            throw new RuntimeException("ERROR: Code should not reach here");
        }
    }

    /**
     * Returns a list of all current children
     *
     * @return List of all current children
     */
    @Override
    public List<T> getAllChildren() {
        this.logger.info("getting all child");
        return new ArrayList<>(this.baseDir.getAllChildren());
    }

    /**
     * Returns Number of current children
     *
     * @return The Number of current children
     */
    @Override
    public int numOfChildren() {
        return this.baseDir.numOfChildren();
    }

    @Override
    public void addTaskToAllChildren(Task task, int level) {
        List<IChildElement> children = new ArrayList<>(getAllChildren());
        level--;

        while (level > 0) {
            List<IChildElement> list = new ArrayList<>();
            for (IChildElement child : children) {
                if (child instanceof ChildrenAndTaskListManager) {
                    ChildrenAndTaskListManager<?> ctlm = (ChildrenAndTaskListManager<?>) child;
                    list.addAll(ctlm.getAllChildren());
                }
            }
            children = new ArrayList<>(list);
            level--;
        }

        addAllTaskToAllChildren(task, children);
    }

    private void addAllTaskToAllChildren(Task task, List<? extends IChildElement> children) {
        for (IChildElement child : children) {
            Task clonedTask = task.clone();
            if (child instanceof TaskListManager) {
                TaskListManager tlm = (TaskListManager) child;
                tlm.addTask(clonedTask);
            } else if (child instanceof ChildrenAndTaskListManager) {
                ChildrenAndTaskListManager<?> ctlm = (ChildrenAndTaskListManager<?>) child;
                ctlm.addTask(clonedTask);
            } else {
                throw new IllegalArgumentException("All children must be task list manager.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChildOperation<?> that = (ChildOperation<?>) o;
        return Objects.equals(baseDir, that.baseDir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseDir);
    }
}
