package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CreateTodoCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CreateTodoCommand.MESSAGE_SUCCESS_ALL_GROUPS;
import static seedu.address.logic.commands.CreateTodoCommand.MESSAGE_SUCCESS_ALL_STUDENTS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.ToDo;
import seedu.address.testutil.StudentBuilder;

public class CreateTodoCommandTest {

    @Test
    public void constructor_nullRelativePathAndTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTodoCommand(null, null));
    }

    @Test
    public void execute_todoForAllStudentsInGroupAccepted_addSuccessful()
            throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~");
        Map<Id, Student> studentMap = new HashMap<>();
        Student alice = new StudentBuilder()
                .withName("Alice")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withAddress("123, Jurong West Ave 6, #08-111")
                .withId("0001Y").withTaskList(new ArrayList<>()).build();
        Student bob = new StudentBuilder()
                .withName("Bob")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withId("0002Y").withTaskList(new ArrayList<>()).build();
        studentMap.put(alice.getId(), alice);
        studentMap.put(bob.getId(), bob);
        Group grp = new Group(new TaskList(null), studentMap, new Name("AmazingGroup"), new GroupId("grp-003"));
        Map<Id, Group> groups = new HashMap<>();
        groups.put(new GroupId("grp-003"), grp);
        Root root = new Root(groups);

        ToDo todo = new ToDo("Assignment 1");

        assertFalse(alice.checkDuplicates(todo));
        assertFalse(bob.checkDuplicates(todo));

        AbsolutePath path = new AbsolutePath("~/grp-003");

        CreateTodoCommand command = new CreateTodoCommand(path, todo, "allStu");
        State state = new StateManager(currPath, root, new UserPrefs());
        CommandResult runCommand = command.execute(state);

        assertTrue(alice.checkDuplicates(todo));
        assertTrue(bob.checkDuplicates(todo));

        CommandResult returnStatement =
                new CommandResult(MESSAGE_SUCCESS_ALL_STUDENTS);

        assertEquals(runCommand, returnStatement);
    }

    @Test
    public void execute_deadlineForAllGroupsInRootAccepted_addSuccessful()
            throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~");
        Map<Id, Student> studentMap = new HashMap<>();
        List<Task> list1 = new ArrayList<>();
        List<Task> list2 = new ArrayList<>();
        TaskList taskList1 = new TaskList(list1);
        TaskList taskList2 = new TaskList(list2);
        Group grp1 = new Group(taskList1, studentMap, new Name("Amazing"), new GroupId("grp-001"));
        Group grp2 = new Group(taskList2, studentMap, new Name("AmazingGroup"), new GroupId("grp-002"));
        Map<Id, Group> groups = new HashMap<>();
        groups.put(new GroupId("grp-001"), grp1);
        groups.put(new GroupId("grp-002"), grp2);
        Root root = new Root(groups);

        ToDo todo = new ToDo("Assignment 3");

        AbsolutePath path = new AbsolutePath("~");

        assertFalse(grp1.checkDuplicates(todo));
        assertFalse(grp2.checkDuplicates(todo));

        CreateTodoCommand command = new CreateTodoCommand(path, todo, "allGrp");
        State state = new StateManager(currPath, root, new UserPrefs());
        CommandResult runCommand = command.execute(state);

        assertTrue(grp1.checkDuplicates(todo));
        assertTrue(grp2.checkDuplicates(todo));

        CommandResult returnStatement =
                new CommandResult(MESSAGE_SUCCESS_ALL_GROUPS);

        assertEquals(runCommand, returnStatement);
    }
    @Test
    public void execute_createTodoTask_success() throws CommandException, InvalidPathException,
            UnsupportedPathOperationException {
        ToDo todo = new ToDo("Todo read book");
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);

        AbsolutePath currPath = new AbsolutePath("~/");
        State state = new StateManager(currPath, root, new UserPrefs());
        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(target, todo);

        CommandResult successCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, target));
        assertEquals(successCommandResult, createTodoCommand.execute(state));
    }

    @Test
    public void execute_duplicateTodoTask_throwCommandException() throws InvalidPathException,
            UnsupportedPathOperationException {
        ToDo todo = new ToDo("Todo read book");
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);

        AbsolutePath currPath = new AbsolutePath("~/");
        State state = new StateManager(currPath, root, new UserPrefs());

        AbsolutePath target = new AbsolutePath("~/grp-001");
        TaskOperation taskOperation = state.taskOperation(target);
        taskOperation.addTask(todo);

        CreateTodoCommand createTodoCommand = new CreateTodoCommand(target, todo);

        assertThrows(CommandException.class,
                CreateTodoCommand.MESSAGE_DUPLICATE_TODO_TASK_STUDENT, () -> createTodoCommand.execute(state));
    }

    @Test
    public void equals_sameInstance_success() throws InvalidPathException {
        ToDo todo = new ToDo("Todo test");
        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(target, todo);
        CreateTodoCommand duplicateCreateTodoCommand = new CreateTodoCommand(target, todo);
        assertTrue(createTodoCommand.equals(duplicateCreateTodoCommand));
    }

    @Test
    public void equals_differentTodoTask_fail() throws InvalidPathException {
        ToDo todoTest1 = new ToDo("Todo test1");
        ToDo todoTest2 = new ToDo("Todo test2");
        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateTodoCommand createTodoCommand1 = new CreateTodoCommand(target, todoTest1);
        CreateTodoCommand createTodoCommand2 = new CreateTodoCommand(target, todoTest2);
        assertFalse(createTodoCommand1.equals(createTodoCommand2));
    }

    @Test
    public void toString_validateOutputString_correctStringRepresentation() throws InvalidPathException {
        ToDo todo = new ToDo("Todo test");
        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateTodoCommand createTodoCommand = new CreateTodoCommand(target, todo);
        String expected = "seedu.address.logic.newcommands.CreateTodoCommand{toCreateTodo="
                + "[T][ ] Todo test}";
        assertEquals(expected, createTodoCommand.toString());
    }
}
