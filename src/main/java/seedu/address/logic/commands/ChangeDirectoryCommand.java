package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.path.AbsolutePath;


/**
 * Change directory to target path.
 */
public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";

    public static final String MESSAGE_SUCCESS = "Changed directory to: %1$s";

    public static final String MESSAGE_INVALID_DEST = "Student path is not navigable.";

    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [destination path]";

    private final AbsolutePath dest;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param dest   The relative path to the destination group to which the student will be moved.
     */
    public ChangeDirectoryCommand(AbsolutePath dest) {
        requireAllNonNull(dest);
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
        if (!model.hasPath(dest)) {
            throw new CommandException(MESSAGE_PATH_NOT_FOUND);
        }

        if (dest.isStudentDirectory()) {
            throw new CommandException(MESSAGE_INVALID_DEST);
        }

        model.changeDirectory(dest);

        return new CommandResult(String.format(MESSAGE_SUCCESS, dest));
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
        if (!(other instanceof ChangeDirectoryCommand)) {
            return false;
        }

        ChangeDirectoryCommand otherChangeDirectoryCommand = (ChangeDirectoryCommand) other;
        return this.dest.equals(otherChangeDirectoryCommand.dest);
    }

    /**
     * Returns a string representation of this MoveStudentToGroupCommand.
     *
     * @return A string representation of the MoveStudentToGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Destination", dest)
                .toString();
    }
}
