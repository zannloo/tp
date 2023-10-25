package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;

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
        AbsolutePath path1 = new AbsolutePath("~/grp-001");
        AbsolutePath path2 = new AbsolutePath("~/grp-002");
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand firstEditCommand = new EditCommand(path1, editGroupDescriptor);
        EditCommand secondEditCommand = new EditCommand(path2, editGroupDescriptor);

        assertFalse(firstEditCommand.equals(secondEditCommand));
    }

    @Test
    public void toString_validateOutputString_correctStringRepresentation() throws InvalidPathException {
        AbsolutePath path = new AbsolutePath("~/grp-001");
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        EditCommand firstEditCommand = new EditCommand(path, editGroupDescriptor);

        String expected = "seedu.address.logic.newcommands.EditCommand{toEdit=seedu.address.model"
                + ".field.EditGroupDescriptor{name=null, id=null}}";
        assertEquals(expected, firstEditCommand.toString());
    }

    @Test
    public void execute_noSuchGroup_throwCommandException() throws InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        AbsolutePath currPath = new AbsolutePath("~/");
        AbsolutePath path = new AbsolutePath("~/grp-001");
        State state = new StateManager(currPath, root, new UserPrefs());

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand editCommand = new EditCommand(path, editStudentDescriptor);

        assertThrows(CommandException.class, EditCommand.MESSAGE_NO_SUCH_PATH, () -> editCommand.execute(state));
    }

    @Test
    public void execute_invalidDirectory_throwCommandException() throws InvalidPathException {
        Map<Id, Group> children = new HashMap<>();
        Root root = new Root(children);
        AbsolutePath currPath = new AbsolutePath("~/");
        AbsolutePath path = new AbsolutePath("~/");
        State state = new StateManager(currPath, root, new UserPrefs());

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand editCommand = new EditCommand(path, editStudentDescriptor);

        assertThrows(CommandException.class,
                EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR, () -> editCommand.execute(state));
    }
}
