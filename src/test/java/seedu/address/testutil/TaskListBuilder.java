package seedu.address.testutil;

import java.util.List;

import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

/**
 * A utility class to help with building ReadOnlyTaskList objects.
 */
public class TaskListBuilder {
    private TaskListManager tasks;

    /**
     * Creates a {@code TaskListBuilder} with the empty task list.
     */
    public TaskListBuilder() {
        this.tasks = new TaskListManager();
    }

    /**
     * Adds a new {@code Task} to the TaskList we are building.
     */
    public TaskListBuilder withTask(Task task) {
        tasks.addTask(task);
        return this;
    }

    /**
     * Adds a list of new {@code Task} to the TaskList we are building.
     */
    public TaskListBuilder withTasks(List<Task> tasks) {
        for (Task task : tasks) {
            this.tasks.addTask(task);
        }
        return this;
    }

    public ReadOnlyTaskList build() {
        return tasks;
    }
}
