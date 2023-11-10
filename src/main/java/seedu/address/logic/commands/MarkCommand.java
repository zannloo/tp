package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.Messages.MESSAGE_TASK_LIST_NOT_SHOWN;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskOperation;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.task.Task;

/**
 * The MarkCommand class represents a command for marking a task in the task list.
 * This command is typically used in a context where the user is viewing a list of tasks.
 * It marks a specific task in the list as completed.
 */
public class MarkCommand extends Command {

    /**
     * The command word for marking a task.
     */
    public static final String COMMAND_WORD = "mark";

    /**
     * Message indicating successful marking of a task.
     */
    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked task: %1$s";

    /**
     * Usage information for the 'mark' command.
     */
    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " <index>\n"
            + "\n"
            + "Mark the task with the given display index as done.\n"
            + "Must use \'cat\' command before mark task.\n"
            + "\n"
            + "Argument: \n"
            + "    index                Valid task index number\n"
            + "\n"
            + "Option: \n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "mark 1";

    public static final MarkCommand HELP_MESSAGE = new MarkCommand() {
        @Override
        public CommandResult execute(Model model) throws CommandException {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    private static final Logger logger = LogsCenter.getLogger(MarkCommand.class);

    private final Index index;

    /**
     * Constructs a MarkCommand with the specified task index to be marked.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    private MarkCommand() {
        this.index = null;
    }

    /**
     * Executes the MarkCommand to mark a task as completed.
     *
     * @param model The current model of the application.
     * @return A CommandResult containing a message indicating the success of the marking operation.
     * @throws CommandException If the command cannot be executed due to an incorrect model (not showing task list).
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.info("Executing mark task command...");

        if (!model.isShowTaskList()) {
            logger.warning("Task list is not shown. Aborting mark task command.");
            throw new CommandException(MESSAGE_TASK_LIST_NOT_SHOWN);
        }

        AbsolutePath displayPath = model.getDisplayPath();
        TaskOperation taskOperation = model.taskOperation(displayPath);

        // Check if index is valid.
        if (!taskOperation.isValidIndex(this.index.getOneBased())) {
            logger.warning("Invalid index: " + this.index.getOneBased() + ". Aborting mark task command.");
            throw new CommandException(
                    String.format(MESSAGE_INVALID_INDEX, taskOperation.getTaskListSize(), index.getOneBased()));
        }

        logger.info("Executing mark task command on index " + this.index.getOneBased());

        Task markedTask = taskOperation.markTask(this.index.getOneBased());
        model.updateList();

        logger.info("Task marked successfully. Marked task: " + markedTask.toString());

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask));
    }

    /**
     * Checks if this MarkCommand is equal to another object. Two MarkCommand objects are considered equal
     * if they have the same task index to be marked.
     *
     * @param other The object to compare with this MarkCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return this.index.equals(otherMarkCommand.index);
    }

    /**
     * Returns a string representation of this MarkCommand, including its index.
     *
     * @return A string representation of the MarkCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", this.index)
                .toString();
    }
}
