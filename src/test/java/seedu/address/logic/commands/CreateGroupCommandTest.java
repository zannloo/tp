package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CreateGroupCommand.MESSAGE_DUPLICATE_GROUP;
import static seedu.address.logic.commands.CreateGroupCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.TaskList;

public class CreateGroupCommandTest {

    @Test
    public void constructor_nullRelativePathAndGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateGroupCommand(null, null));
    }

    @Test
    public void execute_createGroup_success() throws CommandException, InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        AbsolutePath currPath = new AbsolutePath("~/");
        State state = new StateManager(currPath, root, new UserPrefs());

        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(target, group);
        CommandResult successCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, group));

        assertEquals(successCommandResult, createGroupCommand.execute(state));
    }

    @Test
    public void execute_duplicateGroup_throwCommandException() throws InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/");
        AbsolutePath target = new AbsolutePath("~/grp-001");
        State state = new StateManager(currPath, root, new UserPrefs());
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(target, group);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_GROUP, () -> createGroupCommand.execute(state));
    }

    @Test
    public void equals_sameInstance_success() throws InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Student> students = new HashMap<>();
        Name name = new Name("Group 1");
        GroupId id = new GroupId("grp-001");
        Group group = new Group(taskList, students, name, id);

        AbsolutePath target = new AbsolutePath("~/grp-001");
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(target, group);
        CreateGroupCommand duplicateCreateGroupCommand = new CreateGroupCommand(target, group);
        assertEquals(createGroupCommand, duplicateCreateGroupCommand);
    }
}
