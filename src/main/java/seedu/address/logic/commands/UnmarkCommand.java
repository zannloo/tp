package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Task;

/**
 * The UnmarkCommand class represents a command for unmarking a previously marked task in the task list.
 * This command is typically used in a context where the user is viewing a list of tasks.
 * It unmarks a specific task in the list, indicating that it is no longer completed.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_INCORRECT_STATE = "The current state is not showing task list.";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Unmarked task: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [task index]";

    public static final String MESSAGE_INVALID_INDEX = "The task index provided is invalid.";

    private static final Logger logger = LogsCenter.getLogger(UnmarkCommand.class);

    private final Index index;

    /**
     * Constructs an UnmarkCommand with the specified task index to be unmarked.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand to unmark a previously marked task.
     *
     * @param state The current state of the application.
     * @return A CommandResult containing a message indicating the success of the unmarking operation.
     * @throws CommandException If the command cannot be executed due to an incorrect state (not showing the task list).
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        logger.info("Executing unmark task command...");

        if (!state.isShowTaskList()) {
            logger.warning("Task list is not shown. Aborting unmark task command.");
            throw new CommandException(MESSAGE_INCORRECT_STATE);
        }

        AbsolutePath displayPath = state.getDisplayPath();
        TaskOperation taskOperation = state.taskOperation(displayPath);

        // Check if index is valid.
        if (!taskOperation.isValidIndex(this.index.getOneBased())) {
            logger.warning("Invalid index: " + this.index.getOneBased() + ". Aborting unmark task command.");
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        logger.info("Executing unmark task command on index " + this.index.getOneBased());

        Task ummarkedTask = taskOperation.unmarkTask(this.index.getOneBased());
        state.updateList();

        logger.info("Task unmarked successfully. Unmarked task: " + ummarkedTask.toString());

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, ummarkedTask));
    }

    /**
     * Checks if this UnmarkCommand is equal to another object. Two UnmarkCommand objects are considered equal
     * if they have the same task index to be unmarked.
     *
     * @param other The object to compare with this UnmarkCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        UnmarkCommand otherUnmarkCommand = (UnmarkCommand) other;
        return this.index.equals(otherUnmarkCommand.index);
    }

    /**
     * Returns a string representation of this UnmarkCommand, including its index.
     *
     * @return A string representation of the UnmarkCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", this.index)
                .toString();
    }
}
