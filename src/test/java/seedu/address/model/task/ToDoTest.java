package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.TODO_1;
import static seedu.address.testutil.TypicalTasks.TODO_2;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo todo1 = TODO_1;
    private ToDo toDo2 = TODO_2;
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

    @Test
    public void markTask_taskIsMarked_returnsMarkedTask() {
        ToDo markedTask = todo1.mark();
        assertTrue(markedTask.getStatus());
        assertNotSame(todo1, markedTask);
    }

    @Test
    public void unmarkTask_taskIsUnmarked_returnsUnmarkedTask() {
        ToDo unmarkedTask = toDo2.unmark();
        assertFalse(unmarkedTask.getStatus());
    }

}
