package seedu.address.logic.newcommands;

import org.junit.jupiter.api.Test;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.taskmanager.ToDo;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class CreateTodoCommandTest {

    @Test
    public void constructor_nullRelativePathAndTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTodoCommand(null, null));
    }

    @Test
    public void equals_sameInstance_success() throws InvalidPathException {
        ToDo todo = new ToDo("Todo test");
        RelativePath relativePath = new RelativePath("~/grp-001");
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(relativePath, todo);
        CreateTodoCommand duplicateCreateTodoCommand = new CreateTodoCommand(relativePath, todo);
        assertTrue(createTodoCommand.equals(duplicateCreateTodoCommand));
    }

    @Test
    public void equals_differentTodoTask_fail() throws InvalidPathException {
        ToDo todoTest1 = new ToDo("Todo test1");
        ToDo todoTest2 = new ToDo("Todo test2");
        RelativePath relativePath = new RelativePath("~/grp-001");
        CreateTodoCommand createTodoCommand1 = new CreateTodoCommand(relativePath, todoTest1);
        CreateTodoCommand createTodoCommand2 = new CreateTodoCommand(relativePath, todoTest2);
        assertFalse(createTodoCommand1.equals(createTodoCommand2));
    }

    @Test
    public void toString_validateOutputString_correctStringRepresentation() throws InvalidPathException {
        ToDo todo = new ToDo("Todo test");
        RelativePath relativePath = new RelativePath("~/grp-001");
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(relativePath, todo);
        String expected = "seedu.address.logic.newcommands.CreateTodoCommand{toCreateTodo=Your ToDo has been added:\n[T][ ] Todo test}";
        assertEquals(expected, createTodoCommand.toString());
    }
}
