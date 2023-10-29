package seedu.address.model.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.task.exceptions.NoSuchTaskException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListManagerTest {
    private TaskListManager taskListManager1;
    private TaskListManager taskListManager2;

    @BeforeEach
    public void setUp() {
        taskListManager1 = new TaskListManager();
        taskListManager2 = new TaskListManager();
    }

    @Test
    public void findTask_matchingQuery_returnsMatchingTasks() throws NoSuchTaskException {
        Task task1 = new Deadline("Finish project", LocalDateTime.now());
        Task task2 = new Deadline("Buy groceries", LocalDateTime.now());
        Task task3 = new ToDo("Complete homework");
        taskListManager1.addTask(task1);
        taskListManager1.addTask(task2);
        taskListManager1.addTask(task3);
        List<Task> result = taskListManager1.findTask("Finish");
        assertEquals(1, result.size());
        assertEquals("Finish project", result.get(0).description);
    }

    @Test
    public void findTask_noMatchingQuery_throwsNoSuchTaskException() {
        assertThrows(NoSuchTaskException.class, () -> taskListManager1.findTask("Buy groceries"));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        assertTrue(taskListManager1.equals(taskListManager1));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        assertFalse(taskListManager1.equals(null));
    }

}
