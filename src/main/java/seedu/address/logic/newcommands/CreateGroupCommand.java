package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.RootOperation;
import seedu.address.model.statemanager.StateManager;

/**
 * Represents a command for creating a new group within ProfBook.
 * This command is typically used to create a new group at a specified path.
 */
public class CreateGroupCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in ProfBook";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    private final RelativePath relativePath;

    private final Group group;

    /**
     * Constructs a {@code CreateGroupCommand} with the specified relative path and group details.
     *
     * @param relativePath  The relative path at which the new group will be created.
     * @param group The details of the group to be created.
     */
    public CreateGroupCommand(RelativePath relativePath, Group group) {
        requireAllNonNull(relativePath, group);
        this.relativePath = relativePath;
        this.group = group;
    }

    /**
     * Executes the CreateGroupCommand, creating a new group at the specified path in ProfBook.
     *
     * @param currPath The current path in the ProfBook.
     * @param root The root of the ProfBook.
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
        try {
            requireAllNonNull(currPath, root);
            RootOperation rootOperation = StateManager.rootOperation(root);
            Group[] listOfGroups = rootOperation.getAllChildren();
            for (Group group : listOfGroups) {
                if (group.equals(this.group)) {
                    throw new CommandException(MESSAGE_DUPLICATE_GROUP);
                }
            }
            Id groupId = this.group.getId();
            rootOperation.addChild(groupId, this.group);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.group.toString()));
        } catch (DuplicateChildException duplicateChildException) {
            return new CommandResult(MESSAGE_DUPLICATE_GROUP);
        }
    }

    /**
     * Checks if this CreateGroupCommand is equal to another object.
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
        if (!(other instanceof CreateGroupCommand)) {
            return false;
        }

        CreateGroupCommand otherCreateGroupCommand = (CreateGroupCommand) other;
        return this.relativePath.equals(otherCreateGroupCommand.relativePath)
                && this.group.equals(otherCreateGroupCommand.group);
    }

    /**
     * Returns a string representation of this CreateGroupCommand.
     *
     * @return A string representation of the CreateGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateGroup", this.group)
                .toString();
    }
}
