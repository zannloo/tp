package seedu.address.model.taskmanager;

import java.util.List;

// TODO: @niddddddish
/**
 * Encapsulates logic of a TaskList
 */
public interface TaskList {


    public void add(Task t);

    public Task delete(int index); // should throw an error if index is not present

    public Task mark(int index); // should throw an error if index is not present

    public Task unmark(int index); // should throw an error if index is not present

    public List<Task> find(String query);

    public Task get(int index); // should throw an error if index is not present

    public List<Task> getAllTask();

    // Add more if required

}
