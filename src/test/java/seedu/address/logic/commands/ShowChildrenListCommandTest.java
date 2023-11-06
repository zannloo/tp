package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ShowChildrenListCommand.MESSAGE_NOT_CHILDREN_MANAGER;
import static seedu.address.logic.commands.ShowChildrenListCommand.MESSAGE_SUCCESS;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_GROUP_ONE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;

public class ShowChildrenListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_GROUP_ONE), new UserPrefs());
        expectedModel = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_GROUP_ONE), new UserPrefs());
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowChildrenListCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerExecption() {
        ShowChildrenListCommand command = new ShowChildrenListCommand();
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_isHelp_returnsHelpMenu() {
        ShowChildrenListCommand help = ShowChildrenListCommand.HELP_MESSAGE;
        assertCommandSuccess(help, model, ShowChildrenListCommand.MESSAGE_USAGE, expectedModel);
    }

    @Test
    public void execute_currentPath_showsChildrenList() {
        ShowChildrenListCommand command = new ShowChildrenListCommand();

        expectedModel.setDisplayPath(expectedModel.getCurrPath());
        expectedModel.showChildrenList();
        String expectedMsg = String.format(MESSAGE_SUCCESS, "current directory");

        assertCommandSuccess(command, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_validNonExistentPath_throwsCommandException() {
        ShowChildrenListCommand command = new ShowChildrenListCommand(PATH_TO_GROUP_TWO);
        assertCommandFailure(command, model, String.format(MESSAGE_PATH_NOT_FOUND, PATH_TO_GROUP_TWO));
    }

    @Test
    public void execute_studentPath_throwsCommandException() {
        ShowChildrenListCommand command = new ShowChildrenListCommand(PATH_TO_ALICE);
        assertCommandFailure(command, model, String.format(MESSAGE_NOT_CHILDREN_MANAGER, PATH_TO_ALICE));
    }

    @Test
    public void execute_validExistingPath_showsChildrenList() {
        ShowChildrenListCommand command = new ShowChildrenListCommand(PATH_TO_GROUP_ONE);

        expectedModel.setDisplayPath(PATH_TO_GROUP_ONE);
        expectedModel.showChildrenList();

        assertCommandSuccess(command, model, String.format(MESSAGE_SUCCESS, PATH_TO_GROUP_ONE), expectedModel);
    }

    @Test
    public void equals() {
        ShowChildrenListCommand showChildrenListCommand1 = new ShowChildrenListCommand(PATH_TO_GROUP_TWO);
        ShowChildrenListCommand showChildrenListCommand2 = new ShowChildrenListCommand(ROOT_PATH);

        // same object -> returns true
        assertEquals(showChildrenListCommand1, showChildrenListCommand1);

        // same values -> returns true
        ShowChildrenListCommand showChildrenListCommand1Copy = new ShowChildrenListCommand(PATH_TO_GROUP_TWO);
        assertEquals(showChildrenListCommand1, showChildrenListCommand1Copy);

        // different types -> returns false
        ShowTaskListCommand showTaskListCommand = new ShowTaskListCommand(PATH_TO_GROUP_TWO);
        assertNotEquals(showTaskListCommand, showChildrenListCommand1);

        // null -> returns false
        assertNotEquals(null, showChildrenListCommand1);

        // different values
        assertNotEquals(showChildrenListCommand1, showChildrenListCommand2);
    }

    @Test
    public void toStringMethod() {
        ShowChildrenListCommand showChildrenListCommand = new ShowChildrenListCommand(PATH_TO_GROUP_ONE);
        String expected = ShowChildrenListCommand.class.getCanonicalName()
                + "{targetPath=" + PATH_TO_GROUP_ONE + "}";
        assertEquals(expected, showChildrenListCommand.toString());
    }
}
