package seedu.address.logic.commands;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.statemanager.State;

/**
 * Show Task List.
 */
public class ShowChildrenListCommand extends Command {
    public static final String COMMAND_WORD = "ls";
    public static final String MESSAGE_SUCCESS = "Show children List of %1$s";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook: %1$s";
    public static final String MESSAGE_NOT_CHILDREN_MANAGER = "Cannot show children list for this path: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommand.class);

    private final AbsolutePath target;

    public ShowChildrenListCommand() {
        target = null;
    }

    public ShowChildrenListCommand(AbsolutePath path) {
        target = path;
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
        if (target == null) {
            if (!state.hasChildrenListInCurrentPath()) {
                throw new CommandException(MESSAGE_NOT_CHILDREN_MANAGER);
            }
            state.setDisplayPath(state.getCurrPath());
            state.showChildrenList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, "current directory"));
        }

        // Check path exists in ProfBook
        if (!state.hasPath(target)) {
            throw new CommandException(String.format(MESSAGE_PATH_NOT_FOUND, target.toString()));
        }

        // Check path is children manager
        if (!state.hasChildrenListInPath(target)) {
            throw new CommandException(String.format(MESSAGE_NOT_CHILDREN_MANAGER, target.toString()));
        }

        state.setDisplayPath(target);
        state.showChildrenList();

        logger.fine("Showing children list for path: " + target.toString());

        return new CommandResult(String.format(MESSAGE_SUCCESS, target.toString()));
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
        if (!(other instanceof ShowChildrenListCommand)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of this MoveStudentToGroupCommand.
     *
     * @return A string representation of the MoveStudentToGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
