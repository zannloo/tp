package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.parser.newcommandparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.newcommands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_GROUP_RELATIVE_PATH;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_ID_GROUP;
import static seedu.address.logic.newcommands.CommandTestUtil.VALID_NAME_AMY;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CreateGroupCommand;
import seedu.address.model.id.GroupId;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.taskmanager.TaskList;

public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group group = new Group(
                new TaskList(new ArrayList<>()), new HashMap<>(), new Name(VALID_NAME_AMY), new GroupId(VALID_ID_GROUP));
        
        assertParseSuccess(parser, 
                VALID_GROUP_DIR_PREAMBLE + NAME_DESC_AMY, 
                new CreateGroupCommand(VALID_GROUP_RELATIVE_PATH, group));
    }
}
