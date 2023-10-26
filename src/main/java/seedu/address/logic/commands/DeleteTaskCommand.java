package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Task;

/**
 * Deletes a task identified using it's displayed index on display panel.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "rmt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "[display index] + \n"
            + "Constraint: Task list must be shown on display panel using \"cat\" command. + \n"
            + "Parameters: display index (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_TASK_LIST_NOT_SHOWN = "Current display panel is not displaying task list.";
    public static final String MESSAGE_INVALID_INDEX = "The task list provided is invalid.";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";
    private static final Logger logger = LogsCenter.getLogger(DeleteTaskCommand.class);

    private final Index targetIndex;

    /**
     * Construct a DeleteTaskCommand instance with target index.
     */
    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        logger.info("Executing delete task command...");

        // Check if diplay panel is displaying task list
        if (!state.isShowTaskList()) {
            logger.warning("Task list is not shown. Aborting delete task command.");
            throw new CommandException(MESSAGE_TASK_LIST_NOT_SHOWN);
        }

        TaskOperation taskOperation = state.taskOperation(state.getDisplayPath());

        // Check if index is valid.
        if (!taskOperation.isValidIndex(targetIndex.getOneBased())) {
            logger.warning("Invalid index: " + targetIndex.getOneBased() + ". Aborting delete task command.");
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        logger.info("Executing delete task command on index " + targetIndex.getOneBased());

        Task deletedTask = taskOperation.deleteTask(targetIndex.getOneBased());
        state.updateList();

        logger.info("Task deleted successfully. Deleted task: " + deletedTask.toString());

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        DeleteTaskCommand otherDeleteCommand = (DeleteTaskCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
