package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

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
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.TaskList;

public class MoveStudentToGroupCommandTest {
    public static final String ERROR_MESSAGE_INCORRECT_DIRECTORY = "Directory is invalid";

    @Test
    public void constructor_nullSourcePathAndDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoveStudentToGroupCommand(null, null));
    }

    @Test
    public void execute_invalidPathForSourceGroup_throwCommandException() throws InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskList(new ArrayList<>()), students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/");
        AbsolutePath sourcePath = new AbsolutePath("~/");
        AbsolutePath destPath = new AbsolutePath("~/grp-002");
        State state = new StateManager(currPath, root, new UserPrefs());
        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourcePath, destPath);

        assertThrows(CommandException.class, () -> moveStudentToGroupCommand.execute(state));
    }

    @Test
    public void equals() throws InvalidPathException {
        AbsolutePath sourcePath = new AbsolutePath("~/grp-001/stu-001");
        AbsolutePath destinationPath = new AbsolutePath("~/grp-002");
        MoveStudentToGroupCommand command1 = new MoveStudentToGroupCommand(sourcePath, destinationPath);
        MoveStudentToGroupCommand command2 = new MoveStudentToGroupCommand(sourcePath, destinationPath);
        assertEquals(command1, command2);
    }

    // @Test
    // public void testOutputString() throws InvalidPathException {
    //     RelativePath relativeSourcePath = new RelativePath("~/grp-001/stu-001");
    //     RelativePath relativeDestinationPath = new RelativePath("~/grp-002");
    //     MoveStudentToGroupCommand moveStudentToGroupCommand =
    //             new MoveStudentToGroupCommand(relativeSourcePath, relativeDestinationPath);
    //     String expected =
    //             "seedu.address.logic.newcommands.MoveStudentToGroupCommand{toMoveThisStudentToAnotherGroup=null}";
    //     assertEquals(expected, moveStudentToGroupCommand.toString());
    // }
}
