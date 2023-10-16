package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;

/**
 * Represents a command for creating a new group within ProfBook.
 * This command is typically used to create a new group at a specified path.
 */
public class CreateGroupCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in ProfBook";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": group";

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
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        AbsolutePath currentPath = state.getCurrPath();

        // Check resolved path is valid
        AbsolutePath targetPath = null;
        try {
            targetPath = currentPath.resolve(relativePath);
        } catch (InvalidPathException e) {
            throw new CommandException(e.getMessage());
        }

        ChildOperation<Group> rootOperation = state.rootChildOperation();

        // Check duplicate group
        if (rootOperation.hasChild(targetPath.getGroupId().get())) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        rootOperation.addChild(this.group.getId(), this.group);
        state.updateList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.group));
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
