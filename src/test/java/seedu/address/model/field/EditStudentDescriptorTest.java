package seedu.address.model.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;

public class EditStudentDescriptorTest {

    @Test
    public void constructor_descriptorToBeCopied_descriptorCopiedSuccess() {
        EditStudentDescriptor originalDescriptor = new EditStudentDescriptor();
        originalDescriptor.setName(new Name("Ben"));
        originalDescriptor.setPhone(new Phone("656321"));
        originalDescriptor.setEmail(new Email("ben@gmail.com"));
        originalDescriptor.setAddress(new Address("123, Jurong West Ave 6, #08-111"));
        originalDescriptor.setId(new StudentId("0001Y"));

        EditStudentDescriptor copiedDescriptor = new EditStudentDescriptor(originalDescriptor);

        assertEquals(originalDescriptor, copiedDescriptor);
    }

    @Test
    public void isAnyFieldEdited_oneFieldEdited_true() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setName(new Name("Ben"));

        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_noFieldEdited_false() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();

        // No fields edited, so it should return false
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void setName_setNameForEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Name name = new Name("Ben");
        descriptor.setName(name);

        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void getName_getNameOfEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Name name = new Name("Ben");
        descriptor.setName(name);

        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void setPhone_setPhoneForEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Phone phone = new Phone("95427854");
        descriptor.setPhone(phone);

        assertTrue(descriptor.getPhone().isPresent());
        assertEquals(phone, descriptor.getPhone().get());
    }

    @Test
    public void getPhone_getPhoneOfEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Phone phone = new Phone("95427854");
        descriptor.setPhone(phone);

        assertTrue(descriptor.getPhone().isPresent());
        assertEquals(phone, descriptor.getPhone().get());
    }

    @Test
    public void setAddress_setAddressForEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Address address = new Address("123, Jurong West Ave 6, #08-111");
        descriptor.setAddress(address);

        assertTrue(descriptor.getAddress().isPresent());
        assertEquals(address, descriptor.getAddress().get());
    }

    @Test
    public void getAddress_getAddressOfEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Address address = new Address("123, Jurong West Ave 6, #08-111");
        descriptor.setAddress(address);

        assertTrue(descriptor.getAddress().isPresent());
        assertEquals(address, descriptor.getAddress().get());
    }

    @Test
    public void setEmail_setEmailForEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Email email = new Email("ben@gmail.com");
        descriptor.setEmail(email);

        assertTrue(descriptor.getEmail().isPresent());
        assertEquals(email, descriptor.getEmail().get());
    }

    @Test
    public void getEmail_getEmailOfEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Email email = new Email("ben@gmail.com");
        descriptor.setEmail(email);

        assertTrue(descriptor.getEmail().isPresent());
        assertEquals(email, descriptor.getEmail().get());
    }

    @Test
    public void setId_setIdForEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        StudentId studentId = new StudentId("0001Y");
        descriptor.setId(studentId);

        assertTrue(descriptor.getId().isPresent());
        assertEquals(studentId, descriptor.getId().get());
    }

    @Test
    public void getId_getIdOfEditStudentDescriptor_nonEmptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        StudentId studentId = new StudentId("0001Y");
        descriptor.setId(studentId);

        assertTrue(descriptor.getId().isPresent());
        assertEquals(studentId, descriptor.getId().get());
    }

    @Test
    public void get_getFieldsOfEditGroupDescriptor_emptyOptional() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();

        assertFalse(descriptor.getName().isPresent());
        assertFalse(descriptor.getPhone().isPresent());
        assertFalse(descriptor.getAddress().isPresent());
        assertFalse(descriptor.getEmail().isPresent());
        assertFalse(descriptor.getId().isPresent());
    }


    @Test
    public void equals() {
        EditStudentDescriptor firstEditStudentDescriptor = new EditStudentDescriptor();
        EditStudentDescriptor secondEditStudentDescriptor = new EditStudentDescriptor();
        // same object -> returns true
        assertEquals(firstEditStudentDescriptor, firstEditStudentDescriptor);

        // same values -> returns true
        firstEditStudentDescriptor.setName(new Name("Ben"));
        firstEditStudentDescriptor.setPhone(new Phone("656321"));
        firstEditStudentDescriptor.setEmail(new Email("ben@gmail.com"));
        firstEditStudentDescriptor.setAddress(new Address("123, Jurong West Ave 6, #08-111"));
        firstEditStudentDescriptor.setId(new StudentId("0001Y"));

        EditStudentDescriptor firstEditStudentDescriptorCopy = new EditStudentDescriptor(firstEditStudentDescriptor);
        assertEquals(firstEditStudentDescriptor, firstEditStudentDescriptorCopy);

        // different types -> returns false
        assertNotEquals(1, firstEditStudentDescriptor);

        // null -> returns false
        assertNotEquals(null, firstEditStudentDescriptor);

        // different object -> returns false
        secondEditStudentDescriptor.setName(new Name("Ken"));
        secondEditStudentDescriptor.setPhone(new Phone("659921"));
        secondEditStudentDescriptor.setEmail(new Email("ken@gmail.com"));
        secondEditStudentDescriptor.setAddress(new Address("13, Jurong West Ave 10, #08-211"));
        secondEditStudentDescriptor.setId(new StudentId("0003Y"));
        assertNotEquals(firstEditStudentDescriptor, secondEditStudentDescriptor);
    }

    @Test
    public void toString_noChangeField_allFieldsNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=" + null + ", phone=" + null + ", email=" + null + ", address=" + null + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeName_nameNotNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setName(new Name("Ben"));

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=Ben" + ", phone=" + null + ", email=" + null + ", address=" + null + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changePhone_phoneNotNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setPhone(new Phone("32447898"));

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=" + null + ", phone=32447898" + ", email=" + null + ", address=" + null + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeEmail_emailNotNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setEmail(new Email("ben@gmail.com"));

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=" + null + ", phone=" + null + ", email=ben@gmail.com" + ", address=" + null
                + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeAddress_addressNotNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setAddress(new Address("123, Jurong West Ave 6, #08-111"));

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=" + null + ", phone=" + null + ", email=" + null + ", address=123, Jurong West Ave 6, #08-111"
                + ", id=" + null + "}";

        assertEquals(expected, descriptor.toString());
    }

    @Test
    public void toString_changeId_idNotNull() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        descriptor.setId(new StudentId("0001Y"));

        String expected = EditStudentDescriptor.class.getCanonicalName()
                + "{name=" + null + ", phone=" + null + ", email=" + null + ", address=" + null + ", id=0001Y" + "}";

        assertEquals(expected, descriptor.toString());
    }
}
