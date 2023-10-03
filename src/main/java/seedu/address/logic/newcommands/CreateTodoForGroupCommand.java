package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.ToDo;

/**
 * Represents a command for creating a new "ToDo" task within a specified group in ProfBook.
 * This command is typically used to add a "ToDo" task to a group.
 */
public class CreateTodoForGroupCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "New ToDo task added: %1$s";

    public static final String MESSAGE_DUPLICATE_TODO_TASK = "This ToDo task has already been allocated to "
            + "this group in ProfBook";

    private final RelativePath path;
    private final ToDo todo;

    /**
     * Constructs a {@code CreateTodoForGroupCommand} with the specified relative path and "ToDo" task details.
     *
     * @param path The relative path to the group where the "ToDo" task will be added.
     * @param todo The details of the "ToDo" task to be created.
     */
    public CreateTodoForGroupCommand(RelativePath path, ToDo todo) {
        requireAllNonNull(path, todo);
        this.path = path;
        this.todo = todo;
    }

    @Override
    public CommandResult execute(AbsolutePath currPah, Root root) throws CommandException {
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
