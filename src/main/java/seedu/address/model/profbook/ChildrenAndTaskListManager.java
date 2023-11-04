package seedu.address.model.profbook;

import java.util.List;
import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.task.ITaskListManager;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;
import seedu.address.model.task.exceptions.NoSuchTaskException;

/**
 * A child element that is both Children and TaskList Manager.
 */
public abstract class ChildrenAndTaskListManager<R, T extends IChildElement<T>>
        implements IChildElement<R>, IChildrenManager<T>, ITaskListManager {
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
    public ChildrenAndTaskListManager(Map<Id, T> children, ReadOnlyTaskList taskList) {
        childrenManager = new ChildrenManager<>(children);
        taskListManager = new TaskListManager(taskList.getAllTasks());
    }

    /**
     * Construcst a {@code ChildrenAndTaskListManager} with the data in {@code toBeCopied}.
     */
    public ChildrenAndTaskListManager(ChildrenAndTaskListManager<R, T> toBeCopied) {
        this.childrenManager = new ChildrenManager<>(toBeCopied.childrenManager);
        this.taskListManager = new TaskListManager(toBeCopied.taskListManager);
    }

    public ChildrenManager<T> getChildrenManger() {
        return childrenManager;
    }

    public TaskListManager getTaskListManager() {
        return taskListManager;
    }

    //=========== Children Manager ==================================================================================

    @Override
    public void addChild(Id id, T child) throws DuplicateChildException {
        childrenManager.addChild(id, child);
    }

    @Override
    public T deleteChild(Id id) throws NoSuchChildException {
        return childrenManager.deleteChild(id);
    }

    @Override
    public boolean hasChild(Id id) {
        return childrenManager.hasChild(id);
    }

    @Override
    public T getChild(Id id) throws NoSuchChildException {
        return childrenManager.getChild(id);
    }

    @Override
    public int numOfChildren() {
        return childrenManager.numOfChildren();
    }

    @Override
    public List<T> getAllChildren() {
        return childrenManager.getAllChildren();
    }

    @Override
    public Map<Id, T> getChildren() {
        return childrenManager.getChildren();
    }

    //=========== TaskList Manager ==================================================================================

    @Override
    public void addTask(Task t) {
        taskListManager.addTask(t);
    }

    @Override
    public Task deleteTask(int index) throws NoSuchTaskException {
        return taskListManager.deleteTask(index);
    }

    @Override
    public Task markTask(int index) throws NoSuchTaskException {
        return taskListManager.markTask(index);
    }

    @Override
    public Task unmarkTask(int index) throws NoSuchTaskException {
        return taskListManager.unmarkTask(index);
    }

    @Override
    public List<Task> findTask(String query) throws NoSuchTaskException {
        return taskListManager.findTask(query);
    }

    @Override
    public boolean isValidIndex(int index) {
        return taskListManager.isValidIndex(index);
    }

    @Override
    public int size() {
        return taskListManager.size();
    }

    @Override
    public boolean isEmpty() {
        return taskListManager.isEmpty();
    }

    @Override
    public Task getTask(int index) throws NoSuchTaskException {
        return taskListManager.getTask(index);
    }

    @Override
    public boolean contains(Task t) {
        return taskListManager.contains(t);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskListManager.getAllTasks();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Task List", taskListManager)
                .add("Children List", childrenManager)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChildrenAndTaskListManager<?, ?>)) {
            return false;
        }

        ChildrenAndTaskListManager<?, ?> otherChildrenAndTaskListManager = (ChildrenAndTaskListManager<?, ?>) other;
        return this.childrenManager.equals(otherChildrenAndTaskListManager.childrenManager)
                && this.taskListManager.equals(otherChildrenAndTaskListManager.taskListManager);
    }
}
