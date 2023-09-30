package seedu.address.model.id;

import seedu.address.model.id.exceptions.InvalidIdException;

/**
 * The {@code StudentId} class represents a student identifier (ID)
 * with a specific format.
 */
public class StudentId extends Id {
    /**
     * A validator object for checking the format of student IDs.
     * Student IDs must match the pattern "stu-" followed by three digits.
     */
    public static final IdValidator STUDENT_ID_VALIDATOR = new IdValidator("stu-\\d{3}");

    /**
     * Constructs a {@code StudentId} object with the specified ID value.
     * Use the {@link #createStudentId(String)} factory method for ID creation and validation.
     *
     * @param id The student ID value to store.
     */
    private StudentId(String id) {
        super(id);
    }

    /**
     * Creates a new {@code StudentId} object with the specified ID value, while also validating its format.
     *
     * @param id The student ID value to create and validate.
     * @return A valid {@code StudentId} object.
     * @throws InvalidIdException If the provided ID does not match the expected format.
     */
    public static StudentId createStudentId(String id) throws InvalidIdException {
        if (!STUDENT_ID_VALIDATOR.isValid(id)) {
            throw new InvalidIdException("Invalid student id format.");
        }
        return new StudentId(id);
    }
}
