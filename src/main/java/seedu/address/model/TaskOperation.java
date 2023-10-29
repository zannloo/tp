package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

/**
 * Encapsulates the required logic for task operation
 */
public class TaskOperation implements ITaskOperations {
    private static final String MESSAGE_DUPLICATE_TASK = "Task must not exist in task list.";
    private static final String MESSAGE_TASK_NOT_FOUND = "Task not found in task list.";
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

    @Override
    public int getTaskListSize() {
        return this.baseDir.size();
    }

    @Override
    public boolean hasTask(Task t) {
        return this.baseDir.contains(t);
    }

    @Override
    public boolean isValidIndex(int index) {
        return this.baseDir.isValidIndex(index);
    }

    @Override
    public void addTask(Task task) {
        checkArgument(!hasTask(task), MESSAGE_DUPLICATE_TASK);
        this.baseDir.addTask(task);
        this.stateLogger("Adding" + task.toString());
    }

    @Override
    public Task deleteTask(int index) {
        checkArgument(isValidIndex(index), MESSAGE_TASK_NOT_FOUND);
        this.stateLogger("deleting " + index);
        return this.baseDir.deleteTask(index);
    }

    @Override
    public Task markTask(int index) {
        checkArgument(isValidIndex(index), MESSAGE_TASK_NOT_FOUND);
        this.stateLogger("marking " + index);
        return this.baseDir.markTask(index);
    }

    @Override
    public Task unmarkTask(int index) {
        checkArgument(isValidIndex(index), MESSAGE_TASK_NOT_FOUND);
        this.stateLogger("un marking " + index);
        return this.baseDir.unmarkTask(index);
    }

    @Override
    public List<Task> findTask(String query) {
        this.stateLogger("finding " + query);
        return this.baseDir.findTask(query);
    }

    @Override
    public Task getTask(int index) {
        checkArgument(isValidIndex(index), MESSAGE_TASK_NOT_FOUND);
        this.stateLogger("getting " + index);
        return this.baseDir.getTask(index);
    }

    @Override
    public List<Task> getAllTasks() {
        this.stateLogger("getting all ");
        return this.baseDir.getAllTasks();
    }

    @Override
    public String toString() {
        return this.baseDir.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskOperation that = (TaskOperation) o;
        return Objects.equals(baseDir, that.baseDir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseDir);
    }
}
