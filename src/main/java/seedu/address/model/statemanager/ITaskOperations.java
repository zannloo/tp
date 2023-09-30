package seedu.address.model.statemanager;

import java.util.List;

import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;
import seedu.address.model.taskmanager.Task;

/**
 * Interface for classes that operations that involve a task list, ensures that all basic functions are present to
 * interact with TaskListManager instance
 */
public interface ITaskOperations {

    /**
     * Adds a new tasks to the task list
     *
     * @param t
     */
    void addTask(Task t);

    /**
     * Deletes the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The deleted class
     * @throws NoSuchTaskException if no task can be found by the index
     */
    Task deleteTask(int index) throws NoSuchTaskException;

    /**
     * Marks the task at the specified index as completed
     *
     * @param index - The index of the targeted class
     * @return The marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    Task markTask(int index) throws NoSuchTaskException;

    /**
     * Marks the task at the specified index as not completed
     *
     * @param index - The index of the targeted class
     * @return The un-marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    Task unmarkTask(int index) throws NoSuchTaskException;

    /**
     * Finds all matching task, compares by the task's description
     *
     * @param query - The String to match
     * @return A list of all matching Tasks
     */
    List<Task> findTask(String query);

    /**
     * Returns the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The specified task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    Task getTask(int index) throws NoSuchTaskException;

    /**
     * Returns all current task
     *
     * @return A list of all Tasks
     */
    List<Task> getAllTask();

}
