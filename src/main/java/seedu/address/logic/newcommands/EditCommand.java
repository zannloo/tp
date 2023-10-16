package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.TaskList;

import java.util.*;

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
        Id updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
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
        try {
            AbsolutePath currPath = state.getCurrPath();
            Root root = state.getRoot();
            AbsolutePath absolutePath = currPath.resolve(this.relativePath);
            if (absolutePath.isGroupDirectory()) {
                ChildOperation<Group> target = StateManager.rootChildOperation(root);
                GroupId groupId = absolutePath.getGroupId().get();
                if (!target.hasChild(groupId)) {
                    throw new CommandException(ERROR_MESSAGE_NO_SUCH_GROUP);
                }
                Group groupToEdit = target.getChild(groupId);
                Group editedGroup = createEditedGroup(groupToEdit, this.editGroupDescriptor);
                target.deleteChild(groupId);
                target.addChild(groupId, editedGroup);
                state.updateList();

                return new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);

            } else if (absolutePath.isStudentDirectory()) {
                ChildOperation<Student> target = StateManager.groupChildOperation(root, absolutePath);
                StudentId studentId = absolutePath.getStudentId().get();

                Student studentToEdit = target.getChild(studentId);
                Student editedStudent = createEditedStudent(studentToEdit, this.editStudentDescriptor);
                target.deleteChild(studentId);
                target.addChild(editedStudent.getId(), editedStudent);
                state.updateList();

                return new CommandResult(MESSAGE_EDIT_STUDENT_SUCCESS);

            } else {
                throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
            }
        } catch (InvalidPathException invalidPathException) {
            throw new CommandException(ERROR_MESSAGE_INVALID_PATH);
        } catch (UnsupportedPathOperationException e) {
            throw new CommandException(ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION);
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


    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Id id;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setId(toCopy.id);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, id);
        }

        /**
         * Sets the name field in the descriptor.
         *
         * @param name The new name to set.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Gets the name field from the descriptor.
         *
         * @return An Optional containing the name if it's set, empty otherwise.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the phone field in the descriptor.
         *
         * @param phone The new phone to set.
         */
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        /**
         * Gets the phone field from the descriptor.
         *
         * @return An Optional containing the phone if it's set, empty otherwise.
         */
        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Sets the email field in the descriptor.
         *
         * @param email The new phone to set.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Gets the email field from the descriptor.
         *
         * @return An Optional containing the email if it's set, empty otherwise.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets the address field in the descriptor.
         *
         * @param address The new address to set.
         */
        public void setAddress(Address address) {
            this.address = address;
        }

        /**
         * Gets the address field from the descriptor.
         *
         * @return An Optional containing the address if it's set, empty otherwise.
         */
        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets the id field in the descriptor.
         *
         * @param id The new id to set.
         */
        public void setId(Id id) {
            this.id = id;
        }

        /**
         * Gets the id field from the descriptor.
         *
         * @return An Optional containing the id if it's set, empty otherwise.
         */
        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        /**
         * Checks if this EditStudentDescriptor is equal to another object.
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
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            EditStudentDescriptor otherEditStudentDescriptor = (EditStudentDescriptor) other;
            return this.name.equals(otherEditStudentDescriptor.name)
                    && this.phone.equals(otherEditStudentDescriptor.phone)
                    && this.email.equals(otherEditStudentDescriptor.email)
                    && this.address.equals(otherEditStudentDescriptor.address)
                    && this.id.equals(otherEditStudentDescriptor.id);
        }

        /**
         * Returns a string representation of this EditStudentDescriptor.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("id", id)
                    .toString();
        }
    }


    public static class EditGroupDescriptor {
        private Name name;

        private Id id;

        public EditGroupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGroupDescriptor(EditGroupDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        /**
         * Sets the name field in the descriptor.
         *
         * @param name The new name to set.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Gets the name field from the descriptor.
         *
         * @return An Optional containing the name if it's set, empty otherwise.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the id field in the descriptor.
         *
         * @param id The new id to set.
         */
        public void setId(Id id) {
            this.id = id;
        }

        /**
         * Gets the id field from the descriptor.
         *
         * @return An Optional containing the id if it's set, empty otherwise.
         */
        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        /**
         * Checks if this EditStudentDescriptor is equal to another object.
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
            if (!(other instanceof EditGroupDescriptor)) {
                return false;
            }

            EditGroupDescriptor otherEditGroupDescriptor = (EditGroupDescriptor) other;
            if (this.name == null || otherEditGroupDescriptor.name == null) {
                return false;
            }
            if (this.id == null || otherEditGroupDescriptor.id == null) {
                return false;
            }
            return this.name.equals(otherEditGroupDescriptor.name) && this.id.equals(otherEditGroupDescriptor.id);
        }

        /**
         * Returns a string representation of this EditStudentDescriptor.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("id", id)
                    .toString();
        }
    }
}
