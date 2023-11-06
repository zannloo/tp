package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.exceptions.InvalidPathException;

public class GroupIdTest {
    @Test
    public void constructor_validGroupId_validGroupIdObject() {
        GroupId groupId = new GroupId("grp-001");
        assertEquals("grp-001", groupId.toString().toLowerCase());
    }

    @Test
    public void constructor_invalidGroupId_throwsIllegalValueException() {
        assertThrows(IllegalArgumentException.class, () -> new GroupId("invalidGroupId"));
    }

    @Test
    public void isValidGroupId_validGroupId_true() {
        assertTrue(GroupId.isValidGroupId("grp-001"));
    }

    @Test
    public void isValidGroupId_invalidGroupId_false() {
        assertFalse(GroupId.isValidGroupId("invalidGroupId"));
    }

    @Test
    public void equalsMethod() {
        GroupId groupId1 = new GroupId("grp-001");
        GroupId groupId1Copy = new GroupId("grp-001");
        GroupId groupId2 = new GroupId("grp-002");
        StudentId studentId = new StudentId("0001Y");

        // same object -> returns true
        assertEquals(groupId1, groupId1);

        // same values -> returns true
        assertEquals(groupId1Copy, groupId1);

        // different types -> returns false
        assertNotEquals(studentId, groupId1);

        // null -> returns false
        assertNotEquals(null, groupId1);

        // different values
        assertNotEquals(groupId1, groupId2);
    }

    @Test
    public void hashCodeMethod() throws InvalidPathException {
        GroupId groupId1 = new GroupId("grp-001");
        GroupId groupId1Copy = new GroupId("grp-001");
        GroupId groupId2 = new GroupId("grp-002");

        // same value -> returns true
        assertEquals(groupId1.hashCode(), groupId1Copy.hashCode());

        // different values -> returns false
        assertNotEquals(groupId1.hashCode(), groupId2.hashCode());
    }
}
