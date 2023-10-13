package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.StateManager;

/**
 * Represents a command for moving a student from one group to another within ProfBook.
 * This command is typically used to change the group affiliation of a student.
 */
public class MoveStudentToGroupCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String ERROR_MESSAGE_DUPLICATE = "This student has already been allocated";

    public static final String ERROR_MESSAGE_INVALID_PATH = "This path is invalid.";

    public static final String ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";

    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the group";

    public static final String MESSAGE_SUCCESS = "New student added to this group: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student";
    private final RelativePath source;

    private final RelativePath dest;

    private Student studentToBeMoved;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param source The relative path to the source group from which the student will be moved.
     * @param dest   The relative path to the destination group to which the student will be moved.
     */
    public MoveStudentToGroupCommand(RelativePath source, RelativePath dest) {
        requireAllNonNull(source, dest);
        this.source = source;
        this.dest = dest;
    }

    /**
     * Executes the MoveStudentToGroupCommand, moving a student from the source group to the destination group in
     * ProfBook.
     *
     * @param currPath The current path in the ProfBook.
     * @param root The root of the ProfBook.
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
        try {
            requireAllNonNull(currPath, root);
            AbsolutePath absolutePathSourceGroup = currPath.resolve(this.source);
            if (!source.isStudentDirectory()) {
                throw new CommandException("Source is not a student directory.");
            }
            GroupOperation sourceGroupOperation = StateManager.groupOperation(root, absolutePathSourceGroup);
            StudentId targetStudentId = absolutePathSourceGroup.getStudentId().get();
            studentToBeMoved = sourceGroupOperation.getChild(targetStudentId);

            AbsolutePath absolutePathDestinationGroup = currPath.resolve(this.dest);
            GroupOperation destinationGroupOperation = StateManager.groupOperation(root, absolutePathDestinationGroup);
            Student[] listOfStudentsInDestinationGroup = destinationGroupOperation.getAllChildren();
            for (Student student : listOfStudentsInDestinationGroup) {
                if (student.equals(studentToBeMoved)) {
                    throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
                }
            }
            destinationGroupOperation.addChild(targetStudentId, studentToBeMoved);
            sourceGroupOperation.deleteChild(targetStudentId);
            return new CommandResult(String.format(MESSAGE_SUCCESS, studentToBeMoved));
        } catch (DuplicateChildException duplicateChildException) {
            throw new CommandException(ERROR_MESSAGE_DUPLICATE);
        } catch (InvalidPathException invalidPathException) {
            throw new CommandException(ERROR_MESSAGE_INVALID_PATH);
        } catch (UnsupportedPathOperationException unsupportedPathOperationException) {
            throw new CommandException(ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION);
        }
    }

    /**
     * Checks if this MoveStudentToGroupCommand is equal to another object.
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
        if (!(other instanceof MoveStudentToGroupCommand)) {
            return false;
        }

        MoveStudentToGroupCommand otherMoveStudentToGroupCommand = (MoveStudentToGroupCommand) other;
        return this.source.equals(otherMoveStudentToGroupCommand.source)
                && this.dest.equals(otherMoveStudentToGroupCommand.dest);
    }

    /**
     * Returns a string representation of this MoveStudentToGroupCommand.
     *
     * @return A string representation of the MoveStudentToGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toMoveThisStudentToAnotherGroup", studentToBeMoved)
                .toString();
    }
}
