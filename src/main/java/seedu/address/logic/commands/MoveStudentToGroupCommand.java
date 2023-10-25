package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Student;

/**
 * Represents a command for moving a student from one group to another within ProfBook.
 * This command is typically used to change the group affiliation of a student.
 */
public class MoveStudentToGroupCommand extends Command {

    public static final String COMMAND_WORD = "mv";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the group";
    public static final String MESSAGE_SOURCE_IS_NOT_STUDENT = "Source path must be a student directory";
    public static final String MESSAGE_DEST_IS_NOT_GROUP = "Destination path must be a group directory";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Source student not found in ProfBook";
    public static final String MESSAGE_GROUP_NOT_FOUND = "Destination Group not found in ProfBook";
    public static final String MESSAGE_MOVE_STUDENT_SUCCESS = "Student $1$s is moved to this group: %2$s";
    public static final String MESSAGE_INVALID_MOVE_COMMAND = "Move command is invalid.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student";

    private final AbsolutePath source;
    private final AbsolutePath dest;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param source The absolute path to the source group from which the student will be moved.
     * @param dest   The absolute path to the destination group to which the student will be moved.
     */
    public MoveStudentToGroupCommand(AbsolutePath source, AbsolutePath dest) {
        requireAllNonNull(source, dest);
        this.source = source;
        this.dest = dest;
    }

    /**
     * Executes the MoveStudentToGroupCommand, moving a student from the source group to the destination group in
     * ProfBook.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check move student to group
        if (source.isStudentDirectory() && dest.isGroupDirectory()) {
            // Check student exists in ProfBook
            if (!model.hasStudent(source)) {
                throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
            }

            // Check group exists in ProfBook
            if (!model.hasGroup(dest)) {
                throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
            }

            StudentId toBeMovedId = source.getStudentId().get();

            ChildOperation<Student> sourceGroup = model.groupChildOperation(source);
            Student studentToBeMoved = sourceGroup.getChild(toBeMovedId);

            ChildOperation<Student> destGroup = model.groupChildOperation(dest);

            destGroup.addChild(toBeMovedId, studentToBeMoved);
            sourceGroup.deleteChild(toBeMovedId);
            model.updateList();
            return new CommandResult(String.format(
                    MESSAGE_MOVE_STUDENT_SUCCESS, source.getStudentId().get(), dest.getGroupId().get()));
        }

        throw new CommandException(MESSAGE_INVALID_MOVE_COMMAND);
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
                .add("Source Path", source)
                .add("Dest Path", dest)
                .toString();
    }
}
