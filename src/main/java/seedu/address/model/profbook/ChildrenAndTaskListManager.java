package seedu.address.model.profbook;

import java.util.List;
import java.util.Map;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * A child element that is both Children and TaskList Manager.
 */
public abstract class ChildrenAndTaskListManager<T extends IChildElement> implements IChildElement {
    private ChildrenManager<T> childrenManager;
    private TaskListManager taskListManager;

    /**
     * Construct a new children and task list manager.
     */
    public ChildrenAndTaskListManager() {
        childrenManager = new ChildrenManager<>();
        taskListManager = new TaskListManager();
    }

    /**
     * Construct a new children and tasklist manager with given children and tasklist.
     */
    public ChildrenAndTaskListManager(Map<Id, T> children, TaskList taskList) {
        childrenManager = new ChildrenManager<>(children);
        taskListManager = new TaskListManager(taskList);
    }

    public ChildrenManager<T> getChildrenManger() {
        return childrenManager;
    }

    public TaskListManager getTaskListManager() {
        return taskListManager;
    }

    //=========== Children Manager ==================================================================================

    /**
     * Adds the child to list of children
     *
     * @param id    - Unique identifier of the child
     * @param child - The child in question
     * @throws DuplicateChildException If attempting to add child with the same ID
     */
    public void addChild(Id id, T child) throws DuplicateChildException {
        childrenManager.addChild(id, child);
    }

    /**
     * Deletes the child specified by the id
     *
     * @param id - Unique identifier of the child
     * @return The deleted Child
     * @throws NoSuchChildException If there is no such Child found
     */
    public T deleteChild(Id id) throws NoSuchChildException {
        return childrenManager.deleteChild(id);
    }

    /**
     * Checks if the child is present
     *
     * @param id - Unique identifier of the child
     * @return true if the child is present
     */
    public boolean hasChild(Id id) {
        return childrenManager.hasChild(id);
    }

    /**
     * Returns the child specified by the id
     *
     * @param id - Unique identifier of the child
     * @return The specified Child
     * @throws NoSuchChildException If there is no such Child found
     */
    public T getChild(Id id) throws NoSuchChildException {
        return childrenManager.getChild(id);
    }

    /**
     * Returns Number of current children
     *
     * @return The Number of current children
     */
    public int numOfChildren() {
        return childrenManager.numOfChildren();
    }

    /**
     * Returns a list of all current children
     *
     * @return list of all current children
     */
    public List<T> getAllChildren() {
        return childrenManager.getAllChildren();
    }

    public Map<Id, T> getChildren() {
        return childrenManager.getChildren();
    }

    //=========== TaskList Manager ==================================================================================

    /**
     * Adds a new tasks to the task list
     *
     * @param t
     */
    public void addTask(Task t) {
        taskListManager.addTask(t);
    }

    /**
     * Deletes the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The deleted class
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task deleteTask(int index) throws NoSuchTaskException {
        return taskListManager.deleteTask(index);
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param index - The index of the targeted class
     * @return The marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task markTask(int index) throws NoSuchTaskException {
        return taskListManager.markTask(index);
    }

    /**
     * Marks the task at the specified index as not completed
     *
     * @param index - The index of the targeted class
     * @return The un-marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task unmarkTask(int index) throws NoSuchTaskException {
        return taskListManager.unmarkTask(index);
    }

    /**
     * Finds all matching task, compares by the task's description
     *
     * @param query - The String to match
     * @return A list of all matching Tasks
     */
    public List<Task> findTask(String query) throws NoSuchTaskException {
        return taskListManager.findTask(query);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The specified task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task getTask(int index) throws NoSuchTaskException {
        return taskListManager.getTask(index);
    }

    /**
     * Returns all current task
     *
     * @return A list of all Tasks
     */
    public List<Task> getAllTask() {
        return taskListManager.getAllTask();
    }

    public boolean checkDuplicates(Task t) {
        return taskListManager.checkDuplicates(t);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChildrenAndTaskListManager<?>)) {
            return false;
        }

        ChildrenAndTaskListManager<?> otherChildrenAndTaskListManager = (ChildrenAndTaskListManager<?>) other;
        return this.childrenManager.equals(otherChildrenAndTaskListManager.childrenManager)
                && this.taskListManager.equals(otherChildrenAndTaskListManager.taskListManager);
    }
}
