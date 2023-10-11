package seedu.address.model.id;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * The {@code GroupId} class represents a group identifier (ID)
 * with a specific format.
 */
public class GroupId extends Id {
    public static final String VALIDATION_REGEX = "grp-\\d{3}";
    public static final String MESSAGE_CONSTRAINTS =
            "Group Id should be in the format 'grp-XXX' where XXX is a 3-digit number.";

    /**
     * Private constructor to create a {@code GroupId} object with a valid group id string.
     *
     * @param id The valid group Id.
     */
    public GroupId(String id) {
        super(id);
        checkArgument(isValidGroupId(id), MESSAGE_CONSTRAINTS);
    }

    /**
     * Return true if given string is a valid group id.
     */
    public static boolean isValidGroupId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroupId)) {
            return false;
        }

        GroupId otherStudentId = (GroupId) other;
        return id.equals(otherStudentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, "GroupId");
    }
}
