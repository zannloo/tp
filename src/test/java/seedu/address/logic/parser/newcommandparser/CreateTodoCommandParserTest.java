package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.newcommands.CommandTestUtil.TASK_DESC_DESC;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_STUDENT_RELATIVE_PATH;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_TASK_DESC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CreateTodoCommand;
import seedu.address.model.taskmanager.ToDo;

public class CreateTodoCommandParserTest {
    private CreateTodoCommandParser parser = new CreateTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, 
                VALID_STUDENT_DIR_PREAMBLE + TASK_DESC_DESC, 
                new CreateTodoCommand(VALID_STUDENT_RELATIVE_PATH, new ToDo(VALID_TASK_DESC)));
    }
}
