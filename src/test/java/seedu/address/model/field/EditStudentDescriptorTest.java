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
    public void testCopyConstructor() {
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
    public void testIsAnyFieldEdited() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();

        // No fields edited, so it should return false
        assertFalse(descriptor.isAnyFieldEdited());

        descriptor.setName(new Name("Ben"));
        assertTrue(descriptor.isAnyFieldEdited());

        // Reset the name to null
        descriptor.setName(null);
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void testSetNameAndGetName() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Name name = new Name("Ben");

        assertFalse(descriptor.getName().isPresent());

        // Set the name
        descriptor.setName(name);
        assertTrue(descriptor.getName().isPresent());
        assertEquals(name, descriptor.getName().get());
    }

    @Test
    public void testSetPhoneAndGetPhone() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Phone phone = new Phone("656321");

        assertFalse(descriptor.getPhone().isPresent());

        // Set the phone
        descriptor.setPhone(phone);
        assertTrue(descriptor.getPhone().isPresent());
        assertEquals(phone, descriptor.getPhone().get());
    }

    @Test
    public void testSetAddressAndGetAddress() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Address address = new Address("123, Jurong West Ave 6, #08-111");

        assertFalse(descriptor.getAddress().isPresent());

        // Set the address
        descriptor.setAddress(address);
        assertTrue(descriptor.getAddress().isPresent());
        assertEquals(address, descriptor.getAddress().get());
    }

    @Test
    public void testSetEmailAndGetEmail() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        Email email = new Email("ben@gmail.com");

        assertFalse(descriptor.getEmail().isPresent());

        // Set the email
        descriptor.setEmail(email);
        assertTrue(descriptor.getEmail().isPresent());
        assertEquals(email, descriptor.getEmail().get());
    }

    @Test
    public void testSetIdAndGetId() {
        EditStudentDescriptor descriptor = new EditStudentDescriptor();
        StudentId studentId = new StudentId("0001Y");

        assertFalse(descriptor.getId().isPresent());

        // Set the id
        descriptor.setId(studentId);
        assertTrue(descriptor.getId().isPresent());
        assertEquals(studentId, descriptor.getId().get());
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

        EditStudentDescriptor firstEditStudentDescriptorCopy = new EditStudentDescriptor();
        firstEditStudentDescriptorCopy.setName(new Name("Ben"));
        firstEditStudentDescriptorCopy.setPhone(new Phone("656321"));
        firstEditStudentDescriptorCopy.setEmail(new Email("ben@gmail.com"));
        firstEditStudentDescriptorCopy.setAddress(new Address("123, Jurong West Ave 6, #08-111"));
        firstEditStudentDescriptorCopy.setId(new StudentId("0001Y"));

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
}
