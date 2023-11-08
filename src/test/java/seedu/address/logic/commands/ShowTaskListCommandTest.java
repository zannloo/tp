package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ShowTaskListCommand.MESSAGE_NOT_TASK_MANAGER;
import static seedu.address.logic.commands.ShowTaskListCommand.MESSAGE_SUCCESS;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_FIONA;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_GROUP_ONE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;

public class ShowTaskListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_GROUP_ONE), new UserPrefs());
        expectedModel = new ModelManager(ROOT_PATH, new Root(PROFBOOK_WITH_GROUP_ONE), new UserPrefs());
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowTaskListCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerExecption() {
        ShowTaskListCommand command = new ShowTaskListCommand();
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_isHelp_returnsHelpMenu() {
        ShowTaskListCommand help = ShowTaskListCommand.HELP_MESSAGE;
        assertCommandSuccess(help, model, ShowTaskListCommand.MESSAGE_USAGE, expectedModel);
    }

    @Test
    public void execute_currentPathRootPath_throwsCommandException() {
        ShowTaskListCommand command = new ShowTaskListCommand();

        assertCommandFailure(command, model, String.format(MESSAGE_NOT_TASK_MANAGER, model.getCurrPath()));;
    }

    @Test
    public void execute_currentPathGroupPath_showsTaskList() {
        // cd to group path
        model.changeDirectory(PATH_TO_GROUP_ONE);
        expectedModel.changeDirectory(PATH_TO_GROUP_ONE);

        ShowTaskListCommand command = new ShowTaskListCommand();

        expectedModel.setDisplayPath(expectedModel.getCurrPath());
        expectedModel.showTaskList();
        String expectedMsg = String.format(MESSAGE_SUCCESS, expectedModel.getCurrPath());

        assertCommandSuccess(command, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_validNonExistentGroupPath_throwsCommandException() {
        ShowTaskListCommand command = new ShowTaskListCommand(PATH_TO_GROUP_TWO);
        assertCommandFailure(command, model, String.format(MESSAGE_PATH_NOT_FOUND, PATH_TO_GROUP_TWO));
    }

    @Test
    public void execute_validNonExistentStudentPath_throwsCommandException() {
        ShowTaskListCommand command = new ShowTaskListCommand(PATH_TO_FIONA);
        assertCommandFailure(command, model, String.format(MESSAGE_PATH_NOT_FOUND, PATH_TO_FIONA));
    }

    @Test
    public void execute_rootPath_throwsCommandException() {
        ShowTaskListCommand command = new ShowTaskListCommand(ROOT_PATH);
        assertCommandFailure(command, model, String.format(MESSAGE_NOT_TASK_MANAGER, ROOT_PATH));
    }

    @Test
    public void execute_validExistingGroupPath_showsTaskList() {
        ShowTaskListCommand command = new ShowTaskListCommand(PATH_TO_GROUP_ONE);

        expectedModel.setDisplayPath(PATH_TO_GROUP_ONE);
        expectedModel.showTaskList();

        assertCommandSuccess(command, model, String.format(MESSAGE_SUCCESS, PATH_TO_GROUP_ONE), expectedModel);
    }

    @Test
    public void execute_validExistingStudentPath_showsTaskList() {
        ShowTaskListCommand command = new ShowTaskListCommand(PATH_TO_ALICE);

        expectedModel.setDisplayPath(PATH_TO_ALICE);
        expectedModel.showTaskList();

        assertCommandSuccess(command, model, String.format(MESSAGE_SUCCESS, PATH_TO_ALICE), expectedModel);
    }

    @Test
    public void equals() {
        ShowTaskListCommand showTaskListCommand1 = new ShowTaskListCommand(PATH_TO_GROUP_TWO);
        ShowTaskListCommand showTaskListCommand2 = new ShowTaskListCommand(ROOT_PATH);

        // same object -> returns true
        assertEquals(showTaskListCommand1, showTaskListCommand1);

        // same values -> returns true
        ShowTaskListCommand showTaskListCommand1Copy = new ShowTaskListCommand(PATH_TO_GROUP_TWO);
        assertEquals(showTaskListCommand1, showTaskListCommand1Copy);

        // different types -> returns false
        ShowChildrenListCommand showChildrenListCommand = new ShowChildrenListCommand(ROOT_PATH);
        assertNotEquals(showChildrenListCommand, showTaskListCommand1);

        // null -> returns false
        assertNotEquals(null, showTaskListCommand1);

        // different values
        assertNotEquals(showTaskListCommand1, showTaskListCommand2);
    }

    @Test
    public void toStringMethod() {
        ShowTaskListCommand showTaskListCommand = new ShowTaskListCommand(PATH_TO_GROUP_ONE);
        String expected = ShowTaskListCommand.class.getCanonicalName()
                + "{targetPath=" + PATH_TO_GROUP_ONE + "}";
        assertEquals(expected, showTaskListCommand.toString());
    }
}
