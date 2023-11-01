package seedu.address.logic.commands;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.path.AbsolutePath;

/**
 * Show Task List.
 */
public class ShowChildrenListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_SUCCESS = "Show children List of %1$s";

    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook: %1$s";

    public static final String MESSAGE_NOT_CHILDREN_MANAGER = "Cannot show children list for this path: %1$s";

    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " [path] \n"
            + "\n"
            + "Display the children list.\n"
            + "\n"
            + "Option: \n"
            + "    path                 Valid path to group\n"
            + "\n"
            + "Examples: \n"
            + "ls \n"
            + "ls grp-001";

    public static final ShowChildrenListCommand HELP_MESSAGE = new ShowChildrenListCommand(true);

    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommand.class);

    private final AbsolutePath target;
    private final boolean isHelp;

    /**
     * Constructs {@code ShowChildrenListCommand} that show children list of current directory.
     */
    public ShowChildrenListCommand() {
        target = null;
        isHelp = false;
    }

    /**
     * Constructs {@code ShowChildrenListCommand} that show children list of path given.
     */
    public ShowChildrenListCommand(AbsolutePath path) {
        target = path;
        isHelp = false;
    }

    private ShowChildrenListCommand(boolean isHelp) {
        target = null;
        this.isHelp = true;
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
        if (isHelp) {
            return new CommandResult(MESSAGE_USAGE);
        }

        if (target == null) {
            if (!model.hasChildrenListInCurrentPath()) {
                throw new CommandException(MESSAGE_NOT_CHILDREN_MANAGER);
            }
            model.setDisplayPath(model.getCurrPath());
            model.showChildrenList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, "current directory"));
        }

        // Check path exists in ProfBook
        if (!model.hasPath(target)) {
            throw new CommandException(String.format(MESSAGE_PATH_NOT_FOUND, target));
        }

        // Check path is children manager
        if (!model.hasChildrenListInPath(target)) {
            throw new CommandException(String.format(MESSAGE_NOT_CHILDREN_MANAGER, target));
        }

        model.setDisplayPath(target);
        model.showChildrenList();

        logger.fine("Showing children list for path: " + target);

        return new CommandResult(String.format(MESSAGE_SUCCESS, target));
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
        return other instanceof ShowChildrenListCommand;
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
