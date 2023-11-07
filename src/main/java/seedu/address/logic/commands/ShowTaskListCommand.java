package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;

import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.path.AbsolutePath;

/**
 * Shows task list.
 */
public class ShowTaskListCommand extends Command {

    public static final String COMMAND_WORD = "cat";

    public static final String MESSAGE_SUCCESS = "Show task list of %1$s";

    public static final String MESSAGE_NOT_TASK_MANAGER = "Cannot show task list for this path: %1$s";

    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " [path] \n"
            + "\n"
            + "Display the task list of the target path (the current directory by default).\n"
            + "\n"
            + "Option: \n"
            + "    path                 Valid path to group or student\n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "cat grp-001 \n"
            + "cat grp-001/0001Y";

    public static final ShowTaskListCommand HELP_MESSAGE = new ShowTaskListCommand() {
        @Override
        public CommandResult execute(Model model) throws CommandException {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommand.class);

    private final AbsolutePath target;

    /**
     * Constructs {@code ShowChildrenListCommand} that shows task list of current directory.
     */
    public ShowTaskListCommand() {
        target = null;
    }

    /**
     * Constructs {@code ShowChildrenListCommand} that shows children list of path given.
     */
    public ShowTaskListCommand(AbsolutePath path) {
        requireNonNull(path);
        target = path;
    }

    /**
     * Executes the {@code ShowTaskListCommand}, shows the task list of the target path.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // If target is null default to current path
        if (target == null) {
            return handleCurrPath(model);
        }

        return handleTargetPath(model);
    }

    /**
     * Shows task list of current path.
     */
    private CommandResult handleCurrPath(Model model) throws CommandException {
        AbsolutePath currPath = model.getCurrPath();

        logger.fine("Executing show task list command with target path: " + currPath);

        // Checks if current path is task list manager
        if (!model.hasTaskListInCurrentPath()) {
            throw new CommandException(String.format(MESSAGE_NOT_TASK_MANAGER, currPath.toString()));
        }

        logger.fine("Showing task list of path: " + currPath);

        model.setDisplayPath(currPath);
        model.showTaskList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, currPath.toString()));
    }

    /**
     * Shows task list of target path.
     */
    private CommandResult handleTargetPath(Model model) throws CommandException {
        logger.fine("Executing show task list command with target path: " + target);

        // Checks if path exists in ProfBook
        if (!model.hasPath(target)) {
            throw new CommandException(String.format(MESSAGE_PATH_NOT_FOUND, target));
        }

        // Checks if path is task manager
        if (!model.hasTaskListInPath(target)) {
            throw new CommandException(String.format(MESSAGE_NOT_TASK_MANAGER, target));
        }

        logger.fine("Showing task list of path: " + target);

        model.setDisplayPath(target);
        model.showTaskList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ShowTaskListCommand)) {
            return false;
        }

        ShowTaskListCommand otherShowTaskListCommand = (ShowTaskListCommand) other;

        return Objects.equals(this.target, otherShowTaskListCommand.target);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPath", target)
                .toString();
    }
}
