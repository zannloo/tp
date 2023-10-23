package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.newcommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.newcommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Root;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.util.SampleProfBook;

public class DeleteTaskCommandTest {
    private State actualState;
    private State expectedState;
    private TaskOperation taskOperation;

    @BeforeEach
    public void setup() throws InvalidPathException {
        AbsolutePath currentPath = new AbsolutePath("~/");
        Root root1 = SampleProfBook.getRoot();
        Root root2 = SampleProfBook.getRoot();
        actualState = new StateManager(currentPath, root1, new UserPrefs());

        expectedState = new StateManager(currentPath, root2, new UserPrefs());

        // Display task
        AbsolutePath displayPath = new AbsolutePath("~/grp-002/");
        actualState.setDisplayPath(displayPath);
        actualState.showTaskList();
        expectedState.setDisplayPath(displayPath);
        expectedState.showTaskList();

        taskOperation = expectedState.taskOperation(displayPath);
    }

    @Test
    public void execute_validIndex_success() {
        Task taskToDelete = taskOperation.getTask(FIRST_INDEX.getOneBased());
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                taskToDelete.toString());

        taskOperation.deleteTask(FIRST_INDEX.getOneBased());
        expectedState.updateList();

        assertCommandSuccess(deleteCommand, actualState, expectedMessage, expectedState);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskOperation.getTaskListSize() + 1);
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, actualState,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, expectedState);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(FIRST_INDEX);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(FIRST_INDEX);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(targetIndex);
        String expected = DeleteTaskCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
