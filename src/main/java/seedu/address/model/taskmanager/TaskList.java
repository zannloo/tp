package seedu.address.model.taskmanager;

import seedu.address.model.taskmanager.exceptions.TaskException;
import java.util.List;
import java.util.ArrayList;


/**
 * Encapsulates logic of a TaskList
 */
public class TaskList {

    ArrayList<Task> taskList;

    /**
     * Constructs a {@code TaskList}.
     *
     * @param taskList A valid task list.
     */
    public TaskList(ArrayList<Task> taskList) {
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
     * @throws TaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task delete(int index) throws TaskException {
        if (index - 1 >  this.taskList.size() || index < 0) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
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
     * @throws TaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task mark(int taskNumber) throws TaskException {
        if (taskNumber - 1 >  this.taskList.size() || taskNumber < 0) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
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
     * @throws TaskException If there are no tasks at this level or taskNumber provided is too large.
     */
    public Task unmark(int taskNumber) throws TaskException {
        if (taskNumber - 1 >  this.taskList.size() || taskNumber < 0) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
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
     * @throws TaskException If there are no tasks at this level.
     */
    public List<Task> find(String query) throws TaskException {
        if (this.taskList.size() == 0) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
        }
        List<Task> list = null;
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
     * @throws TaskException If the index is out of bounds.
     */
    public Task get(int index) throws TaskException {
        if (index - 1 >  this.taskList.size() || index < 0) {
            throw new TaskException("Please input an index between 1 and" + this.taskList.size());
        }
        Task task = this.taskList.get(index - 1);
        return task;
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return A list of all tasks.
     * @throws TaskException If there are no tasks at this level.
     */
    public List<Task> getAllTask() throws TaskException {
        if (this.taskList.size() == 0) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
        }
        List<Task> list = null;
        for (Task task : this.taskList) {
                list.add(task);
        }
        return list;
    }

}
