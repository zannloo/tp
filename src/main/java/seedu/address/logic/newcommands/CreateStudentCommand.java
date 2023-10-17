package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;

/**
 * Adds a student within the specific group.
 */
public class CreateStudentCommand extends Command {

    public static final String COMMAND_WORD = "touch";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in your specified class";
    public static final String MESSAGE_INVALID_PATH = "Path you have chosen is invalid";
    public static final String MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    public static final String MESSAGE_GROUP_NOT_FOUND = "Group %1$s does not exist in ProfBook";

    private final RelativePath path;
    private final Student student;

    /**
     * Creates an CreateStudentCommand to add the specified {@code Student}
     */
    public CreateStudentCommand(RelativePath path, Student student) {
        requireAllNonNull(path, student);
        this.path = path;
        this.student = student;
    }

    /**
     * Executes an CreateStudentCommand to add the specified {@code Student} to a {@code Group}
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);
        AbsolutePath currPath = state.getCurrPath();

        // Check resolved path is valid
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new CommandException(e.getMessage());
        }

        // Check group exists in ProfBook
        if (!state.hasGroup(targetPath)) {
            throw new CommandException(String.format(MESSAGE_GROUP_NOT_FOUND, targetPath.getGroupId()));
        }

        ChildOperation<Student> target = state.groupChildOperation(targetPath);

        // Check duplicate student
        if (target.hasChild(this.student.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        target.addChild(this.student.getId(), this.student);
        state.updateList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, student));
    }

    /**
     * Compares this {@code CreateStudentCommand} to another {@code CreateStudentCommand} to see if they are equal.
     *
     * @param other The other object to compare against this {@code CreateStudentCommand}.
     * @return True if the object is same as {@code CreateStudentCommand} and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateStudentCommand)) {
            return false;
        }

        CreateStudentCommand otherCreateStudentCommand = (CreateStudentCommand) other;

        return student.equals(otherCreateStudentCommand.student)
                && this.path.equals(otherCreateStudentCommand.path);
    }

    /**
     * Returns a string representation of the {@code CreateStudentCommand}.
     *
     * @return String representation of the {@code CreateStudentCommand}.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateStudent", student)
                .toString();
    }
}
