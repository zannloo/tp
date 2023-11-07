package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

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

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser,
                HELP_OPTION,
                ROOT_PATH,
                MoveStudentToGroupCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser,
                "grp-001" + HELP_OPTION,
                ROOT_PATH,
                MoveStudentToGroupCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser,
                "0001Y" + HELP_OPTION,
                ROOT_PATH,
                MoveStudentToGroupCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_emptyPreamble_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingArgument_throwsParseException() {
        assertParseFailure(parser, VALID_GROUP_DIR_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }
}
