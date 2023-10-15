package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.newcommands.CommandTestUtil.DATETIME_DESC;
import static seedu.address.logic.newcommands.CommandTestUtil.TASK_DESC_DESC;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_CATEGORY_STUDENT;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_TASK_DESC;
import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CommandTestUtil;
import seedu.address.logic.newcommands.CreateDeadlineCommand;
import seedu.address.model.taskmanager.Deadline;

public class CreateDeadlineCommandParserTest {
    private CreateDeadlineCommandParser parser = new CreateDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC,
                new CreateDeadlineCommand(
                        CommandTestUtil.getValidGroupRelativePath(),
                        new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime())));
    }

    //There are errors
        @Test
        public void parse_allFieldsPresentWithCategory_success() {
            assertParseSuccess(parser,
                    VALID_GROUP_DIR_PREAMBLE + TASK_DESC_DESC + DATETIME_DESC + VALID_CATEGORY_STUDENT,
                    new CreateDeadlineCommand(
                            CommandTestUtil.getValidGroupRelativePath(),
                            new Deadline(VALID_TASK_DESC, CommandTestUtil.getValidDateTime()),
                            "allStu"));
        }
}
