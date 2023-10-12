package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.RootOperation;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.ToDo;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * Represents a command for creating a new "ToDo" task in ProfBook.
 * This command is typically used to add a "ToDo" task.
 */
public class CreateTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "New ToDo task has been added to: %1$s";

    public static final String MESSAGE_DUPLICATE_TODO_TASK_STUDENT =
            "This ToDo task has already been allocated to this student in ProfBook";

    public static final String MESSAGE_DUPLICATE_TODO_TASK_GROUP =
            "This ToDo task has already been allocated to this group in ProfBook";

    public static final String MESSAGE_ERROR = "Invalid target encountered while creating this todo task";

    private final RelativePath relativePath;

    private final ToDo todo;

    /**
     * Constructs a {@code CreateTodoCommand} with the specified relative path and "ToDo" task details.
     *
     * @param relativePath The relative path to the group where the "ToDo" task will be added.
     * @param todo The details of the "ToDo" task to be created.
     */
    public CreateTodoCommand(RelativePath relativePath, ToDo todo) {
        requireAllNonNull(relativePath, todo);
        this.relativePath = relativePath;
        this.todo = todo;
    }

    /**
     * Executes the CreateTodoCommand, adding a "ToDo" task to either a group or a specific student as specified
     * in the relative path.
     *
     * @param currPath The current path in the ProfBook.
     * @param root The root of the ProfBook.
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws InvalidIdException If an invalid ID is encountered.
     * @throws InvalidPathException If an invalid path is encountered.
     * @throws NoSuchTaskException If the specified task does not exist.
     * @throws UnsupportedPathOperationException If an unsupported path operation is encountered.
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException, InvalidIdException,
            InvalidPathException, NoSuchTaskException, UnsupportedPathOperationException {
        requireAllNonNull(currPath, root);
        AbsolutePath absolutePath = currPath.resolve(relativePath);
        if (currPath.isStudentDirectory()) {
            StudentOperation studentOperation = StateManager.studentOperation(root, absolutePath);
            if (studentOperation.getAllTasks().contains(this.todo)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_STUDENT);
            }
            studentOperation.addTask(this.todo);
            GroupOperation groupOperation = StateManager.groupOperation(root, absolutePath);
            Student targetStudent = groupOperation.getChild(absolutePath.getStudentId());
            return new CommandResult(String.format(MESSAGE_SUCCESS, targetStudent.toString()));
        } else if (currPath.isGroupDirectory()) {
            GroupOperation groupOperation = StateManager.groupOperation(root, currPath);
            if (groupOperation.getAllTasks().contains(this.todo)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_GROUP);
            }
            groupOperation.addTask(this.todo);
            RootOperation rootOperation = StateManager.rootOperation(root);
            Group targetGroup = rootOperation.getChild(absolutePath.getGroupId());
            return new CommandResult(String.format(MESSAGE_SUCCESS, targetGroup.toString()));
        } else {
            throw new CommandException(MESSAGE_ERROR);
        }
    }

    /**
     * Checks if this CreateTodoCommand is equal to another object.
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
        if (!(other instanceof CreateTodoCommand)) {
            return false;
        }

        CreateTodoCommand otherCreateTodoCommand = (CreateTodoCommand) other;
        return this.todo.getDescription().equals(otherCreateTodoCommand.todo.getDescription());
    }

    /**
     * Returns the string representation of this CreateTodoCommand.
     *
     * @return A string representation of the CreateTodoCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateTodo", this.todo)
                .toString();
    }
}
