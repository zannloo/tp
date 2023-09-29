package seedu.address.model.id;

import seedu.address.model.id.exceptions.InvalidIdException;

/**
 * The {@code GroupId} class represents a group identifier (ID)
 * with a specific format.
 */
public class GroupId extends Id {
    /**
     * A validator object for checking the format of group IDs.
     * Group IDs must match the pattern "grp-" followed by three digits.
     */
    public static final IdValidator GROUP_ID_VALIDATOR = new IdValidator("grp-\\d{3}");

    /**
     * Private constructor to create a {@code GroupId} object with the specified ID value.
     * Use the {@link #createGroupId(String)} factory method for ID creation and validation.
     *
     * @param id The group ID value to store.
     */
    private GroupId(String id) {
        super(id);
    }

    /**
     * Creates a new {@code GroupId} object with the specified ID value, while also validating its format.
     *
     * @param id The group ID value to create and validate.
     * @return A valid {@code GroupId} object.
     * @throws InvalidIdException If the provided ID does not match the expected format.
     */
    public GroupId createGroupId(String id) throws InvalidIdException {
        if (!GROUP_ID_VALIDATOR.isValid(id)) {
            throw new InvalidIdException("Invalid group id format.");
        }
        return new GroupId(id);
    }
}
