package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.*;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.ToDo;


public class MarkCommandTest {

    @Test
    public void execute_markTask_success() throws CommandException, InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        TaskListManager taskListManager = new TaskListManager();
        taskListManager.addTask(new ToDo("task1")); // change to tasklist
        Group group = new Group(taskListManager, students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/grp-001");
        State state = new StateManager(currPath, root, new UserPrefs()); // currPath must be display path
        MarkCommand markCommand = new MarkCommand(1);
        markCommand.execute(state);
    }

    @Test
    public void equals_sameInstance_success() {
        MarkCommand markCommand1 = new MarkCommand(1);
        MarkCommand markCommand2 = new MarkCommand(1);
        assertEquals(markCommand1, markCommand2);
    }
}
