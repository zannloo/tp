package seedu.address.model.id;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * The {@code StudentId} class represents a student identifier (ID)
 * with a specific format.
 */
public class StudentId extends Id {
    public static final String VALIDATION_REGEX = "\\d{4}[a-zA-Z]";
    public static final String MESSAGE_CONSTRAINTS =
        "The string should be 4 digits number follow with any letter.";

    /**
     * Constructs a {@code StudentId} object with the valid ID value.
     *
     * @param id A valid student id.
     */
    public StudentId(String id) {
        super(id);
        checkArgument(isValidStudentId(id), MESSAGE_CONSTRAINTS);
    }

    /**
     * Return true if given string is a valid studentId
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherStudentId = (StudentId) other;
        return id.equals(otherStudentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, "StudentId");
    }
}
