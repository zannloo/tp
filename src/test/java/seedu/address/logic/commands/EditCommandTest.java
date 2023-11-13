package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.EditCommand.ERROR_MESSAGE_NO_SUCH_GROUP;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_GROUP_ID;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_STUDENT_ID;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_GROUP_SUCCESS;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS;
import static seedu.address.logic.commands.EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_CHANGES_MADE;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_SUCH_PATH;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_SUCH_STUDENT;
import static seedu.address.logic.commands.EditCommand.MESSAGE_USAGE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.MAYA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;

public class EditCommandTest {

    private Model model;

    private Model expectedModel;

    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    private EditGroupDescriptor editGroupDescriptorForFirstCommand;

    private EditGroupDescriptor editGroupDescriptorForSecondCommand;

    private EditStudentDescriptor editStudentDescriptorForThirdCommand;

    private EditStudentDescriptor editStudentDescriptorForFourthCommand;

    @BeforeEach
    public void setup() {
        Root root = PROFBOOK_WITH_TWO_GROUPS;
        model = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());

        editGroupDescriptorForFirstCommand = new EditGroupDescriptor();
        editGroupDescriptorForSecondCommand = new EditGroupDescriptor();
        editGroupDescriptorForFirstCommand.setName(new Name("Group 123"));
        editGroupDescriptorForSecondCommand.setName(new Name("Group 321"));

        editStudentDescriptorForThirdCommand = new EditStudentDescriptor();
        editStudentDescriptorForThirdCommand.setName(new Name("Marry"));
        editStudentDescriptorForFourthCommand = new EditStudentDescriptor();
        editStudentDescriptorForFourthCommand.setId(new StudentId("0999A"));
    }

    @Test
    public void constructor_nullRelativePathAndEditGroupDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditGroupDescriptor) null));
    }

    @Test
    public void constructor_nullRelativePathAndEditStudentDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditStudentDescriptor) null));
    }

    @Test
    public void execute_editGroupId_success() throws CommandException, InvalidPathException {
        Group groupInRoot = GROUP_ONE;

        RelativePath groupPath = new RelativePath(groupInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        editGroupDescriptor.setId(new GroupId("grp-003"));
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);
        CommandResult expectedResult = new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);

        CommandResult result = command.execute(model);

        assertEquals(expectedResult, result);
    }

    @Test
    public void execute_editStudentId_success() throws CommandException, InvalidPathException {
        Student toBeEdited = ALICE;
        Group groupInRoot = GROUP_ONE;

        RelativePath groupPath = new RelativePath(groupInRoot.getId().toString());
        RelativePath stuPath = new RelativePath(toBeEdited.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        editStudentDescriptor.setId(new StudentId("0999Y"));
        EditCommand command = new EditCommand(targetAbsolutePath, editStudentDescriptor);
        CommandResult expectedResult = new CommandResult(MESSAGE_EDIT_STUDENT_SUCCESS);

        CommandResult result = command.execute(model);

        assertEquals(expectedResult, result);
    }

    @Test
    public void execute_nullModel_throwCommandException() throws InvalidPathException {
        Group groupInRoot = GROUP_ONE;

        RelativePath groupPath = new RelativePath(groupInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_noSuchPath_throwCommandException() throws InvalidPathException {
        Group groupNotInRoot = GROUP_TWO;

        RelativePath groupPath = new RelativePath(groupNotInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);

        String expectedMessage = MESSAGE_NO_SUCH_PATH;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateGroupId_throwCommandException() throws InvalidPathException {
        Group groupInRoot = GROUP_ONE;

        RelativePath groupPath = new RelativePath(groupInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        editGroupDescriptor.setId(new GroupId("grp-002"));
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);

        String expectedMessage = MESSAGE_DUPLICATE_GROUP_ID;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateStudentId_throwCommandException() throws InvalidPathException {
        Student toBeEdited = ELLE;
        Group elleGroup = GROUP_ONE;

        RelativePath groupPath = new RelativePath(elleGroup.getId().toString());
        RelativePath stuPath = new RelativePath(toBeEdited.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        editStudentDescriptor.setId(new StudentId("0001Y"));
        EditCommand command = new EditCommand(targetAbsolutePath, editStudentDescriptor);

        String expectedMessage = MESSAGE_DUPLICATE_STUDENT_ID;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_noFieldChangeForEditGroup_throwCommandException() throws InvalidPathException {
        Group groupInRoot = GROUP_ONE;

        RelativePath groupPath = new RelativePath(groupInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        editGroupDescriptor.setId(new GroupId("grp-001"));
        editGroupDescriptor.setName(new Name("Group One"));
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);

        String expectedMessage = MESSAGE_NO_CHANGES_MADE;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_noSuchGroup_throwCommandException() throws InvalidPathException {
        Group groupNotInRoot = GROUP_TWO;

        RelativePath groupPath = new RelativePath(groupNotInRoot.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath);

        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand command = new EditCommand(targetAbsolutePath, editGroupDescriptor);

        String expectedMessage = ERROR_MESSAGE_NO_SUCH_GROUP;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_noSuchStudent_throwCommandException() throws InvalidPathException {
        Student toBeEdited = MAYA;
        Group elleGroup = GROUP_ONE;

        RelativePath groupPath = new RelativePath(elleGroup.getId().toString());
        RelativePath stuPath = new RelativePath(toBeEdited.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        EditCommand command = new EditCommand(targetAbsolutePath, descriptor);

        String expectedMessage = MESSAGE_NO_SUCH_STUDENT;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_invalidDirectory_throwCommandException() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand command = new EditCommand(rootPath, editStudentDescriptor);

        String expectedMessage = MESSAGE_INCORRECT_DIRECTORY_ERROR;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_isHelpTrue_returnMessageUsage() throws CommandException {
        CommandResult result = EditCommand.HELP_MESSAGE.execute(model);

        CommandResult expectedResult = new CommandResult(MESSAGE_USAGE);

        assertEquals(result, expectedResult);
    }

    @Test
    public void equals() throws InvalidPathException {
        EditCommand firstCommand = new EditCommand(rootPath, editGroupDescriptorForFirstCommand);
        EditCommand secondCommand = new EditCommand(rootPath, editGroupDescriptorForSecondCommand);
        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> returns true
        EditCommand firstCommandCopy = new EditCommand(rootPath, editGroupDescriptorForFirstCommand);
        assertEquals(firstCommand, firstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);

        // different values
        assertNotEquals(firstCommand, secondCommand);

        EditStudentDescriptor editStudentDescriptor3Copy =
                new EditStudentDescriptor(editStudentDescriptorForThirdCommand);
        EditCommand thirdCommand = new EditCommand(rootPath, editStudentDescriptorForThirdCommand);
        EditCommand fourthCommand = new EditCommand(rootPath, editStudentDescriptorForFourthCommand);

        // same values -> returns true
        EditCommand thirdCommandCopy = new EditCommand(rootPath, editStudentDescriptor3Copy);
        assertEquals(thirdCommand, thirdCommandCopy);

        // different values
        assertNotEquals(thirdCommand, fourthCommand);
    }

    @Test
    public void toString_editGroupDescriptorNotNull_returnExpectedString() {
        EditGroupDescriptor editGroupDescriptor1 = new EditGroupDescriptor();
        editGroupDescriptor1.setName(new Name("Group 123"));
        EditCommand command = new EditCommand(rootPath, editGroupDescriptor1);
        String expected = EditCommand.class.getCanonicalName()
            + "{toEdit=" + editGroupDescriptor1 + "}";
        assertEquals(expected, command.toString());
    }

    @Test
    public void toString_editStudentDescriptorNotNull_returnExpectedString() {
        EditStudentDescriptor editStudentDescriptor1 = new EditStudentDescriptor();
        editStudentDescriptor1.setName(new Name("0999A"));
        EditCommand command = new EditCommand(rootPath, editStudentDescriptor1);
        String expected = EditCommand.class.getCanonicalName()
                + "{toEdit=" + editStudentDescriptor1 + "}";
        assertEquals(expected, command.toString());
    }
}
