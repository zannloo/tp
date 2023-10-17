package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;

import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
import seedu.address.model.taskmanager.TaskList;

/**
 * EditCommand is a class representing a command to edit the details of a person (either a student or a group) in
 * ProfBook.
 * Depending on the context (whether it's a student or group), this command can edit different fields.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String ERROR_MESSAGE_INVALID_PATH = "This path is invalid.";

    public static final String ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";

    public static final String ERROR_MESSAGE_NO_SUCH_GROUP = "Group does not exist in ProfBook.";

    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Field(s) of group has been edited successfully.";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Field(s) of student has been edited successfully.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + OPTION_NAME + " NAME] "
            + "[" + OPTION_PHONE + " PHONE] "
            + "[" + OPTION_EMAIL + " EMAIL] "
            + "[" + OPTION_ADDRESS + " ADDRESS] "
            + "[" + OPTION_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + OPTION_PHONE + " 91234567 "
            + OPTION_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Directory is invalid";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final RelativePath relativePath;

    private EditGroupDescriptor editGroupDescriptor;

    private EditStudentDescriptor editStudentDescriptor;

    /**
     * Constructs an EditCommand for editing a group's details.
     *
     * @param relativePath The relative path to the group to be edited.
     * @param editGroupDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(RelativePath relativePath, EditGroupDescriptor editGroupDescriptor) {
        this.relativePath = relativePath;
        this.editGroupDescriptor = new EditGroupDescriptor(editGroupDescriptor);
    }

    /**
     * Constructs an EditCommand for editing a student's details.
     *
     * @param relativePath The relative path to the student to be edited.
     * @param editStudentDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(RelativePath relativePath, EditStudentDescriptor editStudentDescriptor) {
        this.relativePath = relativePath;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }


    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        StudentId updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
        TaskList taskList = new TaskList(studentToEdit.getAllTask());
        return new Student(taskList, updatedName, updatedEmail, updatedPhone, updatedAddress, updatedId);
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToEdit}
     * edited with {@code editGroupDescriptor}.
     */
    private static Group createEditedGroup(Group groupToEdit, EditGroupDescriptor editGroupDescriptor) {
        assert groupToEdit != null;

        Name updatedName = editGroupDescriptor.getName().orElse(groupToEdit.getName());
        Id updatedId = editGroupDescriptor.getId().orElse(groupToEdit.getId());
        TaskList taskList = new TaskList(groupToEdit.getAllTask());
        Map<Id, Student> students = groupToEdit.getChildren();
        return new Group(taskList, students, updatedName, updatedId);
    }

    /**
     * Executes the EditCommand to edit a group or student's details.
     *
     * @param state The current state of the application.
     * @return A CommandResult indicating the result of the execution.
     * @throws CommandException If there's an error during command execution.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);
        AbsolutePath currPath = state.getCurrPath();

        // Check resolved path is valid
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(relativePath);
        } catch (InvalidPathException e) {
            throw new CommandException(e.getMessage());
        }

        if (targetPath.isGroupDirectory()) {
            ChildOperation<Group> target = state.rootChildOperation();
            GroupId groupId = targetPath.getGroupId().get();
            if (!target.hasChild(groupId)) {
                throw new CommandException(ERROR_MESSAGE_NO_SUCH_GROUP);
            }
            Group groupToEdit = target.getChild(groupId);
            Group editedGroup = createEditedGroup(groupToEdit, this.editGroupDescriptor);
            target.deleteChild(groupId);
            target.addChild(groupId, editedGroup);
            state.updateList();

            return new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);

        } else if (targetPath.isStudentDirectory()) {
            ChildOperation<Student> target = state.groupChildOperation(targetPath);
            StudentId studentId = targetPath.getStudentId().get();

            Student studentToEdit = target.getChild(studentId);
            Student editedStudent = createEditedStudent(studentToEdit, this.editStudentDescriptor);
            target.deleteChild(studentId);
            target.addChild(editedStudent.getId(), editedStudent);
            state.updateList();

            return new CommandResult(MESSAGE_EDIT_STUDENT_SUCCESS);

        } else {
            throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
        }

    }

    /**
     * Checks if this EditCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        if (this.editStudentDescriptor == null) {
            return this.relativePath.equals(otherEditCommand.relativePath)
                    && this.editGroupDescriptor.equals(otherEditCommand.editGroupDescriptor);
        } else {
            return this.relativePath.equals(otherEditCommand.relativePath)
                    && this.editStudentDescriptor.equals(otherEditCommand.editStudentDescriptor);
        }
    }

    /**
     * Returns a string representation of this EditCommand.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        if (this.editStudentDescriptor != null) {
            return new ToStringBuilder(this)
                    .add("toEdit", this.editStudentDescriptor)
                    .toString();
        }
        if (this.editGroupDescriptor != null) {
            return new ToStringBuilder(this)
                    .add("toEdit", this.editGroupDescriptor)
                    .toString();
        }
        return null;
    }
}
