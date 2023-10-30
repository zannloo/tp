package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;

import java.util.Map;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ReadOnlyTaskList;

/**
 * EditCommand is a class representing a command to edit the details of a person (either a student or a group) in
 * ProfBook.
 * Depending on the context (whether it's a student or group), this command can edit different fields.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String ERROR_MESSAGE_NO_SUCH_GROUP = "Group does not exist in ProfBook.";

    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Field(s) of group has been edited successfully.";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Field(s) of student has been edited successfully.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the student id. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: student id "
            + "[" + OPTION_NAME + " NAME] "
            + "[" + OPTION_PHONE + " PHONE] "
            + "[" + OPTION_EMAIL + " EMAIL] "
            + "[" + OPTION_ADDRESS + " ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " 0001Y "
            + OPTION_PHONE + " 91234567 "
            + OPTION_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Directory is invalid";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_NO_SUCH_PATH = "Path does not exist in ProfBook.";

    public static final String MESSAGE_NO_SUCH_STUDENT = "Student does not exist in ProfBook.";

    public static final String MESSAGE_DUPLICATE_STUDENT_ID =
            "StudentId %1$s has already been used by the student: %2$s";

    public static final String MESSAGE_DUPLICATE_GROUP_ID =
            "GroupId %1$s has already been used by the group: %2$s";

    public static final String MESSAGE_NO_CHANGES_MADE =
            "The value(s) you provided is the same as the current value(s). No changes have been made.";

    private final AbsolutePath target;

    private EditGroupDescriptor editGroupDescriptor;

    private EditStudentDescriptor editStudentDescriptor;

    /**
     * Constructs an EditCommand for editing a group's details.
     *
     * @param target              The path to the target group to be edited.
     * @param editGroupDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(AbsolutePath target, EditGroupDescriptor editGroupDescriptor) {
        this.target = target;
        this.editGroupDescriptor = new EditGroupDescriptor(editGroupDescriptor);
    }

    /**
     * Constructs an EditCommand for editing a student's details.
     *
     * @param target                The path to the target student to be edited.
     * @param editStudentDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(AbsolutePath target, EditStudentDescriptor editStudentDescriptor) {
        this.target = target;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    /**
     * Executes the EditCommand to edit a group or student's details.
     *
     * @param model The current model of the application.
     * @return A CommandResult indicating the result of the execution.
     * @throws CommandException If there's an error during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check path exists in ProfBook
        if (!model.hasPath(target)) {
            throw new CommandException(MESSAGE_NO_SUCH_PATH);
        }

        if (target.isGroupDirectory()) {
            return handleEditGroup(model);
        } else if (target.isStudentDirectory()) {
            return handleEditStudent(model);
        } else {
            throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
        }
    }

    private CommandResult handleEditGroup(Model model) throws CommandException {
        if (!model.hasGroup(target)) {
            throw new CommandException(ERROR_MESSAGE_NO_SUCH_GROUP);
        }

        GroupId groupId = target.getGroupId().get();

        // Check if Id is edited, if is edited check whether new id has already been used.
        Optional<GroupId> editedId = editGroupDescriptor.getId();
        boolean isEdited = editedId.isPresent() && (!editedId.get().equals(groupId));
        if (isEdited && model.hasGroupWithId(groupId)) {
            Group groupWithSameId = model.getGroupWithId(groupId);
            throw new CommandException(String.format(
                MESSAGE_DUPLICATE_STUDENT_ID, editedId.get(), Messages.format(groupWithSameId)));
        }

        ChildOperation<Group> rootOperation = model.rootChildOperation();
        Group groupToEdit = rootOperation.getChild(groupId);
        Group editedGroup = createEditedGroup(groupToEdit, this.editGroupDescriptor);

        // Check whether group is actually edited
        if (editedGroup.equals(groupToEdit)) {
            throw new CommandException(MESSAGE_NO_CHANGES_MADE);
        }

        rootOperation.deleteChild(groupId);
        rootOperation.addChild(groupId, editedGroup);
        model.updateList();

        return new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);
    }

    private CommandResult handleEditStudent(Model model) throws CommandException {
        if (!model.hasStudent(target)) {
            throw new CommandException(MESSAGE_NO_SUCH_STUDENT);
        }

        StudentId studentId = target.getStudentId().get();

        // Check if Id is edited, if is edited check whether new id has already been used.
        Optional<StudentId> editedId = editStudentDescriptor.getId();
        boolean isEdited = editedId.isPresent() && (!editedId.get().equals(studentId));
        if (isEdited && model.hasStudentWithId(studentId)) {
            Student studentWithSameId = model.getStudentWithId(studentId);
            throw new CommandException(String.format(
                MESSAGE_DUPLICATE_GROUP_ID, editedId.get(), Messages.format(studentWithSameId)));
        }

        ChildOperation<Student> groupOperation = model.groupChildOperation(target);
        Student studentToEdit = groupOperation.getChild(studentId);
        Student editedStudent = createEditedStudent(studentToEdit, this.editStudentDescriptor);

        // Check whether student is actually edited
        if (editedStudent.equals(studentToEdit)) {
            throw new CommandException(MESSAGE_NO_CHANGES_MADE);
        }

        groupOperation.deleteChild(studentId);
        groupOperation.addChild(editedStudent.getId(), editedStudent);
        model.updateList();

        return new CommandResult(MESSAGE_EDIT_STUDENT_SUCCESS);
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
        ReadOnlyTaskList taskList = new ReadOnlyTaskList(studentToEdit.getAllTasks());
        return new Student(taskList, updatedName, updatedEmail, updatedPhone, updatedAddress, updatedId);
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToEdit}
     * edited with {@code editGroupDescriptor}.
     */
    private static Group createEditedGroup(Group groupToEdit, EditGroupDescriptor editGroupDescriptor) {
        assert groupToEdit != null;
        Name updatedName = editGroupDescriptor.getName().orElse(groupToEdit.getName());
        GroupId updatedId = editGroupDescriptor.getId().orElse(groupToEdit.getId());
        ReadOnlyTaskList taskList = new ReadOnlyTaskList(groupToEdit.getAllTask());
        Map<Id, Student> students = groupToEdit.getChildren();
        return new Group(taskList, students, updatedName, updatedId);
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
            return this.target.equals(otherEditCommand.target)
                    && this.editGroupDescriptor.equals(otherEditCommand.editGroupDescriptor);
        } else {
            return this.target.equals(otherEditCommand.target)
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
