package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.profbook.Student;
import seedu.address.testutil.StudentBuilder;


public class JsonAdaptedStudentTest {

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        Student student = new StudentBuilder().build();
        JsonAdaptedStudent jsonAdaptedStudent = new JsonAdaptedStudent(student);
        assertEquals(student, jsonAdaptedStudent.toModelType());
    }

    @Test
    public void toModelType_emptyTask_returnsStudent() throws Exception {
        Student grp = new StudentBuilder().buildEmptyTask();
        JsonAdaptedStudent student = new JsonAdaptedStudent(grp);
        assertEquals(grp, student.toModelType());
    }
}
