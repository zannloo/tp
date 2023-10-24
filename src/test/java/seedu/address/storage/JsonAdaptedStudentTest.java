package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.StudentBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonAdaptedStudentTest {

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        Student student = new StudentBuilder().build();
        JsonAdaptedStudent jsonAdaptedStudent = new JsonAdaptedStudent(student);
        assertEquals(student, jsonAdaptedStudent.toModelType());
    }

    @Test
    public void toModelType_EmptyTask_returnsStudent() throws Exception {
        Student grp = new StudentBuilder().buildEmptyTask();
        JsonAdaptedStudent Student = new JsonAdaptedStudent(grp);
        assertEquals(grp, Student.toModelType());
    }
}
