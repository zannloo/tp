package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;

/**
 * Adds a student within the specific group.
 */
public class CreateStudentCommand extends Command {

    public static final String COMMAND_WORD = "touch";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Adds a student into the specified directory\n"
            + "Parameters: "
            + "specified path "
            + "[" + OPTION_NAME + " NAME] "
            + "[" + OPTION_EMAIL + " EMAIL] "
            + "[" + OPTION_PHONE + " PHONE] "
            + "[" + OPTION_ADDRESS + " ADDRESS] "
            + "Example: " + COMMAND_WORD
            + " stu-200 "
            + OPTION_NAME + " Bob "
            + OPTION_EMAIL + " bobby@example.com "
            + OPTION_PHONE + " 92929292 "
            + OPTION_ADDRESS + " blk 258 Toa Payoh ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in your specified class";
    public static final String MESSAGE_INVALID_PATH = "Path provided should be a valid student path";
    public static final String MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";
    public static final String MESSAGE_GROUP_NOT_FOUND = "Group %1$s does not exist in ProfBook";

    private final AbsolutePath path;
    private final Student student;

    /**
     * Creates an CreateStudentCommand to add the specified {@code Student}
     */
    public CreateStudentCommand(AbsolutePath path, Student student) {
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

        if (!path.isStudentDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH);
        }

        // Check group exists in ProfBook
        if (!state.hasGroup(path)) {
            throw new CommandException(String.format(MESSAGE_GROUP_NOT_FOUND, path.getGroupId()));
        }

        ChildOperation<Student> target = state.groupChildOperation(path);

        // Check duplicate student
        if (target.hasChild(this.student.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        target.addChild(this.student.getId(), this.student);
        state.updateList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(student)));
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
