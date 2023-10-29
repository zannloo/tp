package seedu.address.model.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.GroupId;
import seedu.address.model.profbook.Name;


public class EditGroupDescriptorTest {

    @Test
    public void testCopyConstructor() {
        EditGroupDescriptor originalDescriptor = new EditGroupDescriptor();
        originalDescriptor.setName(new Name("Group 1"));
        originalDescriptor.setId(new GroupId("grp-001"));

        EditGroupDescriptor copiedDescriptor = new EditGroupDescriptor(originalDescriptor);

        assertEquals(originalDescriptor, copiedDescriptor);
    }

    @Test
    public void testIsAnyFieldEdited() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();

        // No fields edited, so it should return false
        assertFalse(descriptor.isAnyFieldEdited());

        descriptor.setName(new Name("Group 1"));
        assertTrue(descriptor.isAnyFieldEdited());

        // Reset the name to null
        descriptor.setName(null);
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void testSetNameAndGetName() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        Name name = new Name("Group 1");

        assertFalse(descriptor.getName().isPresent());

        // Set the name
        descriptor.setName(name);
        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void testSetIdAndGetId() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        GroupId groupId = new GroupId("grp-001");

        assertFalse(descriptor.getId().isPresent());

        // Set the id
        descriptor.setId(groupId);
        assertTrue(descriptor.getId().isPresent());
        assertEquals(groupId, descriptor.getId().get());
    }

    @Test
    public void equals() {
        EditGroupDescriptor firstEditGroupDescriptor = new EditGroupDescriptor();
        EditGroupDescriptor secondEditGroupDescriptor = new EditGroupDescriptor();

        // same object -> returns true
        assertEquals(firstEditGroupDescriptor, firstEditGroupDescriptor);

        // same values -> returns true
        GroupId groupId = new GroupId("grp-001");
        Name name = new Name("Group 1");
        firstEditGroupDescriptor.setId(groupId);
        firstEditGroupDescriptor.setName(name);

        EditGroupDescriptor firstEditGroupDescriptorCopy = new EditGroupDescriptor();
        firstEditGroupDescriptorCopy.setId(groupId);
        firstEditGroupDescriptorCopy.setName(name);

        assertEquals(firstEditGroupDescriptor, firstEditGroupDescriptorCopy);

        // different types -> returns false
        assertNotEquals(1, firstEditGroupDescriptor);

        // null -> returns false
        assertNotEquals(null, firstEditGroupDescriptor);

        // different object -> returns false
        secondEditGroupDescriptor.setId(new GroupId("grp-002"));
        secondEditGroupDescriptor.setName(new Name("Group 2"));
        assertNotEquals(firstEditGroupDescriptor, secondEditGroupDescriptor);
    }
}
