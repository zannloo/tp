package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CreateGroupCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.id.GroupId;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;

public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, CreateGroupCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, CreateGroupCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, CreateGroupCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_optionNameAbsent_throwsParseException() {
        String path = "~/grp-001";
        assertParseFailure(parser, path, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_missingAllArgument_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Group group = new Group(new Name(VALID_NAME_AMY), new GroupId(VALID_ID_GROUP));

        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + NAME_DESC_AMY,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateGroupCommand(CommandTestUtil.getValidGroupAbsolutePath(), group));
    }

    @Test
    public void parse_invalidPath_throwsParseException() {
        String invalidPath = "../..";
        assertParseFailure(parser, invalidPath, ROOT_PATH,
                String.format(MESSAGE_MISSING_ARGUMENT.apply("mkdir")));
    }

    @Test
    public void parse_pathNotToGroupDirectory_throwsParseException() throws InvalidPathException {
        String path = "~/grp-001/0001Y";
        assertParseFailure(parser, path + NAME_DESC_AMY, ROOT_PATH,
                "Destination path provided is not a group directory.");
    }
}
