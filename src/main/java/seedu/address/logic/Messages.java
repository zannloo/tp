package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Option;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicateOptions(Option... duplicateOptions) {
        assert duplicateOptions.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicateOptions).map(Option::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code student} for display to the user.
     */
    public static String format(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n Name: ")
                .append(student.getName())
                .append("\n Phone: ")
                .append(student.getPhone())
                .append("\n Email: ")
                .append(student.getEmail())
                .append("\n Address: ")
                .append(student.getAddress())
                .append("\n StudentId: ")
                .append(student.getId());
        return builder.toString();
    }

    /**
     * Formats the {@code group} for display to the user.
     */
    public static String format(Group group) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n Name: ")
                .append(group.getName())
                .append("\n GroupId: ")
                .append(group.getId());
        return builder.toString();
    }
}
