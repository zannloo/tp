package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.GroupOperation;

/**
 * Adds a student within the specific group.
 * If command is executed outside a specific group, students are added into an ungrouped folder.
 */
public class CreateStudentCommand extends Command {

    public static final String COMMAND_WORD = "touch";


    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in your specified class";
    protected AbsolutePath absolutePath;

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
     * @param currPath The current path user is at in ProfBook.
     * @param root The root in ProfBook.
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     * @throws InvalidPathException thrown when error occurs due to invalid path.
     * @throws UnsupportedPathOperationException Exception thrown when error occurs due to unsupported path execution.
     * @throws InvalidIdException Exception thrown due to invalid Id which includes GroupId and StudentId.
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException,
            InvalidPathException, UnsupportedPathOperationException, InvalidIdException {
        requireAllNonNull(currPath, root);
        absolutePath = currPath.resolve(path);
        GroupOperation group = groupOperation(root, absolutePath);
        StudentId studentId = (StudentId) student.getId();
        if (group.hasChild(studentId)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }
        group.addChild(studentId, student);
        return new CommandResult(String.format(MESSAGE_SUCCESS, student.toString()));
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
        return student.equals(otherCreateStudentCommand.student);
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
