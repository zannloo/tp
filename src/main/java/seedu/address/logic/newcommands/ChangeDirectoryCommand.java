package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.logic.parser.newcommandparser.ChangeDirectoryCommandParser;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.statemanager.State;

/**
 * Change directory to target path.
 */
public class ChangeDirectoryCommand extends Command {
    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_SUCCESS = "Changed directory to: %1$s";
    public static final String MESSAGE_INVALID_DEST = "Student path is not navigable.";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";
    private static final Logger logger = LogsCenter.getLogger(ChangeDirectoryCommandParser.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [destination path]";

    private final RelativePath dest;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param source The relative path to the source group from which the student will be moved.
     * @param dest   The relative path to the destination group to which the student will be moved.
     */
    public ChangeDirectoryCommand(RelativePath dest) {
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
    public CommandResult execute(State state) throws CommandException {
        AbsolutePath currPath = state.getCurrPath();
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(dest);
        } catch (InvalidPathException e) {
            throw new CommandException(e.getMessage());
        }

        if (!state.hasPath(targetPath)) {
            throw new CommandException(MESSAGE_PATH_NOT_FOUND);
        }

        if (targetPath.isStudentDirectory()) {
            throw new CommandException(MESSAGE_INVALID_DEST);
        }

        state.changeDirectory(targetPath);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetPath.toString()));
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
