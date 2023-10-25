package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
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

    private final AbsolutePath dest;

    private final Group group;

    /**
     * Constructs a {@code CreateGroupCommand} with the specified absolute path and group details.
     *
     * @param dest  The absolute path at which the new group will be created.
     * @param group The details of the group to be created.
     */
    public CreateGroupCommand(AbsolutePath dest, Group group) {
        requireAllNonNull(dest, group);
        this.dest = dest;
        this.group = group;
    }

    /**
     * Executes the CreateGroupCommand to create a new group within ProfBook at the specified path.
     *
     * @param state The current state of the application.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If an error occurs while executing the command.
     */
    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);

        ChildOperation<Group> rootOperation = state.rootChildOperation();

        // Check duplicate group
        if (rootOperation.hasChild(dest.getGroupId().get())) {
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
        return this.dest.equals(otherCreateGroupCommand.dest)
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
