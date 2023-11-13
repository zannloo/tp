package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Student;

/**
 * Represents a command for moving a student from one group to another in ProfBook.
 * This command is typically used to change the group affiliation of a student.
 */
public class MoveStudentToGroupCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String MESSAGE_STUDENT_NOT_FOUND = "Source student not found in ProfBook";

    public static final String MESSAGE_GROUP_NOT_FOUND = "Destination group not found in ProfBook";

    public static final String MESSAGE_MOVE_STUDENT_SUCCESS =
            "Student %1$s has been successfully moved to the group: %2$s";

    public static final String MESSAGE_INVALID_MOVE_COMMAND =
            "Invalid source or destination.\n\n"
            + "source:       existing student\n"
            + "destination:  existing group";

    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " <source>" + " <destination> \n"
            + "\n"
            + "Move a student from source group to destination group.\n"
            + "\n"
            + "Argument: \n"
            + "    source               Valid path to source student\n"
            + "    destination          Valid path to destination group\n"
            + "\n"
            + "Option: \n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "mv grp-001/0001Y grp-002";

    public static final MoveStudentToGroupCommand HELP_MESSAGE = new MoveStudentToGroupCommand() {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    private static final Logger logger = LogsCenter.getLogger(MoveStudentToGroupCommand.class);

    private final AbsolutePath source;

    private final AbsolutePath dest;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param source The absolute path to the source group from which the student will be moved.
     * @param dest   The absolute path to the destination group to which the student will be moved.
     */
    public MoveStudentToGroupCommand(AbsolutePath source, AbsolutePath dest) {
        requireAllNonNull(source, dest);
        this.source = source;
        this.dest = dest;
    }

    /**
     * Private constructor for creating the HELP_MESSAGE instance.
     */
    private MoveStudentToGroupCommand() {
        this.source = null;
        this.dest = null;
    }

    /**
     * Executes the MoveStudentToGroupCommand, moving a student from the source group to the destination group.
     *
     * @param model The model on which the command should be executed.
     * @return A CommandResult containing a message indicating the success of the moving operation.
     * @throws CommandException If there exist any error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.finer("Executing move student to group command...");

        if (source.isStudentDirectory() && dest.isGroupDirectory()) {
            if (!model.hasStudent(source)) {
                logger.warning("Student to be moved does not exist. Aborting move student to group command.");
                throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
            }
            if (!model.hasGroup(dest)) {
                logger.warning("Destination group does not exist. Aborting move student to group command.");
                throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
            }
            StudentId toBeMovedId = source.getStudentId().get();
            logger.finer("Moving student " + toBeMovedId + "...");
            ChildOperation<Student> sourceGroup = model.groupChildOperation(source);
            Student studentToBeMoved = sourceGroup.getChild(toBeMovedId);
            ChildOperation<Student> destGroup = model.groupChildOperation(dest);
            destGroup.addChild(toBeMovedId, studentToBeMoved);
            sourceGroup.deleteChild(toBeMovedId);
            model.updateList();
            logger.finer("Student " + toBeMovedId + " has been successfully moved to destination group.");
            return new CommandResult(String.format(MESSAGE_MOVE_STUDENT_SUCCESS, toBeMovedId, dest.getGroupId().get()));
        }

        logger.warning("Invalid command. Aborting move student to group command.");
        throw new CommandException(MESSAGE_INVALID_MOVE_COMMAND);
    }

    /**
     * Checks if this MoveStudentToGroupCommand is equal to another object.
     *
     * @param other The object to compare with this MoveStudentToGroupCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MoveStudentToGroupCommand)) {
            return false;
        }

        MoveStudentToGroupCommand otherMoveStudentToGroupCommand = (MoveStudentToGroupCommand) other;
        return this.source.equals(otherMoveStudentToGroupCommand.source)
                && this.dest.equals(otherMoveStudentToGroupCommand.dest);
    }

    /**
     * Returns the string representation of this MoveStudentToGroupCommand.
     *
     * @return The string representation of this MoveStudentToGroupCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Source Path", source)
                .add("Destination Path", dest)
                .toString();
    }
}
