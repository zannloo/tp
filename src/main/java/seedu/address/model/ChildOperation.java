package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.ChildAndTaskListManager;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.IChildManager;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.task.ITaskListManager;
import seedu.address.model.task.Task;

/**
 * Encapsulates the logic to perform a generic child operation for child manager
 *
 * @param <T> The type of child that is required
 */
public class ChildOperation<T extends IChildElement<T>> implements IChildOperation<T> {
    public static final String MESSAGE_ALL_CHILDREN_MUST_BE_TASK_LIST_MANAGER =
            "All children must be task list manager.";
    public static final String MESSAGE_INVALID_LEVEL = "Invalid level.";

    private final IChildManager<T> baseDir;

    private final Logger logger = LogsCenter.getLogger(ChildOperation.class);

    public ChildOperation(IChildManager<T> baseDir) {
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
     */
    @Override
    public void updateChild(Id id, T child) {
        this.baseDir.deleteChild(id);
        this.baseDir.addChild(id, child);
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
    public boolean checkIfAllChildrenHaveTask(Task task, int level) {
        List<IChildElement<?>> children = getAllTaskListManagerChildrenAtLevel(level);

        for (IChildElement<?> child : children) {

            //Defensive programming - check if getAllTaskListManagerChildrenAtLevel works as expected
            if (!(child instanceof ITaskListManager)) {
                throw new IllegalArgumentException(MESSAGE_ALL_CHILDREN_MUST_BE_TASK_LIST_MANAGER);
            }

            // Type casting is safe as we checked earlier
            ITaskListManager taskListManager = (ITaskListManager) child;
            if (!taskListManager.contains(task)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkIfAnyChildHasTask(Task task, int level) {
        List<IChildElement<?>> children = getAllTaskListManagerChildrenAtLevel(level);

        for (IChildElement<?> child : children) {

            //Defensive programming - check if getAllTaskListManagerChildrenAtLevel works as expected
            if (!(child instanceof ITaskListManager)) {
                throw new IllegalArgumentException(MESSAGE_ALL_CHILDREN_MUST_BE_TASK_LIST_MANAGER);
            }

            // Type casting is safe as we checked earlier
            ITaskListManager taskListManager = (ITaskListManager) child;
            if (taskListManager.contains(task)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addTaskToAllChildren(Task task, int level) {
        List<IChildElement<?>> children = getAllTaskListManagerChildrenAtLevel(level);

        for (IChildElement<?> child : children) {
            Task clonedTask = task.clone();

            //Defensive programming - check if getAllTaskListManagerChildrenAtLevel works as expected
            if (!(child instanceof ITaskListManager)) {
                throw new IllegalArgumentException(MESSAGE_ALL_CHILDREN_MUST_BE_TASK_LIST_MANAGER);
            }

            // Type casting is safe as we checked earlier
            ITaskListManager taskListManager = (ITaskListManager) child;
            if (taskListManager.contains(task)) {
                continue;
            }
            taskListManager.addTask(clonedTask);
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

    private List<IChildElement<?>> getAllTaskListManagerChildrenAtLevel(int level) {
        List<IChildElement<?>> children = new ArrayList<>(getAllChildren());
        while (--level > 0) {
            List<IChildElement<?>> list = new ArrayList<>();

            for (IChildElement<?> child : children) {

                if (child instanceof ChildAndTaskListManager<?, ?>) { // If child is a group directory
                    ChildAndTaskListManager<?, ?> childrenAndTaskListManager =
                            (ChildAndTaskListManager<?, ?>) child; // type casting is safe as we checked earlier
                    list.addAll(childrenAndTaskListManager.getAllChildren());
                } else { // If child is a student directory
                    throw new IllegalArgumentException(MESSAGE_INVALID_LEVEL); // Student does not have any child
                }
            }
            children = new ArrayList<>(list);
            level--;
        }
        return children;
    }
}
