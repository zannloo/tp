package seedu.address.model.id;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a student identifier (ID).
 */
public class StudentId extends Id {
    public static final String VALIDATION_REGEX = "\\d{4}[a-zA-Z]";
    public static final String MESSAGE_CONSTRAINTS =
        "Student Id should be 4 digits number follow by any letter, and it should not be blank.";

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
     * Returns true if given string is a valid student id.
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
