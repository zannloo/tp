package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;
import static seedu.address.model.statemanager.StateManager.rootOperation;
import static seedu.address.model.statemanager.StateManager.studentOperation;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
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
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * Adds a person to the address book.
 */
public class CreateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE_TASK =
            "This Deadline task has already been allocated";

    protected AbsolutePath absolutePath;
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

    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException,
            InvalidPathException, UnsupportedPathOperationException, InvalidIdException, NoSuchTaskException {
        requireAllNonNull(currPath, root);
        absolutePath = currPath.resolve(path);
        CommandResult returnStatement = null;
        if (absolutePath.isStudentDirectory()) {
            StudentOperation student = studentOperation(root, absolutePath);
            if (student.getAllTasks()
                    .stream()
                    .filter(x -> x.getDescription().equals(this.deadline.getDescription())) != null) {
                throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
            }
            student.addTask(deadline);
            GroupOperation group = groupOperation(root, absolutePath);
            StudentId temp = absolutePath.getStudentId().get();
            stu = group.getChild(temp);
            returnStatement = new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(stu)));
        } else if (absolutePath.isGroupDirectory()) {
            GroupOperation group = groupOperation(root, absolutePath);
            if (group.getAllTasks()
                    .stream()
                    .filter(x -> x.getDescription().equals(this.deadline.getDescription())) != null) {
                throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
            }
            group.addTask(deadline);
            RootOperation currRoot = rootOperation(root);
            GroupId temp = absolutePath.getGroupId().get();
            grp = currRoot.getChild(temp);
            returnStatement = null;
            //return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(grp)));
        }
        return returnStatement;
    }

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

        return deadline.getDescription().equals(otherCreateDeadlineCommand.deadline.getDescription())
                && deadline.getDueBy().equals(otherCreateDeadlineCommand.deadline.getDueBy())
                && this.absolutePath.equals(otherCreateDeadlineCommand.absolutePath);
    }

    @Override
    public String toString() { //have to make this such that it fits for both stu and group
        return new ToStringBuilder(this)
                .add("toCreateDeadline", stu)
                .toString();
    }
}


