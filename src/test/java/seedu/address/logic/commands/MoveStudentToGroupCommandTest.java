package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
import static seedu.address.testutil.TypicalStudents.KAREN;
import static seedu.address.testutil.TypicalStudents.LEO;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.TaskListManager;

public class MoveStudentToGroupCommandTest {

    private Model model;

    private Model expectedModel;

    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    @BeforeEach
    public void setup() {
        Root root = PROFBOOK_WITH_TWO_GROUPS;
        model = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());
    }

    @Test
    public void constructor_nullSourcePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MoveStudentToGroupCommand(null, new AbsolutePath("~/grp-002")));
    }

    @Test
    public void constructor_nullDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MoveStudentToGroupCommand(new AbsolutePath("~/grp-001"), null));
    }

    @Test
    public void constructor_nullSourcePathAndDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoveStudentToGroupCommand(null, null));
    }

    @Test
    public void execute_nullModel_throwCommandException() throws InvalidPathException {
        Group sourceGroup = new Group(model.getGroupWithId(new GroupId("grp-001")));
        Group destinationGroup = new Group(model.getGroupWithId(new GroupId("grp-002")));

        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand command =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidPathForSourceGroup_throwCommandException() throws InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        Map<Id, Student> students = new HashMap<>();
        Group group = new Group(new TaskListManager(),
                students, new Name("Group1"), new GroupId("grp-001"));
        root.addChild(group.getId(), group);
        AbsolutePath currPath = new AbsolutePath("~/");
        AbsolutePath sourcePath = new AbsolutePath("~/");
        AbsolutePath destPath = new AbsolutePath("~/grp-002");
        Model model = new ModelManager(currPath, root, new UserPrefs());
        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourcePath, destPath);

        assertThrows(CommandException.class, () -> moveStudentToGroupCommand.execute(model));
    }

    @Test
    public void equals() throws InvalidPathException {
        Group sourceGroup = new Group(model.getGroupWithId(new GroupId("grp-001")));
        Group destinationGroup = new Group(model.getGroupWithId(new GroupId("grp-002")));

        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand1 =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        // same object -> returns true
        assertEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand1);

        // same values -> returns true
        MoveStudentToGroupCommand moveStudentToGroupCommand1Copy =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        assertEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, moveStudentToGroupCommand1);

        // null -> returns false
        assertNotEquals(null, moveStudentToGroupCommand1);

        MoveStudentToGroupCommand moveStudentToGroupCommand2 =
                new MoveStudentToGroupCommand(destinationGroupAbsolutePath, sourceGroupAbsolutePath);

        // different values
        assertNotEquals(moveStudentToGroupCommand1, moveStudentToGroupCommand2);
    }

    @Test
    public void toStringMethod() throws InvalidPathException {
        Group sourceGroup = new Group(model.getGroupWithId(new GroupId("grp-001")));
        Group destinationGroup = new Group(model.getGroupWithId(new GroupId("grp-002")));

        RelativePath sourceGroupPath = new RelativePath(sourceGroup.getId().toString());
        AbsolutePath sourceGroupAbsolutePath = rootPath.resolve(sourceGroupPath);

        RelativePath destinationGroupPath = new RelativePath(destinationGroup.getId().toString());
        AbsolutePath destinationGroupAbsolutePath = rootPath.resolve(destinationGroupPath);

        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(sourceGroupAbsolutePath, destinationGroupAbsolutePath);

        String expected = MoveStudentToGroupCommand.class.getCanonicalName()
                + "{Source Path=" + sourceGroupAbsolutePath
                + ", Destination Path=" + destinationGroupAbsolutePath + "}";

        assertEquals(expected, moveStudentToGroupCommand.toString());
    }
}
