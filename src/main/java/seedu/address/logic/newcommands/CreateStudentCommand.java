package seedu.address.logic.newcommands;

//import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;
//import seedu.address.logic.newcommands.Command;
//import seedu.address.logic.newcommands.CommandResult;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
//import seedu.address.model.path.element.PathElement;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;

/**
 * Adds a student within the specific group.
 * If command is executed outside a specific group, students are added into an ungrouped folder.
 */
public class CreateStudentCommand extends Command {

    public static final String COMMAND_WORD = "touch";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in ProfBook";

    private final RelativePath path;
    private final Student student;

    /**
     * Creates an CreateStudentCommand to add the specified {@code Student}
     */
    public CreateStudentCommand(RelativePath path, Student student) {
        requireAllNonNull(path, student);
        this.path = path;
        this.student = student;
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
