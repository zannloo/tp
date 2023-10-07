package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;
import static seedu.address.model.statemanager.StateManager.studentOperation;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.Deadline;

/**
 * Adds a person to the address book.
 */
public class CreateDeadlineForStudentCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO_TASK =
            "This Deadline task has already been allocated to this student in ProfBook";

    protected AbsolutePath absolutePath;
    protected Student stu;
    private final RelativePath path;
    private final Deadline deadline;

    /**
     * Creates an CreateDeadlineForStudentCommand to add the Deadline Task for a specified {@code Student}
     */
    public CreateDeadlineForStudentCommand(RelativePath path, Deadline deadline) {
        requireAllNonNull(path, deadline);
        this.path = path;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws
            CommandException, InvalidPathException, UnsupportedPathOperationException, InvalidIdException {
        requireAllNonNull(currPath, root);
        absolutePath = currPath.resolve(path);
        StudentOperation student = studentOperation(root, absolutePath);
        student.addTask(deadline);
        GroupOperation group = groupOperation(root, absolutePath);
        stu = group.getChild(absolutePath.getStudentId());
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(stu)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateDeadlineForStudentCommand)) {
            return false;
        }

        CreateDeadlineForStudentCommand otherCreateDeadlineForStudentCommand =
                (CreateDeadlineForStudentCommand) other;

        return deadline.getDescription().equals(otherCreateDeadlineForStudentCommand.deadline.getDescription())
                && deadline.getDueBy().equals(otherCreateDeadlineForStudentCommand.deadline.getDueBy())
                && this.absolutePath.equals(otherCreateDeadlineForStudentCommand.absolutePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateDeadlineForStudent", stu)
                .toString();
    }
}
