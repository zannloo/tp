package seedu.address.logic.parser.newcommandparser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.CommandTestUtil;
import seedu.address.logic.newcommands.DeleteForStudentsAndGroupsCommand;

class DeleteForStudentsAndGroupsCommandParserTest {
    @Test
    public void parse_allFieldsPresent_success() {
        DeleteForStudentsAndGroupsCommandParser parser = new DeleteForStudentsAndGroupsCommandParser();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.getValidStudentRelativePath().toString(),
                new DeleteForStudentsAndGroupsCommand(CommandTestUtil.getValidStudentRelativePath()));
    }
}
