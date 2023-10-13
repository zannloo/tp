package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.RootOperation;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.ToDo;

/**
 * Represents a command for creating a new "ToDo" task in ProfBook.
 * This command is typically used to add a "ToDo" task.
 */
public class CreateTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String ERROR_MESSAGE_DUPLICATE = "This Todo task has already been allocated";

    public static final String ERROR_MESSAGE_INVALID_PATH = "This path is invalid.";

    public static final String ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION = "Path operation is not supported";

    public static final String MESSAGE_DUPLICATE_TODO_TASK_STUDENT =
            "This ToDo task has already been allocated to this student in ProfBook";

    public static final String MESSAGE_DUPLICATE_TODO_TASK_GROUP =
            "This ToDo task has already been allocated to this group in ProfBook";

    public static final String MESSAGE_ERROR = "Invalid target encountered while creating this todo task";

    public static final String MESSAGE_SUCCESS = "New ToDo task has been added to: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student";

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
     */
    @Override
    public CommandResult execute(AbsolutePath currPath, Root root) throws CommandException {
        try {
            requireAllNonNull(currPath, root);
            AbsolutePath absolutePath = currPath.resolve(relativePath);
            if (currPath.isStudentDirectory()) {
                GroupOperation groupOperation = StateManager.groupOperation(root, absolutePath);
                StudentOperation studentOperation = StateManager.studentOperation(root, absolutePath);
                StudentId targetStudentId = absolutePath.getStudentId().get();
                Student targetStudent = groupOperation.getChild(targetStudentId);
                if (targetStudent.checkDuplicates(this.todo)) {
                    throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_STUDENT);
                }
                studentOperation.addTask(this.todo);
                return new CommandResult(String.format(MESSAGE_SUCCESS, targetStudent.toString()));
            } else if (currPath.isGroupDirectory()) {
                RootOperation rootOperation = StateManager.rootOperation(root);
                GroupOperation groupOperation = StateManager.groupOperation(root, currPath);
                GroupId targetGroupId = absolutePath.getGroupId().get();
                Group targetGroup = rootOperation.getChild(targetGroupId);
                if (targetGroup.checkDuplicates(this.todo)) {
                    throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_GROUP);
                }
                groupOperation.addTask(this.todo);
                return new CommandResult(String.format(MESSAGE_SUCCESS, targetGroup.toString()));
            } else {
                throw new CommandException(MESSAGE_ERROR);
            }
        } catch (DuplicateChildException duplicateChildException) {
            throw new CommandException(ERROR_MESSAGE_DUPLICATE);
        } catch (InvalidPathException invalidPathException) {
            throw new CommandException(ERROR_MESSAGE_INVALID_PATH);
        } catch (UnsupportedPathOperationException unsupportedPathOperationException) {
            throw new CommandException(ERROR_MESSAGE_UNSUPPORTED_PATH_OPERATION);
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
        return this.relativePath.equals(otherCreateTodoCommand.relativePath)
                && this.todo.equals(otherCreateTodoCommand.todo);
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
