package seedu.address.Tasks;

import seedu.address.commons.exceptions.TaskException;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks for the Addressbook program.
 * It provides methods to manage and manipulate the list of tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with the given stored list of tasks.
     * @param taskList an ArrayList of Task objects representing the list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param TaskNumber the index of the task to delete (1-based indexing)
     * @throws TaskException if the given index is out of bounds
     */
    public String deleteTask(int TaskNumber) throws TaskException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
        }
        int initialSize = this.taskList.size();
        Task task = this.taskList.get(TaskNumber - 1);
        String s = task.remove();
        this.taskList.remove(TaskNumber - 1);
        assert this.taskList.size() == initialSize - 1 : "Task should be removed from the list";
        return s;
    }


    /**
     * Marks a task as done.
     * @param TaskNumber the index of the task to mark as done (1-based indexing)
     * @throws TaskException if the given index is out of bounds
     */
    public String mark(int TaskNumber) throws TaskException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
        }
        Task task = this.taskList.get(TaskNumber - 1);
        return task.mark();
    }

    /**
     * Unmarks a task as done.
     * @param TaskNumber the index of the task to unmark as done (1-based indexing)
     * @throws TaskException if the given index is out of bounds
     */
    public String unmark(int TaskNumber) throws TaskException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new TaskException("There are 0 tasks at this level at the moment.");
        }
        Task task = this.taskList.get(TaskNumber - 1);
        return task.unmark();
    }

    /**
     * Creates a new ToDo task and adds it to the list of tasks.
     * @param content the description of the ToDo task
     */
    public String createToDo(String content) {
        Task taskT = new ToDo(content);
        int initialSize = this.taskList.size();
        taskList.add(taskT);
        assert this.taskList.size() == initialSize + 1 : "Task ToDo should be added to the list";
        return "ToDo has been added";
    }

    /**
     * Creates a new Deadline task and adds it to the list of tasks.
     * @param description the description of the Deadline task
     * @param deadline the deadline of the Deadline task
     */
    public String createDeadline(String description, String deadline) {
        Task taskD = new Deadline(description, deadline);
        int initialSize = this.taskList.size();
        taskList.add(taskD);
        assert this.taskList.size() == initialSize + 1 : "Task Deadline should be added to the list";
        return "Deadline has been added";
    }

    /**
     * Finds and displays tasks that contain a given keyword in their description.
     * @param keyWord the keyword to search for in the descriptions of tasks
     */
    public String find(String keyWord) {
        String msg = "";
        if (this.taskList.size() == 0) {
            return "There are 0 tasks at this level at the moment.";
        }
        int i = 1;
        for (Task task : this.taskList) {
            if (task.description.contains(keyWord)) {
                msg += i + task.listString() + "\n";
                i++;
            }
        }
        return msg;
    }
}
