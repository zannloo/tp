package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.UnmarkCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.model.path.AbsolutePath;

/**
 * The UnmarkCommandParserTest class is responsible for testing the parsing of user input and the creation of
 * UnmarkCommand objects by the UnmarkCommandParser class. It contains test cases to ensure that valid and invalid
 * input strings are handled correctly.
 */
public class UnmarkCommandParserTest {

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    private AbsolutePath absolutePath = CommandTestUtil.getValidGroupAbsolutePath();

    /**
     * Test the parsing of valid input arguments and ensure that it returns an UnmarkCommand object.
     */
    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, "1", absolutePath, new UnmarkCommand(INDEX_FIRST_PERSON));
    }

    /**
     * Test the parsing of invalid input arguments and ensure that it throws a ParseException.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", absolutePath, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, UnmarkCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, UnmarkCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, UnmarkCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_missingArgument_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }
}
