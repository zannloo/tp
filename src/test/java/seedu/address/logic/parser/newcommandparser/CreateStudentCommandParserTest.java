package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.newcommands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_RELATIVE_PATH;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_ID_STUDENT;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_PHONE_AMY;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CreateStudentCommand;
import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.TaskList;

public class CreateStudentCommandParserTest {
    private CreateStudentCommandParser parser = new CreateStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStu = new Student(
                new TaskList(new ArrayList<>()), 
                new Name(VALID_NAME_AMY), 
                new Email(VALID_EMAIL_AMY), 
                new Phone(VALID_PHONE_AMY), 
                new Address(VALID_ADDRESS_AMY), 
                new StudentId(VALID_ID_STUDENT));
        
        assertParseSuccess(parser, 
                VALID_STUDENT_DIR_PREAMBLE + NAME_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY, 
                new CreateStudentCommand(VALID_STUDENT_RELATIVE_PATH, expectedStu));
    }
}
