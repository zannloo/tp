package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.MESSAGE_GROUP_NOT_FOUND;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.MESSAGE_INVALID_MOVE_COMMAND;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.MESSAGE_MOVE_STUDENT_SUCCESS;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

public class MoveStudentToGroupCommandTest {

    private Model model;

    private Model expectedModel;

    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    private Group sourceGroup = GROUP_ONE;

    private Group destinationGroup = GROUP_TWO;

    private Student toBeMoved = ALICE;

    @BeforeEach
    public void setup() {
        Root root = PROFBOOK_WITH_TWO_GROUPS;
        model = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());
    }

    @Test
    public void constructor_nullSourcePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MoveStudentToGroupCommand(null, new AbsolutePath("~/grp-002")));
    }

    @Test
    public void constructor_nullDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MoveStudentToGroupCommand(new AbsolutePath("~/grp-001"), null));
    }

    @Test
    public void constructor_nullSourcePathAndDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoveStudentToGroupCommand(null, null));
    }

    @Test
    public void execute_moveStudentFromSourceToDest_success() throws InvalidPathException {
        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        ChildOperation<Student> targetOperation = expectedModel.groupChildOperation(destinationGroupAbsolutePath);
        targetOperation.addChild(toBeMoved.getId(), toBeMoved);

        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        RelativePath studentPath = new RelativePath(toBeMoved.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath).resolve(studentPath);

        ChildOperation<Student> sourceOperation = expectedModel.groupChildOperation(sourceGroupAbsolutePath);
        sourceOperation.deleteChild(toBeMoved.getId());

        MoveStudentToGroupCommand command =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        String expectedMessage = String.format(
                MESSAGE_MOVE_STUDENT_SUCCESS, toBeMoved.getId(), destinationGroup.getId());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwCommandException() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand command =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_studentToBeMovedNotInSourceGroup_throwCommandException() throws InvalidPathException {
        AbsolutePath absolutePathToStudentToBeMoved = new AbsolutePath("~/grp-001/0011Y");

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(absolutePathToStudentToBeMoved, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void execute_destinationGroupNotExists_throwCommandException() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        AbsolutePath destinationGroupAbsolutePath = new AbsolutePath("~/grp-003");

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_sourceIsRootDirectory_throwCommandException() throws InvalidPathException {
        AbsolutePath sourceGroupIsRootAbsolutePath = new AbsolutePath("~");

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupIsRootAbsolutePath, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_INVALID_MOVE_COMMAND);
    }

    @Test
    public void execute_sourceIsGroupDirectory_throwCommandException() throws InvalidPathException {
        AbsolutePath sourceGroupIsGroupAbsolutePath = new AbsolutePath("~/grp-001");

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupIsGroupAbsolutePath, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_INVALID_MOVE_COMMAND);
    }

    @Test
    public void execute_destIsRootDirectory_throwCommandException() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        AbsolutePath destinationGroupAbsolutePath = new AbsolutePath("~");

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_INVALID_MOVE_COMMAND);
    }

    @Test
    public void execute_destIsStudentDirectory_throwCommandException() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        AbsolutePath destinationGroupAbsolutePath = new AbsolutePath("~/grp-002/0006Y");

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertCommandFailure(moveStudentToGroupCommand, model, MESSAGE_INVALID_MOVE_COMMAND);
    }

    @Test
    public void equals() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);
        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand1 =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        // same object -> returns true
        assertEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand1);

        // same values -> returns true
        MoveStudentToGroupCommand moveStudentToGroupCommand1Copy =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, moveStudentToGroupCommand1);

        // null -> returns false
        assertNotEquals(null, moveStudentToGroupCommand1);

        MoveStudentToGroupCommand moveStudentToGroupCommand2 =
                new MoveStudentToGroupCommand(destinationGroupAbsolutePath, sourceGroupAbsolutePath);

        // different values
        assertNotEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand2);
    }

    @Test
    public void toString_successfullyMoveStudent_returnExpectedString() throws InvalidPathException {
        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        String expected = MoveStudentToGroupCommand.class.getCanonicalName()
                + "{Source Path=" + sourceGroupAbsolutePath
                + ", Destination Path=" + destinationGroupAbsolutePath + "}";

        assertEquals(expected, moveStudentToGroupCommand.toString());
    }
}
