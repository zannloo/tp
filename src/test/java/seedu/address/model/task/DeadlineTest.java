package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    private static final String VALID_DESCRIPTION = "Finish project";
    @Test
    public void equals_nullObject_returnsFalse() {
        LocalDateTime deadline = LocalDateTime.now();
        Deadline task = new Deadline(VALID_DESCRIPTION, deadline);
        assertFalse(task.equals(null));
    }

    @Test
    public void equals_objectsOfDifferentClasses_returnsFalse() {
        LocalDateTime deadline = LocalDateTime.now();
        Deadline task = new Deadline(VALID_DESCRIPTION, deadline);
        Object otherObject = new Object();
        assertFalse(task.equals(otherObject));
    }

    @Test
    public void markTask_taskIsMarked_returnsMarkedTask() {
        LocalDateTime deadline = LocalDateTime.now();
        Deadline task = new Deadline(VALID_DESCRIPTION, deadline);
        Deadline markedTask = task.mark();
        assertTrue(markedTask.getStatus());
    }

    @Test
    public void unmarkTask_taskIsUnmarked_returnsUnmarkedTask() {
        LocalDateTime deadline = LocalDateTime.now();
        Deadline task = new Deadline(VALID_DESCRIPTION, deadline, true);
        Deadline unmarkedTask = task.unmark();
        assertFalse(unmarkedTask.getStatus());
    }
}
