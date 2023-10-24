package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CommandTestUtil;
import seedu.address.logic.newcommands.MarkCommand;
import seedu.address.model.path.AbsolutePath;

/**
 * The MarkCommandParserTest class is responsible for testing the parsing of user input and the creation of MarkCommand
 * objects by the MarkCommandParser class. It contains test cases to ensure that valid and invalid input strings are
 * handled correctly.
 */
public class MarkCommandParserTest {

    private MarkCommandParser parser = new MarkCommandParser();

    private AbsolutePath absolutePath = CommandTestUtil.getValidGroupAbsolutePath();

    /**
     * Test the parsing of valid input arguments and ensure that it returns a MarkCommand object.
     */
    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, "1", absolutePath, new MarkCommand(INDEX_FIRST_PERSON));
    }

    /**
     * Test the parsing of invalid input arguments and ensure that it throws a ParseException.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", absolutePath,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
    }
}
