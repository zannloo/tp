package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;

import java.time.LocalDateTime;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.newcommandparser.ParserUtil;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.statemanager.State;

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
    public static final String VALID_ID_STUDENT = "stu-022";
    public static final String VALID_ID_GROUP = "grp-123";
    public static final String VALID_CATEGORY_STUDENT = " --all allStu";
    public static final String VALID_CATEGORY_GROUP = " -all allGrp";

    public static final String VALID_ROOT_DIR_PREAMBLE = "~/";
    public static final String VALID_GROUP_DIR_PREAMBLE = "/grp-123";
    public static final String VALID_STUDENT_DIR_PREAMBLE = "/grp-123/stu-022";
    public static final String NAME_DESC_AMY = " " + OPTION_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + OPTION_NAME + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + OPTION_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + OPTION_PHONE + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + OPTION_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + OPTION_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + OPTION_ADDRESS + " " + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + OPTION_ADDRESS + " " + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + OPTION_TAG + " " + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + OPTION_TAG + " " + VALID_TAG_HUSBAND;
    public static final String DATETIME_DESC = " " + OPTION_DATETIME + " " + VALID_DATETIME_STR;
    public static final String ID_DESC_STUDENT = " " + OPTION_ID + " " + VALID_ID_STUDENT;
    public static final String ID_DESC_GROUP = " " + OPTION_ID + " " + VALID_ID_GROUP;
    public static final String TASK_DESC_DESC = " " + OPTION_DESC + " " + VALID_TASK_DESC;


    public static final String INVALID_NAME_DESC = " "
            + OPTION_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + OPTION_PHONE + " " + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + OPTION_EMAIL + " " + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " "
            + OPTION_ADDRESS + " "; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + OPTION_TAG + " " + "hubby*"; // '*' not allowed in tags

    private static RelativePath validRootRelativePath;
    private static RelativePath validGroupRelativePath;
    private static RelativePath validStudentRelativePath;
    private static AbsolutePath validRootAbsolutePath;
    private static AbsolutePath validGroupAbsolutePath;
    private static AbsolutePath validStudentAbsolutePath;
    private static LocalDateTime validDateTime;

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
     * - the {@code actualState} matches {@code expectedState}
     */
    public static void assertCommandSuccess(Command command, State actualState, CommandResult expectedCommandResult,
            State expectedState) {
        try {
            CommandResult result = command.execute(actualState);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedState, actualState);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, State, CommandResult, State)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, State actualState, String expectedMessage,
            State expectedState) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualState, expectedCommandResult, expectedState);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the {@code actualState} remain unchanged
     */
    public static void assertCommandFailure(Command command, State actualState,
            String expectedMessage, State unchangedState) {
        assertThrows(CommandException.class, () -> command.execute(actualState), expectedMessage);
        assertEquals(unchangedState, actualState);
    }
}
