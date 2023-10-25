package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand;

class DeleteForStudentsAndGroupsCommandParserTest {
    @Test
    public void parse_allFieldsPresent_success() {
        DeleteForStudentsAndGroupsCommandParser parser = new DeleteForStudentsAndGroupsCommandParser();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.getValidStudentRelativePath().toString(),
                CommandTestUtil.getValidRootAbsolutePath(),
                new DeleteForStudentsAndGroupsCommand(CommandTestUtil.getValidStudentAbsolutePath()));
    }
}
