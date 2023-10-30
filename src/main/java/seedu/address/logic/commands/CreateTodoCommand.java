package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.TaskOperation;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ToDo;

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
    public static final String MESSAGE_SUCCESS_ALL_STUDENTS =
            "New ToDo task added to all students in group: %1$s";
    public static final String MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING =
            "Warning: Some student(s) already have the task. \n"
            + "New ToDo task has been added to the rest.";
    public static final String MESSAGE_SUCCESS_ALL_GROUPS =
            "New ToDo task added to all groups in root directory.";
    public static final String MESSAGE_SUCCESS_ALL_GROUPS_WITH_WARNING =
            "Warning: Some group(s) already have the task. \n"
            + "New ToDo task has been added to the rest.";
    public static final String MESSAGE_ERROR = "Invalid target encountered while creating this todo task";
    public static final String MESSAGE_SUCCESS = "New ToDo task has been added to: %1$s";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";
    public static final String MESSAGE_NOT_TASK_MANAGER = "Cannot create task for this path.";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_STU = "AllStu flag is only allowed for root and group path";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_GROUP = "AllGrp flag is only allowed for root path";
    public static final String MESSAGE_ALL_CHILDREN_HAVE_TASK = "All %1$ss already have the task.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": student";

    private final AbsolutePath target;
    private final ToDo todo;
    private Category category;

    /**
     * Constructs a {@code CreateTodoCommand} with the specified absolute path and "ToDo" task details.
     *
     * @param target   The absolute path to the group where the "ToDo" task will be added.
     * @param todo     The details of the "ToDo" task to be created.
     * @param category The specific category of people to add ToDo task to each.
     */
    public CreateTodoCommand(AbsolutePath target, ToDo todo, Category category) {
        requireAllNonNull(target, todo, category);
        this.target = target;
        this.todo = todo;
        this.category = category;
    }

    /**
     * Executes the CreateTodoCommand, adding a "ToDo" task to either a group or a specific student as specified
     * in the relative path.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch(category) {
        case NONE:
            return handleNone(model);
        case ALLSTU:
            return handleAllStu(model);
        default:
            return handleAllGrp(model);
        }
    }

    private CommandResult handleNone(Model model) throws CommandException {
        // Check path exists in ProfBook
        if (!model.hasPath(target)) {
            throw new CommandException(MESSAGE_PATH_NOT_FOUND);
        }

        TaskOperation taskOperation = model.taskOperation(target);
        if (taskOperation.hasTask(this.todo)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK_STUDENT);
        }
        taskOperation.addTask(this.todo);
        model.updateList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, target));
    }

    private CommandResult handleAllStu(Model model) throws CommandException {
        if (target.isStudentDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_STU);
        }

        if (target.isGroupDirectory()) {
            ChildOperation<Student> groupOper = model.groupChildOperation(target);

            // Check whether all children already have the task
            if (groupOper.checkIfAllChildrenHaveTask(todo, 1)) {
                throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "student"));
            }

            // Check whether at least one of the children has the task
            boolean warning = false;
            if (groupOper.checkIfAnyChildHasTask(todo, 1)) {
                warning = true;
            }

            groupOper.addTaskToAllChildren(todo, 1);
            model.updateList();
            return new CommandResult(
                    warning ? MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING
                            : String.format(MESSAGE_SUCCESS_ALL_STUDENTS, target.getGroupId().get()));
        }

        ChildOperation<Group> operation = model.rootChildOperation();

        // Check whether all children already have the task
        if (operation.checkIfAllChildrenHaveTask(todo, 2)) {
            throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "student"));
        }

        // Check whether at least one of the children has the task
        boolean warning = false;
        if (operation.checkIfAnyChildHasTask(todo, 2)) {
            warning = true;
        }

        operation.addTaskToAllChildren(todo, 2);
        model.updateList();
        return new CommandResult(
                warning ? MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING
                        : String.format(MESSAGE_SUCCESS_ALL_STUDENTS, target.getGroupId().get()));
    }

    private CommandResult handleAllGrp(Model model) throws CommandException {
        if (!target.isRootDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_GROUP);
        }
        ChildOperation<Group> rootOper = model.rootChildOperation();

        // Check whether all children already have the task
        if (rootOper.checkIfAllChildrenHaveTask(todo, 1)) {
            throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "group"));
        }

        // Check whether at least one of the children has the task
        boolean warning = false;
        if (rootOper.checkIfAnyChildHasTask(todo, 1)) {
            warning = true;
        }

        rootOper.addTaskToAllChildren(todo, 1);
        model.updateList();
        return new CommandResult(warning ? MESSAGE_SUCCESS_ALL_GROUPS_WITH_WARNING : MESSAGE_SUCCESS_ALL_GROUPS);
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
        return this.target.equals(otherCreateTodoCommand.target)
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
