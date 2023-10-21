package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
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

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        // Check if diplay panel is displaying task list
        if (!state.isShowTaskList()) {
            throw new CommandException(MESSAGE_TASK_LIST_NOT_SHOWN);
        }

        TaskOperation taskOperation = state.taskOperation(state.getDisplayPath());

        // Check if index is valid.
        if (!taskOperation.isValidIndex(targetIndex.getOneBased())) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Task deletedTask = taskOperation.deleteTask(targetIndex.getOneBased());
        state.updateList();

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
