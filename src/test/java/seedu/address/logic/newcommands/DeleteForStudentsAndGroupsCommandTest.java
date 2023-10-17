package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.StudentBuilder;

class DeleteForStudentsAndGroupsCommandTest {
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteForStudentsAndGroupsCommand(null));
    }

    @Test
    void execute_deleteStudent_success() throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(grp.getId(), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-001/stu-001");
        Student stu = new StudentBuilder().build();
        StudentId studentId = new StudentId("stu-001");
        assertTrue(grp.hasChild(studentId));

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        State state = new StateManager(currPath, root, new UserPrefs());
        CommandResult commandResult = command.execute(state);

        assertFalse(root.hasChild(studentId));

        assertEquals(String.format(DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS, stu),
                commandResult.getFeedbackToUser());
    }

    @Test
    void execute_deleteGroup_success() throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~/");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-001/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        State state = new StateManager(currPath, root, new UserPrefs());
        CommandResult commandResult = command.execute(state);

        GroupId groupId = new GroupId("grp-001");
        assertFalse(root.hasChild(groupId));

        assertEquals(String.format(DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS, grp),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noSuchStudent_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("stu-002");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        State state = new StateManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_NO_SUCH_STUDENT_OR_GROUP, (
                ) -> command.execute(state)
        );
    }

    @Test
    public void execute_noSuchGroup_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-002/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        State state = new StateManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_NO_SUCH_STUDENT_OR_GROUP, (
                ) -> command.execute(state)
        );
    }

    @Test
    public void execute_incorrectDirectory_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        State state = new StateManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR, (
                ) -> command.execute(state)
        );
    }


    @Test
    void testEquals() throws InvalidPathException {
        RelativePath pathGrp001 = new RelativePath("~/grp-001");
        RelativePath pathGrp002 = new RelativePath("~/grp-002");
        RelativePath pathStu001 = new RelativePath("~/grp-001/stu-001");
        RelativePath pathStu002 = new RelativePath("~/grp-002/stu-002");

        DeleteForStudentsAndGroupsCommand deleteG001 = new DeleteForStudentsAndGroupsCommand(pathGrp001);
        DeleteForStudentsAndGroupsCommand deleteG002 = new DeleteForStudentsAndGroupsCommand(pathGrp002);
        DeleteForStudentsAndGroupsCommand deleteS001 = new DeleteForStudentsAndGroupsCommand(pathStu001);
        DeleteForStudentsAndGroupsCommand deleteS002 = new DeleteForStudentsAndGroupsCommand(pathStu002);

        // same object(Group) -> returns true
        assertTrue(deleteG001.equals(deleteG001));
        // same object(Student) -> returns true
        assertTrue(deleteS001.equals(deleteS001));

        // same values(Group) -> returns true
        DeleteForStudentsAndGroupsCommand deleteG001Copy = new DeleteForStudentsAndGroupsCommand(pathGrp001);
        assertTrue(deleteG001.equals(deleteG001Copy));
        // same values(Student) -> returns true
        DeleteForStudentsAndGroupsCommand deleteS001Copy = new DeleteForStudentsAndGroupsCommand(pathStu001);
        assertTrue(deleteS001.equals(deleteS001Copy));

        // different types -> returns false
        assertFalse(deleteG001.equals(1));
        assertFalse(deleteS001.equals(1));
        // null -> returns false
        assertFalse(deleteG001.equals(null));
        assertFalse(deleteS001.equals(null));

        // different group -> returns false
        assertFalse(deleteG001.equals(deleteG002));
        // different student -> returns false
        assertFalse(deleteS001.equals(deleteS002));
        // different area to delete -> returns false
        assertFalse(deleteG001.equals(deleteS001));
        // different student -> returns false
        assertFalse(deleteS001.equals(deleteS002));
    }

    @Test
    void toString_sameString_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        String expected = DeleteForStudentsAndGroupsCommand.class.getCanonicalName()
                + "{toDeleteStudentOrGroup=" + path + "}";
        assertEquals(expected, command.toString());
    }
}
