package seedu.address.model.task;

import java.util.List;

import seedu.address.model.task.exceptions.NoSuchTaskException;


/**
 * Encapsulates logic of a TaskList
 */
public interface ReadOnlyTaskList {
    /**
     * Checks if index is between 0 and task list size.
     */
    boolean isValidIndex(int index);

    /**
     * Returns task list size.
     */
    int size();

    /**
     * Returns true if task list is empty.
     */
    boolean isEmpty();

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     * @throws NoSuchTaskException If the index is out of bounds.
     */
    Task getTask(int index) throws NoSuchTaskException;

    /**
     * Checks if the task list contains a duplicate of the specified task.
     *
     * @param t the task to be checked for duplication in the task list
     * @return true if a duplicate of the specified task is found in the task list, false otherwise
     */
    boolean contains(Task t);

    /**
     * Retrieves all tasks from the task list.
     *
     * @return A list of all tasks.
     */
    List<Task> getAllTasks();
}
