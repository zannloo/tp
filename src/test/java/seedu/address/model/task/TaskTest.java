package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.TODO_1;
import static seedu.address.testutil.TypicalTasks.TODO_2;

import org.junit.jupiter.api.Test;
public class TaskTest {

    private Task todo1 = TODO_1;
    private Task toDo2 = TODO_2;

    @Test
    public void statusString_returnsTrue() {
        Task markedTask = todo1.mark();
        assertEquals(markedTask.statusString(), "true");
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        assertFalse(todo1.equals(null));
    }
    @Test
    public void equals_objectsOfDifferentClasses_returnsFalse() {
        Object otherObject = new Object();
        assertFalse(toDo2.equals(otherObject));
    }

    @Test
    public void equals_differentToDos_returnsFalse() {
        assertFalse(toDo2.equals(todo1));
    }

    @Test
    public void equals_sameToDos_returnsTrue() {
        assertTrue(toDo2.equals(toDo2));
    }
}
