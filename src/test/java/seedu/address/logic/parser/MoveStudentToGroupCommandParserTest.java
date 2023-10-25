package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.MoveStudentToGroupCommand;

public class MoveStudentToGroupCommandParserTest {
    private MoveStudentToGroupCommandParser parser = new MoveStudentToGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_STUDENT_DIR_PREAMBLE + " " + VALID_GROUP_DIR_PREAMBLE,
                CommandTestUtil.getValidRootAbsolutePath(),
                new MoveStudentToGroupCommand(
                        CommandTestUtil.getValidStudentAbsolutePath(),
                        CommandTestUtil.getValidGroupAbsolutePath()));
    }
}
