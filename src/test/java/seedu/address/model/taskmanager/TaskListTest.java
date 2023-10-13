package seedu.address.model.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;


public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Initialize the tasklist with no tasks
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddToDo() {
        taskList.add(new ToDo("Do junit test"));
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void testAddDeadline() {
        LocalDateTime testTime = LocalDateTime.now();
        taskList.add(new Deadline("Do junit test", testTime));
        assertEquals(taskList.size(), 1);
    }

    @Test
    public void testMark() throws NoSuchTaskException {
        taskList.add(new ToDo("Do junit test"));
        taskList.mark(1);
        assertEquals(taskList.get(1).isDone, true);
    }

    @Test
    public void testUnMark() throws NoSuchTaskException {
        taskList.add(new ToDo("Do junit test"));
        taskList.unmark(1);
        assertEquals(taskList.get(1).isDone, false);
    }
    @Test
    public void testDeleteTask() throws NoSuchTaskException {
        taskList.add(new ToDo("Do junit test"));
        taskList.delete(1);
        assertEquals(taskList.size(), 0);
    }


}
