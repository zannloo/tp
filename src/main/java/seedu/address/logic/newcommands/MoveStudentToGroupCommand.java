package seedu.address.logic.newcommands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.profbook.Root;

/**
 * Represents a command for moving a student from one group to another within ProfBook.
 * This command is typically used to change the group affiliation of a student.
 */
public class MoveStudentToGroupCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String MESSAGE_SUCCESS = "New student added to this group: %1$s";

    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the group";

    private final RelativePath source;
    private final RelativePath dest;

    /**
     * Constructs a {@code MoveStudentToGroupCommand} with the specified source and destination paths.
     *
     * @param source The relative path to the source group from which the student will be moved.
     * @param dest   The relative path to the destination group to which the student will be moved.
     */
    public MoveStudentToGroupCommand(RelativePath source, RelativePath dest) {
        requireAllNonNull(source, dest);
        this.source = source;
        this.dest = dest;
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
