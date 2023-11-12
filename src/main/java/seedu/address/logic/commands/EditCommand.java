package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
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
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.TaskListManager;

/**
 * EditCommand is a class representing a command to edit the details of a person (either a student or a group) in
 * ProfBook.
 * Depending on the context (whether it's a student or group), this command can edit different fields.
 */
public class EditCommand extends Command {

    /** The command word for editing the details of a group or student */
    public static final String COMMAND_WORD = "edit";

    /** Error message indicating that the group to be edited does not exist in ProfBook */
    public static final String ERROR_MESSAGE_NO_SUCH_GROUP = "Group does not exist in ProfBook.";

    /** Usage information for the 'edit' command */
    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " <path> " + "[OPTION]... \n"
            + "\n"
            + "Edit the details of the group or student.\n"
            + "\n"
            + "Argument: \n"
            + "    path                 Valid path to group or student\n"
            + "\n"
            + "Option: (Provide at least one of the following fields for editing)\n"
            + "    -n, --name           Name of the group / student\n"
            + "    -i, --id             Id of the group / student\n"
            + "    -e, --email          Email of the student\n"
            + "    -p, --phone          Phone of the student\n"
            + "    -a, --address        Address of the student\n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "edit grp-001 -n Perfect Group \n"
            + "edit grp-001 -i grp-002";

    /** Message indicating successful editing of group fields */
    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Field(s) of group has been edited successfully.";

    /** Message indicating successful editing of student fields */
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Field(s) of student has been edited successfully.";

    /** Error message indicating that the root directory cannot be edited */
    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Root directory cannot be edited.";

    /** Message indicating that at least one field to edit must be provided */
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    /** Error message indicating that the provided path does not exist in ProfBook */
    public static final String MESSAGE_NO_SUCH_PATH = "Path does not exist in ProfBook.";

    /** Error message indicating that the provided student does not exist in ProfBook */
    public static final String MESSAGE_NO_SUCH_STUDENT = "Student does not exist in ProfBook.";

    /** Error message indicating that a student with the provided ID already exists */
    public static final String MESSAGE_DUPLICATE_STUDENT_ID =
            "StudentId %1$s has already been used by the student: %2$s";

    /** Error message indicating that a group with the provided ID already exists */
    public static final String MESSAGE_DUPLICATE_GROUP_ID =
            "GroupId %1$s has already been used by the group: %2$s";

    /** Message indicating that no changes have been made during the edit */
    public static final String MESSAGE_NO_CHANGES_MADE =
            "The value(s) you provided is the same as the current value(s). No changes have been made.";

    /** A special instance of EditCommand used to display the command's usage message */
    public static final EditCommand HELP_MESSAGE = new EditCommand() {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    /** Logger for logging messages related to EditCommand */
    private static final Logger logger = LogsCenter.getLogger(EditCommand.class);

    /** Represents the absolute path of the target group or student to be edited */
    private final AbsolutePath target;

    /**
     * Represents the descriptor containing the fields to be edited in a group.
     * This is used when editing a group.
     */
    private final EditGroupDescriptor editGroupDescriptor;

    /**
     * Represents the descriptor containing the fields to be edited in a student.
     * This is used when editing a student.
     */
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * Constructs an EditCommand for editing a group's details with the specified target path and editGroupDescriptor.
     *
     * @param target              The absolute path to the target group to be edited.
     * @param editGroupDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(AbsolutePath target, EditGroupDescriptor editGroupDescriptor) {
        this.target = target;
        this.editGroupDescriptor = new EditGroupDescriptor(editGroupDescriptor);
        this.editStudentDescriptor = null;
    }

    /**
     * Constructs an EditCommand for editing a student's details with the specified target path and
     * editStudentDescriptor.
     *
     * @param target The absolute path to the target student to be edited.
     * @param editStudentDescriptor The descriptor containing the details to edit.
     */
    public EditCommand(AbsolutePath target, EditStudentDescriptor editStudentDescriptor) {
        this.target = target;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
        this.editGroupDescriptor = null;
    }

    /**
     * Private constructor for creating the HELP_MESSAGE instance.
     */
    private EditCommand() {
        this.target = null;
        this.editGroupDescriptor = null;
        this.editStudentDescriptor = null;
    }

    /**
     * Executes the EditCommand to edit a group or student's details.
     *
     * @param model The model on which the command should be executed.
     * @return A CommandResult containing a message indicating the success of the edit operation.
     * @throws CommandException If there exist any error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.info("Executing edit command...");

        // Check path exists in ProfBook
        if (!model.hasPath(target)) {
            logger.warning("The path to the group or student to be edited does not exist in ProfBook."
                    + " Aborting edit command.");
            throw new CommandException(MESSAGE_NO_SUCH_PATH);
        }

        if (target.isGroupDirectory()) {
            return handleEditGroup(model);
        }

        if (target.isStudentDirectory()) {
            return handleEditStudent(model);
        }

        logger.warning("Root directory cannot be edited. Aborting edit command.");
        throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
    }

    /**
     * Handles the execution of the EditCommand for editing a group.
     *
     * @param model The model on which the command should be executed.
     * @return A CommandResult containing a message indicating the success of editing group's details.
     * @throws CommandException If there exist any error in executing the command.
     */
    private CommandResult handleEditGroup(Model model) throws CommandException {
        if (!model.hasGroup(target)) {
            logger.warning("The group to be edited does not exist in ProfBook. Aborting edit command.");
            throw new CommandException(ERROR_MESSAGE_NO_SUCH_GROUP);
        }

        GroupId groupId = target.getGroupId().get();
        logger.info("Editing group " + groupId + "...");

        // Check whether id is edited, if it is edited then check whether new id has already been used.
        Optional<GroupId> editedId = editGroupDescriptor.getId();
        boolean isEdited = editedId.isPresent() && (!editedId.get().equals(groupId));

        if (isEdited && model.hasGroupWithId(editedId.get())) {
            Group groupWithSameId = model.getGroupWithId(editedId.get());
            logger.warning("The GroupId has already been used by other group. Aborting edit command.");
            throw new CommandException(String.format(
                MESSAGE_DUPLICATE_GROUP_ID, editedId.get(), Messages.format(groupWithSameId)));
        }

        updatingGroupList(model, groupId);

        logger.info("The group's details have been edited successfully.");
        return new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);
    }

    /**
     * Handles the execution of the EditCommand for editing a student.
     *
     * @param model The model on which the command should be executed.
     * @return  A CommandResult containing a message indicating the success of editing student's details.
     * @throws CommandException If there exist any error in executing the command.
     */
    private CommandResult handleEditStudent(Model model) throws CommandException {
        if (!model.hasStudent(target)) {
            logger.warning("The student to be edited does not exist in ProfBook. Aborting edit command.");
            throw new CommandException(MESSAGE_NO_SUCH_STUDENT);
        }

        StudentId studentId = target.getStudentId().get();
        logger.info("Editing student " + studentId + "...");

        // Check if id is edited, if is edited check whether new id has already been used.
        Optional<StudentId> editedId = editStudentDescriptor.getId();
        boolean isEdited = editedId.isPresent() && (!editedId.get().equals(studentId));

        if (isEdited && model.hasStudentWithId(editedId.get())) {
            Student studentWithSameId = model.getStudentWithId(editedId.get());
            logger.warning("The StudentId has already been used by other student. Aborting edit command.");
            throw new CommandException(String.format(
                    MESSAGE_DUPLICATE_STUDENT_ID, editedId.get(), Messages.format(studentWithSameId)));
        }

        updatingStudentList(model, studentId);

        logger.info("The student's details have been edited successfully.");
        return new CommandResult(MESSAGE_EDIT_STUDENT_SUCCESS);
    }

    /**
     * Updates the group list in the model after editing a group.
     *
     * @param model The model on which the command should be executed.
     * @param groupId The ID of the group to be edited.
     * @throws CommandException If no changes are being made to the group's fields.
     */
    private void updatingGroupList(Model model, GroupId groupId) throws CommandException {
        ChildOperation<Group> rootOperation = model.rootChildOperation();
        Group groupToEdit = rootOperation.getChild(groupId);
        Group editedGroup = createEditedGroup(groupToEdit, this.editGroupDescriptor);

        if (editedGroup.equals(groupToEdit)) {
            logger.warning("The details of the group has not been changed.");
            throw new CommandException(MESSAGE_NO_CHANGES_MADE);
        }

        boolean idIsEdited = !editedGroup.getId().equals(groupId);
        if (idIsEdited && model.hasGroupWithId(editedGroup.getId())) {
            Group groupWithSameId = model.getGroupWithId(editedGroup.getId());
            throw new CommandException(String.format(
                MESSAGE_DUPLICATE_GROUP_ID, editedGroup.getId(), Messages.format(groupWithSameId)));
        }

        rootOperation.deleteChild(groupId);
        rootOperation.addChild(editedGroup.getId(), editedGroup);

        if (target.equals(model.getCurrPath())) {
            try {
                model.changeDirectory(model.getCurrPath().resolve(RelativePath.PARENT));
                model.changeDirectory(model.getCurrPath().resolve(new RelativePath(editedGroup.getId().toString())));
            } catch (InvalidPathException e) {
                throw new IllegalArgumentException("Internal Error: " + e.getMessage());
            }
        }
        model.updateList();
    }

    /**
     * Updates the student list in the model after editing a student.
     *
     * @param model The model on which the command should be executed.
     * @param studentId The ID of the student to be edited.
     * @throws CommandException If no changes are being made to the student's fields.
     */
    private void updatingStudentList(Model model, StudentId studentId) throws CommandException {
        ChildOperation<Student> groupOperation = model.groupChildOperation(target);
        Student studentToEdit = groupOperation.getChild(studentId);
        Student editedStudent = createEditedStudent(studentToEdit, this.editStudentDescriptor);

        // Check whether student is actually edited
        if (editedStudent.equals(studentToEdit)) {
            logger.warning("The details of the student has not been changed.");
            throw new CommandException(MESSAGE_NO_CHANGES_MADE);
        }

        // Check if Id is edited, if is edited check whether new id has already been used.
        boolean idIsEdited = !editedStudent.getId().equals(studentId);
        if (idIsEdited && model.hasStudentWithId(editedStudent.getId())) {
            Student studentWithSameId = model.getStudentWithId(editedStudent.getId());
            throw new CommandException(String.format(
                MESSAGE_DUPLICATE_STUDENT_ID, editedStudent.getId(), Messages.format(studentWithSameId)));
        }

        groupOperation.deleteChild(studentId);
        groupOperation.addChild(editedStudent.getId(), editedStudent);

        model.updateList();
    }

    /**
     * Creates a new Student instance with updated fields based on the provided editStudentDescriptor.
     *
     * @param studentToEdit The original student to be edited.
     * @param editStudentDescriptor The descriptor containing the fields to be edited in the student.
     * @return A new edited Student instance.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        StudentId updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
        ReadOnlyTaskList taskList = new TaskListManager(studentToEdit.getAllTasks());

        return new Student(taskList, updatedName, updatedEmail, updatedPhone, updatedAddress, updatedId);
    }

    /**
     * Creates a new Group instance with updated fields based on the provided editGroupDescriptor.
     *
     * @param groupToEdit The original group to be edited.
     * @param editGroupDescriptor The descriptor containing the fields to be edited in the group.
     * @return A new edited group instance.
     */
    private static Group createEditedGroup(Group groupToEdit, EditGroupDescriptor editGroupDescriptor) {
        assert groupToEdit != null;

        Name updatedName = editGroupDescriptor.getName().orElse(groupToEdit.getName());
        GroupId updatedId = editGroupDescriptor.getId().orElse(groupToEdit.getId());
        ReadOnlyTaskList taskList = new TaskListManager(groupToEdit.getAllTasks());
        Map<Id, Student> students = groupToEdit.getChildren();

        return new Group(taskList, students, updatedName, updatedId);
    }

    /**
     * Checks if this EditCommand is equal to another object.
     *
     * @param other The object to compare with this EditCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

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
     * Returns the string representation of this EditCommand.
     *
     * @return The string representation of this EditCommand.
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
