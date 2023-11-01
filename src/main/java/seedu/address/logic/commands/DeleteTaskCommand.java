package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskOperation;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index on display panel.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "rmt";

    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " <task index>\n"
            + "\n"
            + "Delete a task with the specified index number.\n"
            + "\n"
            + "Argument: \n"
            + "    task index           Valid path to group or student\n"
            + "\n"
            + "Examples: \n"
            + "rmt 1";

    public static final String MESSAGE_TASK_LIST_NOT_SHOWN = "Current display panel is not displaying task list.";

    public static final String MESSAGE_INVALID_INDEX = "Index %1$s is invalid for task list with size %2$s";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";

    public static final DeleteTaskCommand HELP_MESSAGE = new DeleteTaskCommand();

    private static final Logger logger = LogsCenter.getLogger(DeleteTaskCommand.class);

    private final Index targetIndex;
    private final boolean isHelp;

    /**
     * Construct a DeleteTaskCommand instance with target index.
     */
    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.isHelp = false;
    }

    private DeleteTaskCommand() {
        this.targetIndex = null;
        this.isHelp = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isHelp) {
            return new CommandResult(MESSAGE_USAGE);
        }

        logger.info("Executing delete task command...");

        // Check if diplay panel is displaying task list
        if (!model.isShowTaskList()) {
            logger.warning("Task list is not shown. Aborting delete task command.");
            throw new CommandException(MESSAGE_TASK_LIST_NOT_SHOWN);
        }

        TaskOperation taskOperation = model.taskOperation(model.getDisplayPath());

        // Check if index is valid.
        if (!taskOperation.isValidIndex(targetIndex.getOneBased())) {
            logger.warning("Invalid index: " + targetIndex.getOneBased() + ". Aborting delete task command.");
            throw new CommandException(
                    String.format(MESSAGE_INVALID_INDEX, targetIndex.getOneBased(), taskOperation.getTaskListSize()));
        }

        logger.info("Executing delete task command on index " + targetIndex.getOneBased());

        Task deletedTask = taskOperation.deleteTask(targetIndex.getOneBased());
        model.updateList();

        logger.info("Task deleted successfully. Deleted task: " + deletedTask.toString());

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask));
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
