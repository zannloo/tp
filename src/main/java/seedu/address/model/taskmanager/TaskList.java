package seedu.address.model.taskmanager;

import java.util.List;

// TODO: @niddddddish
public interface TaskList {


    public TaskList add(Task t);

    public TaskList delete(int index); // should throw an error if index is not present

    public TaskList mark(int index); // should throw an error if index is not present

    public TaskList unmark(int index); // should throw an error if index is not present

    public TaskList find(String query);

    public Task get(int index); // should throw an error if index is not present

    public List<Task> getAllTask();

    // Add more if required

}
