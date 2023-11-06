package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.TASK_LIST_1;
import static seedu.address.testutil.TypicalTasks.TASK_LIST_2;

import org.junit.jupiter.api.Test;

public class ReadOnlyTaskListTest {

    private ReadOnlyTaskList taskList1 = TASK_LIST_1;
    private ReadOnlyTaskList taskList2 = TASK_LIST_2;
    @Test
    public void isValidIndex_validIndices() {
        assertTrue(taskList1.isValidIndex(1));
    }


    @Test
    public void equals_sameTaskList() {
        ReadOnlyTaskList taskListCopy = TASK_LIST_1;
        assertTrue(taskList1.equals(taskListCopy));
    }

    @Test
    public void equals_nullTaskList() {
        assertFalse(taskList1.equals(null));
    }

    @Test
    public void equals_differentTaskLists() {
        assertFalse(taskList1.equals(taskList2));
    }

    @Test
    public void toString_returnsTrue() {
        String expected = "seedu.address.model.task.TaskListManager"
                + "{Task List=[[D][ ] Assignment 1(by: 2023-11-15 10:00), [D][ ] Assignment 2(by: 2023-11-20 15:30),"
                + " [D][ ] Project Presentation(by: 2023-12-05 14:00),"
                + " [T][ ] Quiz 1, [T][ ] Homework 2, [T][ ] Research Paper]}";
        assertEquals(expected, taskList1.toString());
    }
}
