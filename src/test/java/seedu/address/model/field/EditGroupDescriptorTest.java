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
    public void constructor_descriptorToBeCopied_descriptorCopiedSuccess() {
        EditGroupDescriptor originalDescriptor = new EditGroupDescriptor();
        originalDescriptor.setName(new Name("Group 1"));
        originalDescriptor.setId(new GroupId("grp-001"));

        EditGroupDescriptor copiedDescriptor = new EditGroupDescriptor(originalDescriptor);

        assertEquals(originalDescriptor, copiedDescriptor);
    }

    @Test
    public void isAnyFieldEdited_oneFieldEdited_true() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        descriptor.setName(new Name("Group 1"));

        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_noFieldEdited_false() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();

        // No fields edited, so it should return false
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void setName_setNameForEditGroupDescriptor_nonEmptyOptional() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        Name name = new Name("Group 1");
        descriptor.setName(name);

        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void getName_getNameOfEditGroupDescriptor_nonEmptyOptional() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        Name name = new Name("Group 1");
        descriptor.setName(name);

        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void setId_setIdForEditGroupDescriptor_nonEmptyOptional() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        GroupId groupId = new GroupId("grp-001");
        descriptor.setId(groupId);

        assertTrue(descriptor.getId().isPresent());
        assertEquals(groupId, descriptor.getId().get());
    }

    @Test
    public void getId_getIdOfEditGroupDescriptor_nonEmptyOptional() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        GroupId groupId = new GroupId("grp-001");
        descriptor.setId(groupId);

        assertTrue(descriptor.getId().isPresent());
        assertEquals(groupId, descriptor.getId().get());
    }

    @Test
    public void get_getFieldsOfEditGroupDescriptor_emptyOptional() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();

        assertFalse(descriptor.getName().isPresent());
        assertFalse(descriptor.getId().isPresent());
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

        EditGroupDescriptor firstEditGroupDescriptorCopy = new EditGroupDescriptor(firstEditGroupDescriptor);

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

    @Test
    public void toString_noChangeField_allFieldsNull() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();

        String expected = EditGroupDescriptor.class.getCanonicalName()
                + "{name=" + null + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeName_nameNotNull() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        descriptor.setName(new Name("Group 1"));

        String expected = EditGroupDescriptor.class.getCanonicalName()
                + "{name=Group 1" + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeId_idNotNull() {
        EditGroupDescriptor descriptor = new EditGroupDescriptor();
        descriptor.setId(new GroupId("grp-001"));

        String expected = EditGroupDescriptor.class.getCanonicalName()
                + "{name=" + null + ", id=GRP-001" + "}";

        assertEquals(expected, descriptor.toString());
    }
}
