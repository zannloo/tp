package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PATH_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand;

class DeleteForStudentsAndGroupsCommandParserTest {

    private DeleteForStudentsAndGroupsCommandParser parser = new DeleteForStudentsAndGroupsCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteForStudentsAndGroupsCommandParser parser = new DeleteForStudentsAndGroupsCommandParser();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.getValidStudentRelativePath().toString(),
                CommandTestUtil.getValidRootAbsolutePath(),
                new DeleteForStudentsAndGroupsCommand(CommandTestUtil.getValidStudentAbsolutePath()));
    }

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser,
                HELP_OPTION,
                ROOT_PATH,
                DeleteForStudentsAndGroupsCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser,
                "grp-001" + HELP_OPTION,
                ROOT_PATH,
                DeleteForStudentsAndGroupsCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser,
                "0001Y" + HELP_OPTION,
                ROOT_PATH,
                DeleteForStudentsAndGroupsCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_missingArgument_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_invalidRelativePathFormat_throwsParseException() {
        String invalidRelativePath = "abcde";
        assertParseFailure(parser, invalidRelativePath, ROOT_PATH,
                String.format(MESSAGE_INVALID_PATH_FORMAT, invalidRelativePath));
    }
}
