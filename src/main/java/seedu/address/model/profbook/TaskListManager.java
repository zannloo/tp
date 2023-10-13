package seedu.address.model.profbook;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;


/**
 * Encapsulate the logic of a prof book model, mainly the need for a task list
 * at every level
 */
public class TaskListManager {
    /**
     * Task list instance of this class
     */
    private final TaskList taskList;


    /**
     * Constructs a fresh model with tasks loaded from storage
     *
     * @param taskList - prefilled task list from storage
     */
    public TaskListManager(TaskList taskList) {
        requireAllNonNull(taskList);
        this.taskList = taskList;
    }

    /**
     * Adds a new tasks to the task list
     *
     * @param t
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The deleted class
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task deleteTask(int index) throws NoSuchTaskException {
        return this.taskList.delete(index);
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param index - The index of the targeted class
     * @return The marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task markTask(int index) throws NoSuchTaskException {
        return this.taskList.mark(index);
    }

    /**
     * Marks the task at the specified index as not completed
     *
     * @param index - The index of the targeted class
     * @return The un-marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task unmarkTask(int index) throws NoSuchTaskException {
        return this.taskList.mark(index);
    }

    /**
     * Finds all matching task, compares by the task's description
     *
     * @param query - The String to match
     * @return A list of all matching Tasks
     */
    public List<Task> findTask(String query) throws NoSuchTaskException {
        return this.taskList.find(query);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The specified task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    public Task getTask(int index) throws NoSuchTaskException {
        return this.taskList.get(index);
    }

    /**
     * Returns all current task
     *
     * @return A list of all Tasks
     */
    public List<Task> getAllTask() throws NoSuchTaskException {
        return this.taskList.getAllTask();
    }

    public boolean checkDuplicates(Task t) {
        return this.taskList.containsDuplicates(t);
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
