package seedu.address.model.task;

import java.util.List;

import seedu.address.model.task.exceptions.NoSuchTaskException;

/**
 * API for task list manager
 */
public interface ITaskListManager extends ReadOnlyTaskList {
    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    void addTask(Task t);
    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    Task deleteTask(int index) throws NoSuchTaskException;
    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     * @return The marked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    Task markTask(int index) throws NoSuchTaskException;

    /**
     * Unmarks a task.
     *
     * @param index The number of the task to be unmarked.
     * @return The unmarked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    Task unmarkTask(int index) throws NoSuchTaskException;

    /**
     * Finds tasks that match the given query.
     *
     * @param query The query to match.
     * @return A list of tasks that match the query.
     * @throws NoSuchTaskException If there are no tasks at this level.
     */
    List<Task> findTask(String query) throws NoSuchTaskException;
}
