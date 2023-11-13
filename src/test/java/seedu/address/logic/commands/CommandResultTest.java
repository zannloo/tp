package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private static final String FEEDBACK = "Test";

    private static final Boolean SHOW_HELP = true;
    private static final Boolean EXIT = false;
    private CommandResult commonCommandResult;

    @BeforeEach
    public void setUp() {
        commonCommandResult = createCommandResult(FEEDBACK, SHOW_HELP, EXIT);
    }

    @Test
    public void equals_sameCommandResult_returnsTrue() {
        assertTrue(commonCommandResult.equals(commonCommandResult));
    }

    @Test
    public void equals_differentCommandResult_returnsFalse() {
        CommandResult commandResult2 = createCommandResult("Different", true, false);

        assertFalse(commonCommandResult.equals(commandResult2));
    }

    @Test
    public void equals_nullCommandResult_returnsFalse() {
        assertFalse(commonCommandResult.equals(null));
    }

    @Test
    public void hashCode_equalCommandResults_returnsSameHashCode() {
        CommandResult commandResult2 = createCommandResult("Test", true, false);

        assertEquals(commonCommandResult.hashCode(), commandResult2.hashCode());
    }

    @Test
    public void hashCode_differentCommandResults_returnsDifferentHashCodes() {
        CommandResult commandResult2 = createCommandResult("Different", true, false);

        assertFalse(commonCommandResult.hashCode() == commandResult2.hashCode());
    }

    @Test
    public void toString_correctOutput() {
        String expectedOutput = CommandResult.class.getCanonicalName()
                + "{feedbackToUser=Test, showHelp=true, exit=false}";
        assertEquals(expectedOutput, commonCommandResult.toString());
    }

    private CommandResult createCommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        return new CommandResult(feedbackToUser, showHelp, exit);
    }

    @Test
    public void getFeedback_correctOutput() {
        assertEquals(commonCommandResult.getFeedbackToUser(), FEEDBACK);
    }

    @Test
    public void showHelp_correctOutput() {
        assertTrue(commonCommandResult.isShowHelp());
    }

    @Test
    public void isExit_correctOutput() {
        assertFalse(commonCommandResult.isExit());
    }
}
