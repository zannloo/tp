package seedu.address.model.profbook;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
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
     * Constructs a task list manager with task list given
     */
    public TaskListManager(TaskList taskList) {
        requireAllNonNull(taskList);
        this.taskList = taskList;
    }

    /**
     * Constructs a new task list manager
     */
    public TaskListManager() {
        taskList = new TaskList(new ArrayList<>());
    }

    /**
     * Check if index is between 0 and task list size.
     */
    public boolean isValidIndex(int index) {
        return index > 0 && index <= taskList.size();
    }

    /**
     * Return task list size.
     */
    public int getTaskListSize() {
        return taskList.size();
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
        return this.taskList.unmark(index);
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
    public List<Task> getAllTask() {
        if (taskList.size() == 0) {
            return new ArrayList<>();
        }
        return this.taskList.getAllTask();
    }

    public boolean checkDuplicates(Task t) {
        return this.taskList.containsDuplicates(t);
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
