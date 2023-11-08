package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PATH_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.commands.CreateStudentCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.TaskListManager;

public class CreateStudentCommandParserTest {
    private CreateStudentCommandParser parser = new CreateStudentCommandParser();

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, CreateStudentCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, CreateStudentCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, CreateStudentCommand.HELP_MESSAGE);

        // invalid option with help option
        assertParseSuccess(parser, ADDRESS_DESC_AMY + HELP_OPTION, ROOT_PATH, CreateStudentCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_missingOptionNameOrEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingPath_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingPhone_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingEmail_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingAddress_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStu = new Student(
                new TaskListManager(),
                new Name(VALID_NAME_AMY),
                new Email(VALID_EMAIL_AMY),
                new Phone(VALID_PHONE_AMY),
                new Address(VALID_ADDRESS_AMY),
                new StudentId(VALID_ID_STUDENT));

        assertParseSuccess(parser,
                VALID_STUDENT_DIR_PREAMBLE + NAME_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateStudentCommand(CommandTestUtil.getValidStudentAbsolutePath(), expectedStu));
    }

    @Test
    public void parse_optionalArgumentsFieldsAbsent_success() {
        Student expectedStu = new Student(
                new TaskListManager(),
                new Name(VALID_NAME_AMY),
                Email.PLACEHOLDER,
                Phone.PLACEHOLDER,
                Address.PLACEHOLDER,
                new StudentId(VALID_ID_STUDENT));

        assertParseSuccess(parser,
                VALID_STUDENT_DIR_PREAMBLE + NAME_DESC_AMY,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateStudentCommand(CommandTestUtil.getValidStudentAbsolutePath(), expectedStu));
    }

    @Test
    public void parse_invalidRelativePathFormat_throwsParseException() {
        String invalidRelativePath = "abcde";
        assertParseFailure(parser, invalidRelativePath + NAME_DESC_AMY, ROOT_PATH,
                String.format(MESSAGE_INVALID_PATH_FORMAT, invalidRelativePath));
    }

    @Test
    public void parse_pathNotToStudentDirectory_throwsParseException() throws InvalidPathException {
        String path = "~/grp-001";
        assertParseFailure(parser, path + NAME_DESC_AMY, ROOT_PATH,
                "Destination path provided is not a student directory.");
    }
}
