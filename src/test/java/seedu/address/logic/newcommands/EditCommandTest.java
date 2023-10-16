package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.statemanager.State;
import seedu.address.model.taskmanager.TaskList;

public class EditCommandTest {

    @Test
    public void constructor_nullRelativePathAndEditGroupDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditGroupDescriptor) null));
    }

    @Test
    public void constructor_nullRelativePathAndEditStudentDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, (EditStudentDescriptor) null));
    }

    @Test
    public void equals_differentRelativePath_fail() throws InvalidPathException {
        RelativePath relativePath1 = new RelativePath("~/grp-001");
        RelativePath relativePath2 = new RelativePath("~/grp-002");
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand firstEditCommand = new EditCommand(relativePath1, editGroupDescriptor);
        EditCommand secondEditCommand = new EditCommand(relativePath2, editGroupDescriptor);

        assertFalse(firstEditCommand.equals(secondEditCommand));
    }

    @Test
    public void toString_validateOutputString_correctStringRepresentation() throws InvalidPathException {
        RelativePath relativePath = new RelativePath("~/grp-001");
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand firstEditCommand = new EditCommand(relativePath, editGroupDescriptor);

        String expected = "seedu.address.logic.newcommands.EditCommand{toEdit=seedu.address.model"
                + ".field.EditGroupDescriptor{name=null, id=null}}";
        assertEquals(expected, firstEditCommand.toString());
    }

    @Test
    public void execute_noSuchGroup_throwCommandException() throws InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(taskList, children);
        AbsolutePath currPath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("~/grp-001");
        State state = new State(currPath, root, new UserPrefs());

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand editCommand = new EditCommand(relativePath, editStudentDescriptor);

        assertThrows(CommandException.class, EditCommand.ERROR_MESSAGE_NO_SUCH_GROUP, () -> editCommand.execute(state));
    }

    @Test
    public void execute_invalidDirectory_throwCommandException() throws InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(taskList, children);
        AbsolutePath currPath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("~/");
        State state = new State(currPath, root, new UserPrefs());

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand editCommand = new EditCommand(relativePath, editStudentDescriptor);

        assertThrows(CommandException.class,
                EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR, () -> editCommand.execute(state));
    }
}
