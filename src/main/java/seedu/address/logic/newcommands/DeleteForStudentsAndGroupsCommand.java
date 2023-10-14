package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;
import static seedu.address.model.statemanager.StateManager.rootOperation;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.RootOperation;
import seedu.address.model.statemanager.State;

/**
 * Deletes a {@code Student} or {@code Group} according to the targeted path.
 */
public class DeleteForStudentsAndGroupsCommand extends Command {
    public static final String COMMAND_WORD = "rm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student or group ";

    public static final String MESSAGE_SUCCESS = "Student/Group removed: %1$s";
    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Directory is invalid";
    public static final String MESSAGE_INVALID_PATH = "Path is invalid";
    public static final String MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    public static final String MESSAGE_NO_SUCH_STUDENT_OR_GROUP = "There is no such student or group to delete";
    protected Student stu;
    protected Group grp;
    private final RelativePath path;

    /**
     * Creates an DeleteForStudentsAndGroupsCommand to specified {@code Student} or {@code Group}
     */
    public DeleteForStudentsAndGroupsCommand(RelativePath path) {
        requireNonNull(path);
        this.path = path;
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
        AbsolutePath currPath = state.getCurrPath();
        Root root = state.getRoot();
        try {
            requireAllNonNull(currPath, root);
            AbsolutePath absolutePath = currPath.resolve(path);
            CommandResult returnStatement;
            if (absolutePath.isStudentDirectory()) {
                GroupOperation groupOper = groupOperation(root, absolutePath);
                StudentId studentId = absolutePath.getStudentId().get();
                stu = groupOper.getChild(studentId);
                groupOper.deleteChild(studentId);
                returnStatement = new CommandResult(String.format(MESSAGE_SUCCESS, stu.toString()));
                state.updateFilteredList();
            } else if (absolutePath.isGroupDirectory()) {
                RootOperation rootOper = rootOperation(root);
                GroupId groupId = absolutePath.getGroupId().get();
                grp = rootOper.getChild(groupId);
                rootOper.deleteChild(groupId);
                returnStatement = new CommandResult(String.format(MESSAGE_SUCCESS, grp.toString()));
                state.updateFilteredList();
            } else {
                throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
            }
            state.updateFilteredList();
            return returnStatement;
        } catch (InvalidPathException e) {
            throw new CommandException(MESSAGE_INVALID_PATH);
        } catch (UnsupportedPathOperationException e) {
            throw new CommandException(MESSAGE_UNSUPPORTED_PATH_OPERATION);
        } catch (NoSuchChildException e) {
            throw new CommandException(MESSAGE_NO_SUCH_STUDENT_OR_GROUP);
        }
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
        return this.path.equals(otherDeleteForStudentsAndGroupsCommand.path);
    }

    /**
     * Returns a string representation of the {@code DeleteForStudentsAndGroupsCommand}.
     *
     * @return String representation of the {@code DeleteForStudentsAndGroupsCommand}.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDeleteStudentOrGroup", path)
                .toString();
    }
}
