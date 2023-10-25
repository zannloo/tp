package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.StudentBuilder;

class DeleteForStudentsAndGroupsCommandTest {
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteForStudentsAndGroupsCommand(null));
    }

    @Test
    void execute_deleteStudent_success() throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(grp.getId(), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-001/0001Y");
        Student stu = new StudentBuilder().build();
        StudentId studentId = new StudentId("0001Y");
        assertTrue(grp.hasChild(studentId));

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        Model model = new ModelManager(currPath, root, new UserPrefs());
        CommandResult commandResult = command.execute(model);

        assertFalse(root.hasChild(studentId));

        assertEquals(String.format(DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS_FOR_STUDENT, Messages.format(stu)),
                commandResult.getFeedbackToUser());
    }

    @Test
    void execute_deleteGroup_success() throws InvalidPathException, CommandException {
        AbsolutePath currPath = new AbsolutePath("~/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-001/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        Model model = new ModelManager(currPath, root, new UserPrefs());
        CommandResult commandResult = command.execute(model);

        GroupId groupId = new GroupId("grp-001");
        assertFalse(root.hasChild(groupId));

        assertEquals(String.format(DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS_FOR_GROUP, Messages.format(grp)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noSuchStudent_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-001/0002Y");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        Model model = new ModelManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_NO_SUCH_STUDENT_OR_GROUP, (
                ) -> command.execute(model)
        );
    }

    @Test
    public void execute_noSuchGroup_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-002/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        Model model = new ModelManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_NO_SUCH_STUDENT_OR_GROUP, (
                ) -> command.execute(model)
        );
    }

    @Test
    public void execute_incorrectDirectory_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/");

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        Model model = new ModelManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                DeleteForStudentsAndGroupsCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR, (
                ) -> command.execute(model)
        );
    }


    @Test
    void testEquals() throws InvalidPathException {
        AbsolutePath pathGrp001 = new AbsolutePath("~/grp-001");
        AbsolutePath pathGrp002 = new AbsolutePath("~/grp-002");
        AbsolutePath pathStu001 = new AbsolutePath("~/grp-001/0001Y");
        AbsolutePath pathStu002 = new AbsolutePath("~/grp-002/0002Y");

        DeleteForStudentsAndGroupsCommand deleteG001 = new DeleteForStudentsAndGroupsCommand(pathGrp001);
        DeleteForStudentsAndGroupsCommand deleteG002 = new DeleteForStudentsAndGroupsCommand(pathGrp002);
        DeleteForStudentsAndGroupsCommand deleteS001 = new DeleteForStudentsAndGroupsCommand(pathStu001);
        DeleteForStudentsAndGroupsCommand deleteS002 = new DeleteForStudentsAndGroupsCommand(pathStu002);

        // same object(Group) -> returns true
        assertEquals(deleteG001, deleteG001);
        // same object(Student) -> returns true
        assertEquals(deleteS001, deleteS001);

        // same values(Group) -> returns true
        DeleteForStudentsAndGroupsCommand deleteG001Copy = new DeleteForStudentsAndGroupsCommand(pathGrp001);
        assertEquals(deleteG001, deleteG001Copy);
        // same values(Student) -> returns true
        DeleteForStudentsAndGroupsCommand deleteS001Copy = new DeleteForStudentsAndGroupsCommand(pathStu001);
        assertEquals(deleteS001, deleteS001Copy);

        // different types -> returns false
        assertNotEquals(1, deleteG001);
        assertNotEquals(1, deleteS001);
        // null -> returns false
        assertNotEquals(null, deleteG001);
        assertNotEquals(null, deleteS001);

        // different group -> returns false
        assertNotEquals(deleteG001, deleteG002);
        // different student -> returns false
        assertNotEquals(deleteS001, deleteS002);
        // different area to delete -> returns false
        assertNotEquals(deleteG001, deleteS001);
        // different student -> returns false
        assertNotEquals(deleteS001, deleteS002);
    }

    @Test
    void toString_sameString_success() throws InvalidPathException {
        AbsolutePath path = new AbsolutePath("~/grp-001/0001Y");
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(path);
        String expected = DeleteForStudentsAndGroupsCommand.class.getCanonicalName()
                + "{toDeleteStudentOrGroup=" + path + "}";
        assertEquals(expected, command.toString());
    }
}
