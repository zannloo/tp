package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESC;
import static seedu.address.logic.commands.CreateDeadlineCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.model.task.Deadline;

public class CreateDeadlineCommandParserTest {
    private CreateDeadlineCommandParser parser = new CreateDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateDeadlineCommand(
                        CommandTestUtil.getValidGroupAbsolutePath(),
                        new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime()),
                        Category.NONE));
    }

    @Test
    public void parse_allFieldsPresentWithCategory_success() {
        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC + VALID_CATEGORY_STUDENT,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateDeadlineCommand(
                        CommandTestUtil.getValidGroupAbsolutePath(),
                        new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime()),
                        Category.ALLSTU));
    }

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, CreateDeadlineCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, CreateDeadlineCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, CreateDeadlineCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_missingDesc_throwsParseException() {
        assertParseFailure(parser, EMPTY_PREAMBLE, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));

        assertParseFailure(parser, TASK_DESC_DESC, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));

        assertParseFailure(parser, DATETIME_DESC, ROOT_PATH, MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
    }
}
