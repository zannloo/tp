package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.TaskOperation;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.Deadline;

/**
 * Adds a Deadline for a specified {@code Student} or {@code Group}.
 */
public class CreateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a deadline task for student/group in specified directory\n"
            + "Parameters: "
            + "specified path\n"
            + "[" + OPTION_DESC + " DESCRIPTION] "
            + "[" + OPTION_DATETIME + " DATE_AND_TIME] "
            + "[" + OPTION_ALL + " CATERGORY]\n"
            + "Example: " + COMMAND_WORD
            + " stu-001 "
            + OPTION_DESC + " Assignment 1 "
            + OPTION_DATETIME + " 2023-10-11 23:59 ";
    public static final String MESSAGE_SUCCESS = "New Deadline task added to Student/Group: %1$s";

    public static final String MESSAGE_SUCCESS_ALL_STUDENTS =
            "New Deadline task added to all students in group: %1$s";
    public static final String MESSAGE_SUCCESS_ALL_GROUPS =
            "New Deadline task added to all groups in root: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE_TASK =
            "This Deadline task has already been allocated";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";
    public static final String MESSAGE_NOT_TASK_MANAGER = "Cannot create task for this path.";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_STU = "All stu flag is only allowed for group path";
    public static final String MESSAGE_INVALID_PATH_FOR_ALL_GROUP = "All Group flag is only allowed for root path";

    private final AbsolutePath path;
    private final Deadline deadline;
    private String category = null;

    /**
     * Creates an CreateDeadlineCommand to add the Deadline Task for a specified {@code Student} or {@code Group}
     */
    public CreateDeadlineCommand(AbsolutePath path, Deadline deadline) {
        requireAllNonNull(path, deadline);
        this.path = path;
        this.deadline = deadline;
    }

    /**
     * Creates an CreateDeadlineCommand to add the Deadline Task for a specified {@code Student} or {@code Group}
     * User has input a category as well.
     */
    public CreateDeadlineCommand(AbsolutePath path, Deadline deadline, String category) {
        requireAllNonNull(path, deadline, category);
        this.path = path;
        this.deadline = deadline;
        this.category = category;
    }

    /**
     * Executes an CreateDeadlineCommand to allocate a {@code Deadline} task to a {@code Group} or {@code Student}
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Check path exists in ProfBook
        if (!model.hasPath(path)) {
            throw new CommandException(MESSAGE_PATH_NOT_FOUND);
        }

        if (this.category == null) {
            // Check target path is task manager
            if (!model.hasTaskListInPath(path)) {
                throw new CommandException(MESSAGE_NOT_TASK_MANAGER);
            }

            TaskOperation target = model.taskOperation(path);

            // Check duplicate deadline
            if (target.hasTask(this.deadline)) {
                throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
            }

            target.addTask(this.deadline);
            model.updateList();

            return new CommandResult(String.format(MESSAGE_SUCCESS, this.deadline));
        }

        if (this.category.equals("allStu")) {
            if (!path.isGroupDirectory()) {
                throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_STU);
            }
            ChildOperation<Student> groupOper = model.groupChildOperation(path);
            groupOper.addTaskToAllChildren(deadline, 1);
            model.updateList();
            return new CommandResult(MESSAGE_SUCCESS_ALL_STUDENTS);
        }

        if (!path.isRootDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_GROUP);
        }

        ChildOperation<Group> rootOper = model.rootChildOperation();
        rootOper.addTaskToAllChildren(deadline, 1);
        model.updateList();

        return new CommandResult(MESSAGE_SUCCESS_ALL_GROUPS);
    }

    /**
     * Compares this {@code CreateDeadlineCommand} to another {@code CreateDeadlineCommand} to see if they are equal.
     *
     * @param other The other object to compare against this {@code CreateDeadlineCommand}.
     * @return True if the object is same as {@code CreateDeadlineCommand} and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateDeadlineCommand)) {
            return false;
        }

        CreateDeadlineCommand otherCreateDeadlineCommand =
                (CreateDeadlineCommand) other;
        return this.deadline.equals(otherCreateDeadlineCommand.deadline)
                && this.path.equals(otherCreateDeadlineCommand.path);
    }

    /**
     * Returns a string representation of the {@code CreateDeadlineCommand}.
     *
     * @return String representation of the {@code CreateDeadlineCommand}.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateDeadline", this.deadline)
                .toString();
    }
}


