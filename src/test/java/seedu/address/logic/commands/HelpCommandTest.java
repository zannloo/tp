package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;

public class HelpCommandTest {
    @Test
    public void execute_validCommand_success() throws CommandException, InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        AbsolutePath currPath = new AbsolutePath("~/");
        State state = new StateManager(currPath, root, new UserPrefs());

        HelpCommand helpCommand = new HelpCommand();
        CommandResult commandResult = helpCommand.execute(state);

        CommandResult expexctedCommandResult =
                new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false);
        assertEquals(commandResult, expexctedCommandResult);
    }
}
