package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_GROUP_RELATIVE_PATH;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_RELATIVE_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.MoveStudentToGroupCommand;

public class MoveStudentToGroupCommandParserTest {
    private MoveStudentToGroupCommandParser parser = new MoveStudentToGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, 
                VALID_STUDENT_DIR_PREAMBLE + " " + VALID_GROUP_DIR_PREAMBLE, 
                new MoveStudentToGroupCommand(VALID_STUDENT_RELATIVE_PATH, VALID_GROUP_RELATIVE_PATH));
    }
}
