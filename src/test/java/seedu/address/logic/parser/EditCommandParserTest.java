package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.HELP_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;

public class EditCommandParserTest {

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_help_returnsHelpMessage() {
        // help option only
        assertParseSuccess(parser, HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);

        // valid path with help option
        assertParseSuccess(parser, "grp-001" + HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);

        // invalid path with help option
        assertParseSuccess(parser, "0001Y" + HELP_OPTION, ROOT_PATH, EditCommand.HELP_MESSAGE);
    }

    @Test
    public void parse_editStudentName_success() {
        EditStudentDescriptor expectedDescriptor = new EditStudentDescriptor();
        expectedDescriptor.setName(BENSON.getName());
        assertParseSuccess(parser,
                PATH_TO_ALICE + " " + OPTION_NAME.getLongName() + " " + BENSON.getName().fullName,
                CommandTestUtil.getValidRootAbsolutePath(),
                new EditCommand(PATH_TO_ALICE, expectedDescriptor));
    }

    @Test
    public void parse_editAllStudentFields_success() {
        EditStudentDescriptor expectedEditStudentDescriptor = new EditStudentDescriptor();
        expectedEditStudentDescriptor.setName(new Name("marypotter"));
        expectedEditStudentDescriptor.setId(new StudentId("0999A"));
        expectedEditStudentDescriptor.setEmail(new Email("marypotter@gmail.com"));
        expectedEditStudentDescriptor.setPhone(new Phone("22338787"));
        expectedEditStudentDescriptor.setAddress(new Address("Singapore"));

        assertParseSuccess(parser,
                PATH_TO_ALICE + " "
                        + OPTION_NAME.getLongName() + " marypotter "
                        + OPTION_ID.getLongName() + " 0999A "
                        + OPTION_EMAIL.getLongName() + " marypotter@gmail.com "
                        + OPTION_PHONE.getLongName() + " 22338787 "
                        + OPTION_ADDRESS.getLongName() + " Singapore ",
                CommandTestUtil.getValidRootAbsolutePath(),
                new EditCommand(PATH_TO_ALICE, expectedEditStudentDescriptor));
    }

    @Test
    public void parse_editGroupName_success() {
        EditGroupDescriptor expectedDescriptor = new EditGroupDescriptor();
        expectedDescriptor.setName(new Name("Group Three"));
        assertParseSuccess(parser,
                PATH_TO_GROUP_ONE + " " + OPTION_NAME.getLongName() + " " + "Group Three",
                CommandTestUtil.getValidRootAbsolutePath(),
                new EditCommand(PATH_TO_GROUP_ONE, expectedDescriptor));
    }

    @Test
    public void parse_editAllGroupFields_success() {
        EditGroupDescriptor expectedEditGroupDescriptor = new EditGroupDescriptor();
        expectedEditGroupDescriptor.setName(new Name("Group Nineteen"));
        expectedEditGroupDescriptor.setId(new GroupId("grp-019"));

        assertParseSuccess(parser,
                PATH_TO_GROUP_ONE + " "
                        + OPTION_NAME.getLongName() + " Group Nineteen "
                        + OPTION_ID.getLongName() + " grp-019 ",
                CommandTestUtil.getValidRootAbsolutePath(),
                new EditCommand(PATH_TO_GROUP_ONE, expectedEditGroupDescriptor));
    }

    @Test
    public void parse_invalidRelativePathFormat_throwsParseException() {
        String invalidRelativePath = "~";
        assertParseFailure(parser, invalidRelativePath + NAME_DESC_AMY, ROOT_PATH,
                EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR);
    }
}
