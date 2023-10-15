package seedu.address.model.taskmanager;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;


/**
 * Encapsulates logic of a TaskList
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Constructs a {@code TaskList}.
     *
     * @param taskList A valid task list.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
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
    public Task delete(int index) throws NoSuchTaskException {
        if (index - 1 > this.taskList.size() || index < 0) {
            throw new NoSuchTaskException("There are 0 tasks at this level at the moment.");
        }
        int initialSize = this.taskList.size();
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        assert this.taskList.size() == initialSize - 1 : "Task should be removed from the list";
        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The number of the task to be marked.
     * @return The marked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task mark(int taskNumber) throws NoSuchTaskException {
        if (taskNumber - 1 > this.taskList.size() || taskNumber < 0) {
            throw new NoSuchTaskException("There are 0 tasks at this level at the moment.");
        }
        Task task = this.taskList.get(taskNumber - 1);
        task.mark();
        return task;
    }

    /**
     * Unmarks a task.
     *
     * @param taskNumber The number of the task to be unmarked.
     * @return The unmarked task.
     * @throws NoSuchTaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task unmark(int taskNumber) throws NoSuchTaskException {
        if (taskNumber - 1 > this.taskList.size() || taskNumber < 0) {
            throw new NoSuchTaskException("There are 0 tasks at this level at the moment.");
        }
        Task task = this.taskList.get(taskNumber - 1);
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
    public List<Task> find(String query) throws NoSuchTaskException {
        if (this.taskList.size() == 0) {
            throw new NoSuchTaskException("There are 0 tasks at this level at the moment.");
        }
        List<Task> list = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.description.contains(query)) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     * @throws NoSuchTaskException If the index is out of bounds.
     */
    public Task get(int index) throws NoSuchTaskException {
        if (index - 1 > this.taskList.size() || index < 0) {
            throw new NoSuchTaskException("Please input an index between 1 and" + this.taskList.size());
        }
        Task task = this.taskList.get(index - 1);
        return task;
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return A list of all tasks.
     * @throws NoSuchTaskException If there are no tasks at this level.
     */
    public List<Task> getAllTask() {
        return new ArrayList<>(this.taskList);
    }

    /**
     * Checks if this TaskList is equal to another object.
     * Two TaskLists are equal if their task lists are equal.
     * The task lists are compared based on the equals method of the Task class.
     * @param o The object to compare this TaskList against.
     * @return true if the given object represents a TaskList equivalent to this task list, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList1 = (TaskList) o;
        return this.taskList.equals(taskList1.taskList);
    }

    /**
     * Checks if the task list contains a duplicate of the specified task.
     *
     * @param t the task to be checked for duplication in the task list
     * @return true if a duplicate of the specified task is found in the task list, false otherwise
     */
    public boolean containsDuplicates(Task t) {
        for (Task check : this.taskList) {
            if (check.equals(t)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Retrieves size of the current task list for testing purposes
     *
     * @return size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
