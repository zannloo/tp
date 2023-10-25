package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DIR_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.id.GroupId;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;

public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group group = new Group(new Name(VALID_NAME_AMY), new GroupId(VALID_ID_GROUP));

        assertParseSuccess(parser,
                VALID_GROUP_DIR_PREAMBLE + NAME_DESC_AMY,
                CommandTestUtil.getValidRootAbsolutePath(),
                new CreateGroupCommand(CommandTestUtil.getValidGroupAbsolutePath(), group));
    }
}
