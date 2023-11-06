package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.EditCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;

public class EditCommandParserTest {

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);
    }
}
