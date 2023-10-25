package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.model.taskmanager.Deadline;

public class CreateDeadlineCommandParserTest {
    private CreateDeadlineCommandParser parser = new CreateDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateDeadlineCommand(
                        CommandTestUtil.getValidGroupAbsolutePath(),
                        new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime())));
    }

    @Test
    public void parse_allFieldsPresentWithCategory_success() {
        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC + VALID_CATEGORY_STUDENT,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateDeadlineCommand(
                        CommandTestUtil.getValidGroupAbsolutePath(),
                        new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime()), "allStu"));
    }
}
