package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;

/**
 * Represents a command for creating a new group in ProfBook.
 */
public class CreateGroupCommand extends Command {

    /**
     * The command word for creating a group.
     */
    public static final String COMMAND_WORD = "mkdir";

    /**
     * Error message indicating that a group with the provided ID already exists.
     */
    public static final String MESSAGE_DUPLICATE_GROUP_ID =
            "GroupId %1$s has already been used by the group: %2$s";

    /**
     * Message indicating successful creating a new group.
     */
    public static final String MESSAGE_SUCCESS = "New group added: %1$s";

    /**
     * A special instance of CreateGroupCommand used to display the command's usage message.
     */
    public static final CreateGroupCommand HELP_MESSAGE = new CreateGroupCommand() {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    /**
     * Logger for logging messages related to EditCommand.
     */
    private static final Logger logger = LogsCenter.getLogger(CreateGroupCommand.class);

    /**
     * Usage information for the 'CreateGroupCommand' command.
     */
    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " <path>" + " -n <name>\n"
            + "\n"
            + "Create new group.\n"
            + "\n"
            + "Argument: \n"
            + "    path                 Valid path to a new group\n"
            + "    -n, --name           Name for the new group\n"
            + "\n"
            + "Option: \n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "mkdir grp-001 -n Group One";

    /**
     * Represents the destination path where the new group will be created.
     */
    private final AbsolutePath dest;

    /**
     * Represents the group to be created.
     */
    private final Group group;

    /**
     * Constructs a CreateGroupCommand for creating a new group with the specified absolute path and group.
     *
     * @param dest  The absolute path at which the new group will be created.
     * @param group The group to be created.
     */
    public CreateGroupCommand(AbsolutePath dest, Group group) {
        requireAllNonNull(dest, group);
        this.dest = dest;
        this.group = group;
    }

    /**
     * Private constructor for creating the HELP_MESSAGE instance.
     */
    private CreateGroupCommand() {
        this.dest = null;
        this.group = null;
    }

    /**
     * Executes the CreateGroupCommand to create a new group within ProfBook at the specified path.
     *
     * @param model The model on which the command should be executed.
     * @return A CommandResult containing a message indicating the success of creating a new group.
     * @throws CommandException If there exist any error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.info("Executing create group command...");

        ChildOperation<Group> rootOperation = model.rootChildOperation();

        // Check duplicate group id
        if (model.hasGroupWithId(group.getId())) {
            Group groupWithSameId = model.getGroupWithId(group.getId());
            logger.warning("The GroupId " + group.getId().toString() + " has already been used by other group. "
                    + "Aborting create group command.");
            throw new CommandException(String.format(
                    MESSAGE_DUPLICATE_GROUP_ID, group.getId().toString(), Messages.format(groupWithSameId)));
        }

        logger.info("Creating a new group...");

        rootOperation.addChild(this.group.getId(), this.group);
        model.updateList();

        logger.info("New group added successfully.");

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.group)));
    }

    /**
     * Checks if this CreateGroupCommand is equal to another object.
     *
     * @param other The object to compare with this CreateGroupCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CreateGroupCommand)) {
            return false;
        }

        CreateGroupCommand otherCreateGroupCommand = (CreateGroupCommand) other;
        return this.dest.equals(otherCreateGroupCommand.dest)
                && this.group.equals(otherCreateGroupCommand.group);
    }

    /**
     * Returns the string representation of this CreateGroupCommand.
     *
     * @return The string representation of this CreateGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateGroup", this.group)
                .toString();
    }
}
