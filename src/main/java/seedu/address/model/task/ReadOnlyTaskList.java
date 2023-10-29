package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.task.exceptions.NoSuchTaskException;


/**
 * Encapsulates logic of a TaskList
 */
public class ReadOnlyTaskList {

    protected final List<Task> taskList;

    /**
     * Constructs a new {@code ReadOnlyTaskList}.
     */
    public ReadOnlyTaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code ReadOnlyTaskList} using the given task list.
     *
     * @param taskList A valid task list.
     */
    public ReadOnlyTaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Contructs a {@code ReadOnlyTaskList} using the data in {@code toBeCopied}.
     */
    public ReadOnlyTaskList(ReadOnlyTaskList toBeCopied) {
        this(toBeCopied.taskList);
    }

    /**
     * Checks if index is between 0 and task list size.
     */
    public boolean isValidIndex(int index) {
        return index > 0 && index <= taskList.size();
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

    /**
     * Returns task list size.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns true if task list is empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     * @throws NoSuchTaskException If the index is out of bounds.
     */
    public Task getTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        return task;
    }

    /**
     * Checks if the task list contains a duplicate of the specified task.
     *
     * @param t the task to be checked for duplication in the task list
     * @return true if a duplicate of the specified task is found in the task list, false otherwise
     */
    public boolean contains(Task t) {
        for (Task check : this.taskList) {
            if (check.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReadOnlyTaskList taskList1 = (ReadOnlyTaskList) o;
        return this.taskList.equals(taskList1.taskList);
    }

    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
