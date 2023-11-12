package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_ADDRESS;
import static seedu.address.testutil.StudentBuilder.DEFAULT_EMAIL;
import static seedu.address.testutil.StudentBuilder.DEFAULT_ID;
import static seedu.address.testutil.StudentBuilder.DEFAULT_NAME;
import static seedu.address.testutil.StudentBuilder.DEFAULT_PHONE;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profbook.Student;
import seedu.address.testutil.TypicalStudents;

public class JsonAdaptedStudentTest {

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedStudentTest.class);
    private static final Student student = TypicalStudents.ALICE;
    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        logger.info("Testing toModelType for valid student");
        JsonAdaptedStudent jsonAdaptedStudent = new JsonAdaptedStudent(student);
        assertEquals(student, jsonAdaptedStudent.toModelType());
    }

    @Test
    public void toModelType_emptyTask_returnsStudent() throws Exception {
        JsonAdaptedStudent jsonAdaptedStudent = new JsonAdaptedStudent(student);
        assertEquals(student, jsonAdaptedStudent.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent("!@#$%^&*",
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                "!@#$%^&*",
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                null,
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                null,
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                "!@#$%^&*",
                DEFAULT_ADDRESS,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                " fail",
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                null,
                DEFAULT_ID,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                "!@#$%^&*",
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() throws Exception {
        logger.info("Testing toModelType with null ID should throw excepetion");
        JsonAdaptedStudent student = new JsonAdaptedStudent(DEFAULT_NAME,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                DEFAULT_ADDRESS,
                null,
                new ArrayList<JsonAdaptedTasks>());
        assertThrows(IllegalValueException.class, () -> student.toModelType());
    }
}
