package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.newcommands.CreateTodoCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.ToDo;

public class CreateTodoCommandTest {

    @Test
    public void constructor_nullRelativePathAndTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTodoCommand(null, null));
    }

    @Test
    public void execute_createTodoTask_success() throws CommandException, InvalidPathException,
            UnsupportedPathOperationException {
        ToDo todo = new ToDo("Todo read book");
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(taskList, children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("~/grp-001");
        State state = new State(currPath, root, new UserPrefs());
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(relativePath, todo);
        AbsolutePath absolutePath = currPath.resolve(relativePath);
        TaskOperation target = StateManager.taskOperation(root, absolutePath);
        CommandResult successCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, target));

        assertEquals(successCommandResult, createTodoCommand.execute(state));
    }

    @Test
    public void execute_duplicateTodoTask_throwCommandException() throws InvalidPathException,
            UnsupportedPathOperationException {
        ToDo todo = new ToDo("Todo read book");
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(taskList, children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("~/grp-001");
        State state = new State(currPath, root, new UserPrefs());
        AbsolutePath absolutePath = currPath.resolve(relativePath);
        TaskOperation target = StateManager.taskOperation(root, absolutePath);
        target.addTask(todo);
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(relativePath, todo);

        assertThrows(CommandException.class,
                CreateTodoCommand.MESSAGE_DUPLICATE_TODO_TASK, () -> createTodoCommand.execute(state));
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
        String expected = "seedu.address.logic.newcommands.CreateTodoCommand{toCreateTodo=Your ToDo has been added:"
                + "\n[T][ ] Todo test}";
        assertEquals(expected, createTodoCommand.toString());
    }
}
