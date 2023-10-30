package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private CommandResult commonCommandResult;

    @BeforeEach
    public void setUp() {
        commonCommandResult = createCommandResult("Test", true, false);
    }

    @Test
    public void equals_sameCommandResult_returnsTrue() {
        CommandResult commandResult2 = createCommandResult("Test", true, false);

        assertTrue(commonCommandResult.equals(commandResult2));
    }

    @Test
    public void equals_differentCommandResult_returnsFalse() {
        CommandResult commandResult2 = createCommandResult("Different", true, false);

        assertFalse(commonCommandResult.equals(commandResult2));
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
        String expectedOutput = "CommandResult{feedbackToUser='Test', showHelp=true, exit=false}";

        assertEquals(expectedOutput, commonCommandResult.toString());
    }

    private CommandResult createCommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        return new CommandResult(feedbackToUser, showHelp, exit);
    }
}
}
