package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;

/**
 * Represents a command for creating a new group within ProfBook.
 * This command is typically used to create a new group at a specified path.
 */
public class CreateGroupCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";
    public static final String MESSAGE_DUPLICATE_GROUP_ID =
            "GroupId %1$s has already been used by the group: %2$s";
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
     * @param model The current model of the application.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If an error occurs while executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ChildOperation<Group> rootOperation = model.rootChildOperation();

        // Check duplicate group id
        if (model.hasGroupWithId(group.getId())) {
            Group groupWithSameId = model.getGroupWithId(group.getId());
            throw new CommandException(String.format(
                    MESSAGE_DUPLICATE_GROUP_ID, group.getId().toString(), Messages.format(groupWithSameId)));
        }

        rootOperation.addChild(this.group.getId(), this.group);
        model.updateList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.group)));
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
