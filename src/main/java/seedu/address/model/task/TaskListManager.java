package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.exceptions.NoSuchTaskException;


/**
 * Encapsulate the logic of a prof book model, mainly the need for a task list
 * at every level
 */
public class TaskListManager extends ReadOnlyTaskList {

    /**
     * Constructs a new task list manager
     */
    public TaskListManager() {
        super();
    }

    /**
     * Constructs a new {@code TaskListManager} with the data in {@code taskList}.
     * @param taskList
     */
    public TaskListManager(ReadOnlyTaskList taskList) {
        super(taskList);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        int initialSize = this.taskList.size();
        taskList.add(t);
        assert this.taskList.size() == initialSize + 1 : "Task Deadline should be added to the list";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task deleteTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        int initialSize = this.taskList.size();
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        assert this.taskList.size() == initialSize - 1 : "Task should be removed from the list";
        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     * @return The marked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task markTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        task.mark();
        return task;
    }

    /**
     * Unmarks a task.
     *
     * @param index The number of the task to be unmarked.
     * @return The unmarked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task unmarkTask(int index) throws NoSuchTaskException {
        verifyIsValidIndex(index);
        Task task = this.taskList.get(index - 1);
        task.unmark();
        return task;
    }

    /**
     * Finds tasks that match the given query.
     *
     * @param query The query to match.
     * @return A list of tasks that match the query.
     * @throws NoSuchTaskException If there are no tasks at this level.
     */
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
        return super.equals(otherTaskListManager);
    }
}
