package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.exceptions.InvalidPathException;

public class StudentIdTest {
    @Test
    public void constructor_validStudentId_validStudentIdObject() {
        StudentId studentId = new StudentId("0001Y");
        assertEquals("0001Y", studentId.toString());
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalValueException() {
        assertThrows(IllegalArgumentException.class, () -> new StudentId("invalidStudentId"));
    }

    @Test
    public void isValidStudentId_validStudentId_true() {
        assertTrue(StudentId.isValidStudentId("0001Y"));
    }

    @Test
    public void isValidStudentId_invalidStudentId_false() {
        assertFalse(StudentId.isValidStudentId("invalidStudentId"));
    }

    @Test
    public void equalsMethod() {
        StudentId studentId1 = new StudentId("0001Y");
        StudentId studentId1Copy = new StudentId("0001Y");
        StudentId studentId2 = new StudentId("0002Y");
        GroupId groupId1 = new GroupId("grp-001");

        // same object -> returns true
        assertEquals(studentId1, studentId1);

        // same values -> returns true
        assertEquals(studentId1Copy, studentId1);

        // different types -> returns false
        assertNotEquals(groupId1, studentId1);

        // null -> returns false
        assertNotEquals(null, studentId1);

        // different values
        assertNotEquals(studentId1, studentId2);
    }

    @Test
    public void hashCodeMethod() throws InvalidPathException {
        StudentId studentId1 = new StudentId("0001Y");
        StudentId studentId1Copy = new StudentId("0001Y");
        StudentId studentId2 = new StudentId("0002Y");

        // same value -> returns true
        assertEquals(studentId1.hashCode(), studentId1Copy.hashCode());

        // different values -> returns false
        assertNotEquals(studentId1.hashCode(), studentId2.hashCode());
    }
}
