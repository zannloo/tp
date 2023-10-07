package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupOperation;
import static seedu.address.model.statemanager.StateManager.studentOperation;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.ToDo;

/**
 * Adds a person to the address book.
 */
public class CreateToDoForStudentCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "New ToDo task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO_TASK =
            "This ToDo task has already been allocated to this student in ProfBook";
    protected AbsolutePath absolutePath;
    protected Student stu;
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
    public CommandResult execute(AbsolutePath currPath, Root root) throws
            CommandException, InvalidPathException, UnsupportedPathOperationException, InvalidIdException {
        requireAllNonNull(currPath, root);
        AbsolutePath absolutePath = currPath.resolve(path);
        StudentOperation student = studentOperation(root, absolutePath);
        student.addTask(toDo);
        GroupOperation group = groupOperation(root, absolutePath);
        stu = group.getChild(absolutePath.getStudentId());
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(stu)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateToDoForStudentCommand)) {
            return false;
        }

        CreateToDoForStudentCommand otherCreateToDoForStudentCommand = (CreateToDoForStudentCommand) other;
        return toDo.getDescription().equals(otherCreateToDoForStudentCommand.toDo.getDescription())
                && this.absolutePath.equals(otherCreateToDoForStudentCommand.absolutePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateToDoForStudent", stu)
                .toString();
    }
}
