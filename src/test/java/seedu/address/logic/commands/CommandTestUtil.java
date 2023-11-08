package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_DATETIME_STR = "2023-09-22 11:30";
    public static final String VALID_TASK_DESC = "Assignment 1: Software Engineer Project";
    public static final String VALID_ID_STUDENT = "0011Y";
    public static final String VALID_ID_GROUP = "grp-123";
    public static final String VALID_CATEGORY_STUDENT = " --all allStu";
    public static final String VALID_CATEGORY_GROUP = " -all allGrp";

    public static final String VALID_ROOT_DIR_PREAMBLE = "~/";
    public static final String VALID_GROUP_DIR_PREAMBLE = "/grp-123";
    public static final String VALID_STUDENT_DIR_PREAMBLE = "/grp-123/0011Y";
    public static final String NAME_DESC_AMY = " " + OPTION_NAME.getLongName() + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + OPTION_NAME.getLongName() + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + OPTION_PHONE.getLongName() + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + OPTION_PHONE.getLongName() + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + OPTION_EMAIL.getLongName() + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + OPTION_EMAIL.getLongName() + " " + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + OPTION_ADDRESS.getLongName() + " " + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + OPTION_ADDRESS.getLongName() + " " + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + OPTION_TAG.getLongName() + " " + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + OPTION_TAG.getLongName() + " " + VALID_TAG_HUSBAND;
    public static final String DATETIME_DESC = " " + OPTION_DATETIME.getLongName() + " " + VALID_DATETIME_STR;
    public static final String ID_DESC_STUDENT = " " + OPTION_ID.getLongName() + " " + VALID_ID_STUDENT;
    public static final String ID_DESC_GROUP = " " + OPTION_ID.getLongName() + " " + VALID_ID_GROUP;
    public static final String TASK_DESC_DESC = " " + OPTION_DESC.getLongName() + " " + VALID_TASK_DESC;
    public static final String HELP_OPTION = " " + OPTION_HELP.getLongName() + " ";

    public static final String EMPTY_PREAMBLE = " ";

    public static final String INVALID_NAME_DESC = " "
            + OPTION_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + OPTION_PHONE.getLongName()
            + " " + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + OPTION_EMAIL.getLongName()
            + " " + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " "
            + OPTION_ADDRESS + " "; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + OPTION_TAG.getLongName()
            + " " + "hubby*"; // '*' not allowed in tags

    private static final RelativePath validRootRelativePath;
    private static final RelativePath validGroupRelativePath;
    private static final RelativePath validStudentRelativePath;
    private static final AbsolutePath validRootAbsolutePath;
    private static final AbsolutePath validGroupAbsolutePath;
    private static final AbsolutePath validStudentAbsolutePath;
    private static final LocalDateTime validDateTime;

    static {
        try {
            validRootRelativePath = ParserUtil.parseRelativePath(VALID_ROOT_DIR_PREAMBLE);
            validGroupRelativePath = ParserUtil.parseRelativePath(VALID_GROUP_DIR_PREAMBLE);
            validStudentRelativePath = ParserUtil.parseRelativePath(VALID_STUDENT_DIR_PREAMBLE);
            validRootAbsolutePath = new AbsolutePath(VALID_ROOT_DIR_PREAMBLE);
            validGroupAbsolutePath = validRootAbsolutePath.resolve(validGroupRelativePath);
            validStudentAbsolutePath = validRootAbsolutePath.resolve(validStudentRelativePath);
            validDateTime = ParserUtil.parseDateTime(VALID_DATETIME_STR);
        } catch (ParseException | InvalidPathException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static RelativePath getValidRootRelativePath() {
        return validRootRelativePath;
    }

    public static RelativePath getValidGroupRelativePath() {
        return validGroupRelativePath;
    }

    public static RelativePath getValidStudentRelativePath() {
        return validStudentRelativePath;
    }

    public static AbsolutePath getValidRootAbsolutePath() {
        return validRootAbsolutePath;
    }

    public static AbsolutePath getValidGroupAbsolutePath() {
        return validGroupAbsolutePath;
    }

    public static AbsolutePath getValidStudentAbsolutePath() {
        return validStudentAbsolutePath;
    }

    public static LocalDateTime getValidDateTime() {
        return validDateTime;
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        Model unchangedModel = new ModelManager(actualModel.getCurrPath(), actualModel.getRoot(),
                actualModel.getUserPrefs(), actualModel.getDisplayPath(), actualModel.isShowTaskList());
        assertThrows(CommandException.class, () -> command.execute(actualModel), expectedMessage);
        assertEquals(unchangedModel, actualModel);
    }
}
