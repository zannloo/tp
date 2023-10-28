package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskOperation;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.util.SampleProfBook;

public class DeleteTaskCommandTest {
    private Model actualModel;
    private Model expectedModel;
    private TaskOperation taskOperation;

    @BeforeEach
    public void setup() throws InvalidPathException {
        AbsolutePath currentPath = new AbsolutePath("~/");
        Root root1 = SampleProfBook.getRoot();
        Root root2 = SampleProfBook.getRoot();
        actualModel = new ModelManager(currentPath, root1, new UserPrefs());

        expectedModel = new ModelManager(currentPath, root2, new UserPrefs());

        // Display task
        AbsolutePath displayPath = new AbsolutePath("~/grp-002/");
        actualModel.setDisplayPath(displayPath);
        actualModel.showTaskList();
        expectedModel.setDisplayPath(displayPath);
        expectedModel.showTaskList();

        taskOperation = expectedModel.taskOperation(displayPath);
    }

    @Test
    public void execute_validIndex_success() {
        Task taskToDelete = taskOperation.getTask(FIRST_INDEX.getOneBased());
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                taskToDelete.toString());

        taskOperation.deleteTask(FIRST_INDEX.getOneBased());
        expectedModel.updateList();

        assertCommandSuccess(deleteCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskOperation.getTaskListSize() + 1);
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, actualModel,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, expectedModel);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(FIRST_INDEX);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(SECOND_INDEX);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(FIRST_INDEX);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(targetIndex);
        String expected = DeleteTaskCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
