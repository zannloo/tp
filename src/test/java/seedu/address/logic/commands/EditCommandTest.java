package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.EditCommand.ERROR_MESSAGE_NO_SUCH_GROUP;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_GROUP_ID;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_STUDENT_ID;
import static seedu.address.logic.commands.EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_CHANGES_MADE;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_SUCH_PATH;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_SUCH_STUDENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalStudents.ELLE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.testutil.RootBuilder;

public class EditCommandTest {
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
    public void constructor_nullRelativePathAndEditGroupDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditGroupDescriptor) null));
    }

    @Test
    public void constructor_nullRelativePathAndEditStudentDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditStudentDescriptor) null));
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
        editGroupDescriptor.setId(new GroupId("grp-001"));
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
        editStudentDescriptor.setId(new StudentId("0005Y"));
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
    public void execute_noFieldChangeForEditStudent_throwCommandException() throws InvalidPathException {
        Student toBeEdited = ELLE;
        Group elleGroup = GROUP_ONE;

        RelativePath groupPath = new RelativePath(elleGroup.getId().toString());
        RelativePath stuPath = new RelativePath(toBeEdited.getId().toString());
        AbsolutePath targetAbsolutePath = rootPath.resolve(groupPath).resolve(stuPath);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        editStudentDescriptor.setId(new StudentId("0005Y"));
        editStudentDescriptor.setName(new Name("ELLE"));
        editStudentDescriptor.setPhone(new Phone("9482224"));
        editStudentDescriptor.setEmail(new Email("werner@example.com"));
        editStudentDescriptor.setAddress(new Address("michegan ave"));
        EditCommand command = new EditCommand(targetAbsolutePath, editStudentDescriptor);

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
        Student toBeEdited = ELLE;
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
    public void equals() throws InvalidPathException {
        EditGroupDescriptor editGroupDescriptor1 = new EditGroupDescriptor();
        EditGroupDescriptor editGroupDescriptor2 = new EditGroupDescriptor();
        editGroupDescriptor1.setName(new Name("Group 123"));
        editGroupDescriptor2.setName(new Name("Group 321"));
        EditCommand command1 = new EditCommand(rootPath, editGroupDescriptor1);
        EditCommand command2 = new EditCommand(rootPath, editGroupDescriptor2);

        // same object -> returns true
        assertEquals(command1, command1);

        // same values -> returns true
        EditCommand command1Copy = new EditCommand(rootPath, editGroupDescriptor1);
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
        EditGroupDescriptor editGroupDescriptor1 = new EditGroupDescriptor();
        editGroupDescriptor1.setName(new Name("Group 123"));
        EditCommand command = new EditCommand(rootPath, editGroupDescriptor1);
        String expected = EditCommand.class.getCanonicalName()
            + "{toEdit=" + editGroupDescriptor1 + "}";
        assertEquals(expected, command.toString());
    }
}
