package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;
import static seedu.address.model.statemanager.StateManager.rootOperation;
import static seedu.address.model.statemanager.StateManager.studentOperation;

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
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.RootOperation;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.Deadline;

/**
 * Adds a Deadline for a specified {@code Student} or {@code Group}.
 */
public class CreateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deadline ";

    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE_TASK =
            "This Deadline task has already been allocated";
    public static final String MESSAGE_INCORRECT_DIRECTORY_ERROR = "Directory is invalid";
    public static final String MESSAGE_INVALID_PATH = "Path is invalid";
    public static final String MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    protected Student stu;
    protected Group grp;
    private final RelativePath path;
    private final Deadline deadline;

    /**
     * Creates an CreateDeadlineCommand to add the Deadline Task for a specified {@code Student} or {@code Group}
     */
    public CreateDeadlineCommand(RelativePath path, Deadline deadline) {
        requireAllNonNull(path, deadline);
        this.path = path;
        this.deadline = deadline;
    }

    /**
     * Executes an CreateDeadlineCommand to allocate a {@code Deadline} task to a {@code Group} or {@code Student}
     *
     * @param currPath The current path user is at in ProfBook.
     * @param root The root in ProfBook.
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     * @throws InvalidPathException thrown when error occurs due to invalid path.
     * @throws UnsupportedPathOperationException Exception thrown when error occurs due to unsupported path execution.
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
        try {
            requireAllNonNull(currPath, root);
            AbsolutePath absolutePath = currPath.resolve(path);
            CommandResult returnStatement;
            if (absolutePath.isStudentDirectory()) {
                GroupOperation groupOper = groupOperation(root, absolutePath);
                StudentId studentId = absolutePath.getStudentId().get();
                stu = groupOper.getChild(studentId);
                if (stu.checkDuplicates(deadline)) {
                    throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
                }
                StudentOperation studentOper = studentOperation(root, absolutePath);
                studentOper.addTask(deadline);
                returnStatement = new CommandResult(String.format(MESSAGE_SUCCESS, stu.toString()));
            } else if (absolutePath.isGroupDirectory()) {
                RootOperation rootOper = rootOperation(root);
                GroupId groupId = absolutePath.getGroupId().get();
                grp = rootOper.getChild(groupId);
                if (grp.checkDuplicates(deadline)) {
                    throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
                }
                GroupOperation groupOper = groupOperation(root, absolutePath);
                groupOper.addTask(deadline);
                returnStatement = new CommandResult(String.format(MESSAGE_SUCCESS, grp.toString()));
            } else {
                throw new CommandException(MESSAGE_INCORRECT_DIRECTORY_ERROR);
            }
            return returnStatement;
        } catch (InvalidPathException e) {
            throw new CommandException(MESSAGE_INVALID_PATH);
        } catch (UnsupportedPathOperationException e) {
            throw new CommandException(MESSAGE_UNSUPPORTED_PATH_OPERATION);
        }
    }

    /**
     * Compares this {@code CreateDeadlineCommand} to another {@code CreateDeadlineCommand} to see if they are equal.
     *
     * @param other The other object to compare against this {@code CreateDeadlineCommand}.
     * @return True if the object is same as {@code CreateDeadlineCommand} and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateDeadlineCommand)) {
            return false;
        }

        CreateDeadlineCommand otherCreateDeadlineCommand =
                (CreateDeadlineCommand) other;
        return this.deadline.equals(otherCreateDeadlineCommand.deadline)
                && this.path.equals(otherCreateDeadlineCommand.path);
    }

    /**
     * Returns a string representation of the {@code CreateDeadlineCommand}.
     *
     * @return String representation of the {@code CreateDeadlineCommand}.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateDeadline", this.deadline)
                .toString();
    }
}


