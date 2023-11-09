//@@author mingyuanc
package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.exceptions.NoSuchTaskException;


/**
 * Encapsulate the logic of a prof book model, mainly the need for a task list
 * at every level
 */
public class TaskListManager implements ITaskListManager {
    protected final List<Task> taskList;

    /**
     * Constructs a new task list manager
     */
    public TaskListManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskListManager} using {@code taskList}.
     * @param taskList
     */
    public TaskListManager(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Constructs a new {@code TaskListManager} with the data in {@code toBeCopied}.
     * @param toBeCopied
     */
    public TaskListManager(ReadOnlyTaskList toBeCopied) {
        this.taskList = new ArrayList<>(toBeCopied.getAllTasks());
    }

    @Override
    public void addTask(Task t) {
        int initialSize = this.taskList.size();
        taskList.add(t);
        assert this.taskList.size() == initialSize + 1 : "Task Deadline should be added to the list";
    }

    @Override
    public Task deleteTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        int initialSize = this.taskList.size();
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        assert this.taskList.size() == initialSize - 1 : "Task should be removed from the list";
        return task;
    }

    @Override
    public Task markTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        Task markedTask = task.mark();
        this.taskList.set(index - 1, markedTask);
        return markedTask;
    }

    @Override
    public Task unmarkTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        Task unmarkedTask = task.unmark();
        this.taskList.set(index - 1, unmarkedTask);
        return unmarkedTask;
    }

    @Override
    public List<Task> findTask(String query) throws NoSuchTaskException {
        if (isEmpty()) {
            throw new NoSuchTaskException("No task in the task list.");
        }
        List<Task> list = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.description.contains(query)) {
                list.add(task);
            }
        }
        return list;
    }

    @Override
    public boolean isValidIndex(int index) {
        return index > 0 && index <= taskList.size();
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public Task getTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        return task;
    }

    @Override
    public boolean contains(Task t) {
        for (Task check : this.taskList) {
            if (check.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskList);
    }

    /**
     * Checks if index is vaild.
     * @throws NoSuchTaskException if index given is invalid.
     */
    protected void verifyIsValidIndex(int index) throws NoSuchTaskException {
        if (!isValidIndex(index)) {
            throw new NoSuchTaskException("Index " + index
                    + " is invalid for task list with size " + size());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Task List", taskList)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskListManager)) {
            return false;
        }

        TaskListManager otherTaskListManager = (TaskListManager) other;
        return this.taskList.equals(otherTaskListManager.taskList);
    }
}
