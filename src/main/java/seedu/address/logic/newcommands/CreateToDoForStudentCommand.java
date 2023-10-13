package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.ToDo;

/**
 * Adds a person to the address book.
 */
public class CreateToDoForStudentCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "New ToDo task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO_TASK =
            "This ToDo task has already been allocated to this student in ProfBook";

    private final RelativePath path;
    private final ToDo toDo;

    /**
     * Creates an CreateToDoForStudentCommand  to add the ToDo Task for a specified {@code Student}
     */
    public CreateToDoForStudentCommand(RelativePath path, ToDo toDo) {
        requireAllNonNull(path, toDo);
        this.path = path;
        this.toDo = toDo;

    }

    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
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
