//@@author zannloo
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.MESSAGE_DELETE_CURRENT_PATH;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.MESSAGE_DELETE_DISPLAY_PATH;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS_FOR_GROUP;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.MESSAGE_SUCCESS_FOR_STUDENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.testutil.RootBuilder;

class DeleteForStudentsAndGroupsCommandTest {
    private Model model;
    private Model expectedModel;
    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    @BeforeEach
    public void setup() {
        Root root = new RootBuilder().withGroup(GROUP_ONE).build();
        model = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());
    }

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteForStudentsAndGroupsCommand(null));
    }

    @Test
    public void execute_nullModel_throwCommandException() throws InvalidPathException {
        Group toBeDeleted = GROUP_ONE;

        RelativePath groupPath = new RelativePath(toBeDeleted.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    void execute_deleteStudent_success() throws InvalidPathException, CommandException {
        Student toBeDeleted = ELLE;
        Group elleGroup = GROUP_ONE;

        RelativePath groupPath = new RelativePath(elleGroup.getId().toString());
        RelativePath stuPath = new RelativePath(toBeDeleted.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        ChildOperation<Student> operation = expectedModel.groupChildOperation(targetAbsolutePath);
        operation.deleteChild(toBeDeleted.getId());

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_SUCCESS_FOR_STUDENT, Messages.format(toBeDeleted));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_deleteGroup_success() throws InvalidPathException {
        Group toBeDeleted = GROUP_ONE;

        RelativePath groupPath = new RelativePath(toBeDeleted.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        ChildOperation<Group> operation = expectedModel.rootChildOperation();
        operation.deleteChild(toBeDeleted.getId());
        expectedModel.updateList();

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_SUCCESS_FOR_GROUP, Messages.format(toBeDeleted));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noSuchStudent_throwsCommandException() throws InvalidPathException {
        Student studentNotInGroupOne = FIONA;

        RelativePath groupPath = new RelativePath(GROUP_ONE.getId().toString());
        RelativePath stuPath = new RelativePath(studentNotInGroupOne.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_PATH_NOT_FOUND, targetAbsolutePath);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_noSuchGroup_throwsCommandException() throws InvalidPathException {
        Group groupNotInRoot = GROUP_TWO;

        RelativePath groupPath = new RelativePath(groupNotInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_PATH_NOT_FOUND, targetAbsolutePath);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_incorrectDirectory_throwsCommandException() {
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(rootPath);
        String expectedMessage = MESSAGE_INCORRECT_DIRECTORY_ERROR;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_pathNotExistsForGroup_throwsCommandException() throws InvalidPathException {
        AbsolutePath targetAbsolutePath = new AbsolutePath("~/grp-123");
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_PATH_NOT_FOUND, targetAbsolutePath);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_pathNotExistsForStudent_throwsCommandException() throws InvalidPathException {
        AbsolutePath targetAbsolutePath = new AbsolutePath("~/grp-001/0999Y");
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = String.format(MESSAGE_PATH_NOT_FOUND, targetAbsolutePath);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_deleteCurrentPath_throwsCommandException() {
        AbsolutePath targetAbsolutePath = model.getCurrPath();
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = MESSAGE_DELETE_CURRENT_PATH;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_deleteDisplayPath_throwsCommandException() {
        AbsolutePath targetAbsolutePath = model.getDisplayPath();
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(targetAbsolutePath);
        String expectedMessage = MESSAGE_DELETE_DISPLAY_PATH;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() throws InvalidPathException {
        DeleteForStudentsAndGroupsCommand command1 = new DeleteForStudentsAndGroupsCommand(rootPath);
        DeleteForStudentsAndGroupsCommand command2 = new DeleteForStudentsAndGroupsCommand(
                new AbsolutePath("~/grp-001"));

        // same object -> returns true
        assertEquals(command1, command1);

        // same values -> returns true
        DeleteForStudentsAndGroupsCommand command1Copy = new DeleteForStudentsAndGroupsCommand(rootPath);
        assertEquals(command1, command1Copy);

        // different types -> returns false
        assertNotEquals(1, command1);

        // null -> returns false
        assertNotEquals(null, command1);

        // different values
        assertNotEquals(command1, command2);
    }

    @Test
    public void toStringMethod() {
        DeleteForStudentsAndGroupsCommand command = new DeleteForStudentsAndGroupsCommand(rootPath);
        String expected = DeleteForStudentsAndGroupsCommand.class.getCanonicalName()
            + "{toDeleteStudentOrGroup=" + rootPath + "}";
        assertEquals(expected, command.toString());
    }
}
