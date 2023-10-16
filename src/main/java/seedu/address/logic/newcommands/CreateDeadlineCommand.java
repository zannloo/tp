package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.statemanager.StateManager.groupChildOperation;
import static seedu.address.model.statemanager.StateManager.rootChildOperation;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Deadline;

/**
 * Adds a Deadline for a specified {@code Student} or {@code Group}.
 */
public class CreateDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deadline ";
    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";

    public static final String MESSAGE_SUCCESS_ALL_STUDENTS =
            "New Deadline task added to all students in group: %1$s";
    public static final String MESSAGE_SUCCESS_ALL_GROUPS =
            "New Deadline task added to all groups in root: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE_TASK =
            "This Deadline task has already been allocated";
    public static final String MESSAGE_PATH_NOT_FOUND = "Path does not exist in ProfBook.";
    public static final String MESSAGE_NOT_TASK_MANAGER = "Cannot create task for this path.";
    protected Student stu;
    protected Group grp;
    private final RelativePath path;
    private final Deadline deadline;
    private String category = null;
    private CommandResult returnStatement = null;

    /**
     * Creates an CreateDeadlineCommand to add the Deadline Task for a specified {@code Student} or {@code Group}
     */
    public CreateDeadlineCommand(RelativePath path, Deadline deadline) {
        requireAllNonNull(path, deadline);
        this.path = path;
        this.deadline = deadline;
    }

    /**
     * Creates an CreateDeadlineCommand to add the Deadline Task for a specified {@code Student} or {@code Group}
     * User has input a category as well.
     */
    public CreateDeadlineCommand(RelativePath path, Deadline deadline, String category) {
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
    public CommandResult execute(State state) throws CommandException {
        AbsolutePath currPath = state.getCurrPath();
        // Check resolved path is valid
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new CommandException(e.getMessage());
        }

        // Check path exists in ProfBook
        if (!state.hasPath(targetPath)) {
            throw new CommandException(MESSAGE_PATH_NOT_FOUND);
        }

        if (this.category == null) {
            // Check target path is task manager
            if (!state.hasTaskListInPath(targetPath)) {
                throw new CommandException(MESSAGE_NOT_TASK_MANAGER);
            }

            TaskOperation target = state.taskOperation(targetPath);

            // Check duplicate deadline
            if (target.hasTask(this.deadline)) {
                throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
            }

            target.addTask(this.deadline);
            state.updateList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));
        }

        if (this.category.equals("allStu")) {
            if (!targetPath.isGroupDirectory()) {
                throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_STU);
            }
            ChildOperation<Student> groupOper = state.groupChildOperation(targetPath);
            List<Student> allStudents = groupOper.getAllChildren();
            for (Student s : allStudents) {
                StudentId studentId = (StudentId) s.getId();
                AbsolutePath newPath = targetPath.resolve(new RelativePath(studentId.toString()));
                TaskOperation target = state.taskOperation(newPath);
                if (target.hasTask(this.deadline)) {
                    throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
                }
                target.addTask(this.deadline);
            }
            return new CommandResult(MESSAGE_SUCCESS_ALL_STUDENTS);
        }

        if (!targetPath.isRootDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_GROUP);
        }

        ChildOperation<Group> rootOper = state.rootChildOperation();
        List<Group> allGroups = rootOper.getAllChildren();

        for (Group g : allGroups) {
            GroupId groupId = (GroupId) g.getId();
            AbsolutePath newPath = targetPath.resolve(new RelativePath(groupId.toString()));
            TaskOperation target = state.taskOperation(newPath);
            if (target.hasTask(this.deadline)) {
                throw new CommandException(MESSAGE_DUPLICATE_DEADLINE_TASK);
            }
            target.addTask(this.deadline);
        }

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


