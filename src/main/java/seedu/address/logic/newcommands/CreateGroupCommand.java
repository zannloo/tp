package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;

/**
 * Represents a command for creating a new group within ProfBook.
 * This command is typically used to create a new group at a specified path.
 */
public class CreateGroupCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in ProfBook";

    private final RelativePath path;

    private final Group group;

    /**
     * Constructs a {@code CreateGroupCommand} with the specified relative path and group details.
     *
     * @param path  The relative path at which the new group will be created.
     * @param group The details of the group to be created.
     */
    public CreateGroupCommand(RelativePath path, Group group) {
        requireAllNonNull(path, group);
        this.path = path;
        this.group = group;
    }

    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
