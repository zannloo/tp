package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;
import static seedu.address.logic.commands.ChangeDirectoryCommand.MESSAGE_CURRENT_DIRECTORY;
import static seedu.address.logic.commands.ChangeDirectoryCommand.MESSAGE_INVALID_DEST;
import static seedu.address.logic.commands.ChangeDirectoryCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalGroups.EMPTY_GROUP;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Root;
import seedu.address.testutil.PathBuilder;

public class ChangeDirectoryCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_TWO_GROUPS), new UserPrefs());
        expectedModel = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_TWO_GROUPS), new UserPrefs());
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ChangeDirectoryCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerExecption() {
        ChangeDirectoryCommand command = new ChangeDirectoryCommand(ROOT_PATH);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_isHelp_returnsHelpMenu() {
        ChangeDirectoryCommand help = ChangeDirectoryCommand.HELP_MESSAGE;
        assertCommandSuccess(help, model, ChangeDirectoryCommand.MESSAGE_USAGE, expectedModel);
    }

    @Test
    public void execute_nonExistentPath_throwsCommandException() {
        AbsolutePath path = new PathBuilder().withGroup(EMPTY_GROUP).build();
        ChangeDirectoryCommand command = new ChangeDirectoryCommand(path);
        assertCommandFailure(command, model, String.format(MESSAGE_PATH_NOT_FOUND, path));
    }

    @Test
    public void execute_currentPath_throwsCommandException() {
        ChangeDirectoryCommand command = new ChangeDirectoryCommand(model.getCurrPath());
        assertCommandFailure(command, model, MESSAGE_CURRENT_DIRECTORY);
    }

    @Test
    public void execute_studentPath_throwsCommandException() {
        ChangeDirectoryCommand command = new ChangeDirectoryCommand(PATH_TO_ALICE);
        assertCommandFailure(command, model, MESSAGE_INVALID_DEST);
    }

    @Test
    public void execute_validExistingGroupPath_changesDirectory() {
        ChangeDirectoryCommand command = new ChangeDirectoryCommand(PATH_TO_GROUP_ONE);

        expectedModel.changeDirectory(PATH_TO_GROUP_ONE);
        String expectedString = String.format(MESSAGE_SUCCESS, PATH_TO_GROUP_ONE);

        assertCommandSuccess(command, model, expectedString, expectedModel);
    }

    @Test
    public void execute_rootPath_changesDirectory() {
        // Set current path to group one.
        model.changeDirectory(PATH_TO_GROUP_ONE);
        assert model.getCurrPath() == PATH_TO_GROUP_ONE;

        ChangeDirectoryCommand command = new ChangeDirectoryCommand(ROOT_PATH);

        expectedModel.changeDirectory(ROOT_PATH);
        String expectedString = String.format(MESSAGE_SUCCESS, ROOT_PATH);

        assertCommandSuccess(command, model, expectedString, expectedModel);
    }

    @Test
    public void equals() {
        ChangeDirectoryCommand changeDirectoryCommand1 = new ChangeDirectoryCommand(PATH_TO_GROUP_TWO);
        ChangeDirectoryCommand changeDirectoryCommand2 = new ChangeDirectoryCommand(ROOT_PATH);

        // same object -> returns true
        assertEquals(changeDirectoryCommand1, changeDirectoryCommand1);

        // same values -> returns true
        ChangeDirectoryCommand changeDirectoryCommand1Copy = new ChangeDirectoryCommand(PATH_TO_GROUP_TWO);
        assertEquals(changeDirectoryCommand1, changeDirectoryCommand1Copy);

        // different types -> returns false
        ShowChildrenListCommand showChildrenListCommand = new ShowChildrenListCommand(ROOT_PATH);
        assertNotEquals(showChildrenListCommand, changeDirectoryCommand1);

        // null -> returns false
        assertNotEquals(null, changeDirectoryCommand1);

        // different values
        assertNotEquals(changeDirectoryCommand1, changeDirectoryCommand2);
    }

    @Test
    public void toStringMethod() {
        ChangeDirectoryCommand changeDirectoryCommand = new ChangeDirectoryCommand(PATH_TO_GROUP_ONE);
        String expected = ChangeDirectoryCommand.class.getCanonicalName()
                + "{Destination=" + PATH_TO_GROUP_ONE + "}";
        assertEquals(expected, changeDirectoryCommand.toString());
    }
}
