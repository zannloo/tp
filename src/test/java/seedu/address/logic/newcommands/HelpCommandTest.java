package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.statemanager.State;
import seedu.address.model.taskmanager.TaskList;

public class HelpCommandTest {
    @Test
    public void execute_validCommand_success() throws CommandException, InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(taskList, children);
        AbsolutePath currPath = new AbsolutePath("~/");
        State state = new State(currPath, root, new UserPrefs());

        HelpCommand helpCommand = new HelpCommand();
        CommandResult commandResult = helpCommand.execute(state);

        CommandResult expexctedCommandResult =
                new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false);
        assertEquals(commandResult, expexctedCommandResult);
    }
}
