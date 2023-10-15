package seedu.address.model.statemanager;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.profbook.TaskListManager;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * Encapsulates the required logic for task operation
 */
public class TaskOperation implements ITaskOperations {

    private final TaskListManager baseDir;
    private final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    public TaskOperation(TaskListManager baseDir) {
        this.baseDir = baseDir;
    }

    void stateLogger(String log) {
        this.logger.info(log);
    }

    void stateErrorLogger(String errMsg) {
        this.logger.severe(errMsg);
    }

    /**
     * Checks if current task is present
     *
     * @param t the task being checked for
     */
    @Override
    public boolean hasTask(Task t) {
        return this.baseDir.checkDuplicates(t);
    }

    /**
     * Adds a new tasks to the task list
     *
     * @param task the task being added
     */
    @Override
    public void addTask(Task task) {
        this.baseDir.addTask(task);
        this.stateLogger("Adding" + task.toString());
    }

    /**
     * Deletes the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The deleted class
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task deleteTask(int index) throws NoSuchTaskException {
        this.stateLogger("deleting " + index);
        return this.baseDir.deleteTask(index);
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param index - The index of the targeted class
     * @return The marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task markTask(int index) throws NoSuchTaskException {
        this.stateLogger("marking " + index);
        return this.baseDir.markTask(index);
    }

    /**
     * Marks the task at the specified index as not completed
     *
     * @param index - The index of the targeted class
     * @return The un-marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task unmarkTask(int index) throws NoSuchTaskException {
        this.stateLogger("un marking " + index);
        return this.baseDir.unmarkTask(index);
    }

    /**
     * Finds all matching task, compares by the task's description
     *
     * @param query - The String to match
     * @return A list of all matching Tasks
     */
    @Override
    public List<Task> findTask(String query) throws NoSuchTaskException {
        this.stateLogger("finding " + query);
        return this.baseDir.findTask(query);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The specified task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task getTask(int index) throws NoSuchTaskException {
        this.stateLogger("getting " + index);
        return this.baseDir.getTask(index);
    }

    /**
     * Returns all current task
     *
     * @return A list of all Tasks
     */
    @Override
    public List<Task> getAllTasks() {
        this.stateLogger("getting all ");
        return this.baseDir.getAllTask();
    }

    @Override
    public String toString() {
        return this.baseDir.toString();
    }
}
