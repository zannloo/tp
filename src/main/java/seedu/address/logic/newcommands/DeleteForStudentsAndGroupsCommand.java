package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
/**
 * Deletes a {@code Student} or {@code Group} according to the targeted path.
 */
public class DeleteForStudentsAndGroupsCommand extends Command {
    public static final String COMMAND_WORD = "rm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a student/group from the specified directory\n"
            + "Parameters: "
            + "specified path\n"
            + "Example: " + COMMAND_WORD
            + " stu-200 ";


    public static final String MESSAGE_SUCCESS_FOR_STUDENT = "Student removed: %1$s";
    public static final String MESSAGE_SUCCESS_FOR_GROUP = "Group removed: %1$s";
    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Directory is invalid";
    public static final String MESSAGE_INVALID_PATH = "Path is invalid";
    public static final String MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    public static final String MESSAGE_NO_SUCH_STUDENT_OR_GROUP = "There is no such student or group to delete";
    public static final String MESSAGE_DELETE_CURRENT_PATH = "Current path cannot be deleted";
    public static final String MESSAGE_DELETE_DISPLAY_PATH = "Current display path cannot be deleted.";
    protected Student stu;
    protected Group grp;
    private final AbsolutePath toBeDeleted;

    /**
     * Creates an DeleteForStudentsAndGroupsCommand to specified {@code Student} or {@code Group}
     */
    public DeleteForStudentsAndGroupsCommand(AbsolutePath toBeDeleted) { //path will specify which grp/student
        requireNonNull(toBeDeleted);
        this.toBeDeleted = toBeDeleted;
    }

    /**
     * Executes an DeleteForStudentsAndGroupsCommand to delete a {@code Student} or {@code Group}
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     * @throws InvalidPathException Exception thrown when error occurs due to invalid path.
     * @throws UnsupportedPathOperationException Exception thrown when error occurs due to unsupported path execution.
     * @throws NoSuchChildException Exception thrown when child specified does not exist.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        if (toBeDeleted.isRootDirectory()) {
            throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
        }

        // Check if to be deleted path is current path.
        if (toBeDeleted.equals(state.getCurrPath())) {
            throw new CommandException(MESSAGE_DELETE_CURRENT_PATH);
        }

        // Check if to be deleted path is diplay path.
        if (toBeDeleted.equals(state.getDisplayPath())) {
            throw new CommandException(MESSAGE_DELETE_DISPLAY_PATH);
        }

        // Check path exists in ProfBook
        if (!state.hasPath(toBeDeleted)) {
            throw new CommandException(MESSAGE_NO_SUCH_STUDENT_OR_GROUP);
        }

        if (toBeDeleted.isStudentDirectory()) {
            ChildOperation<Student> target = state.groupChildOperation(toBeDeleted);
            StudentId studentId = toBeDeleted.getStudentId().get();
            if (!target.hasChild(studentId)) {
                throw new CommandException(MESSAGE_NO_SUCH_STUDENT_OR_GROUP);
            }
            stu = target.getChild(studentId);
            target.deleteChild(studentId);
            state.updateList();
            return new CommandResult(String.format(MESSAGE_SUCCESS_FOR_STUDENT, Messages.format(stu)));
        }

        if (toBeDeleted.isGroupDirectory()) {
            ChildOperation<Group> target = state.rootChildOperation();
            GroupId groupId = toBeDeleted.getGroupId().get();
            if (!target.hasChild(groupId)) {
                throw new CommandException(MESSAGE_NO_SUCH_STUDENT_OR_GROUP);
            }
            grp = target.getChild(groupId);
            target.deleteChild(groupId);
            state.updateList();
            return new CommandResult(String.format(MESSAGE_SUCCESS_FOR_GROUP, Messages.format(grp)));
        }

        throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);

    }


    /**
     * Compares this {@code DeleteForStudentsAndGroupsCommand} to another {@code DeleteForStudentsAndGroupsCommand}
     * to see if they are equal.
     *
     * @param other The other object to compare against this {@code DeleteForStudentsAndGroupsCommand}.
     * @return True if the object is same as {@code DeleteForStudentsAndGroupsCommand} and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof DeleteForStudentsAndGroupsCommand)) {
            return false;
        }

        DeleteForStudentsAndGroupsCommand otherDeleteForStudentsAndGroupsCommand =
                (DeleteForStudentsAndGroupsCommand) other;
        return this.toBeDeleted.equals(otherDeleteForStudentsAndGroupsCommand.toBeDeleted);
    }

    /**
     * Returns a string representation of the {@code DeleteForStudentsAndGroupsCommand}.
     *
     * @return String representation of the {@code DeleteForStudentsAndGroupsCommand}.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDeleteStudentOrGroup", toBeDeleted)
                .toString();
    }
}
