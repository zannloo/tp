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
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleProfBook;

public class UnmarkCommandTest {

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
        Task taskToUnmark = taskOperation.unmarkTask(FIRST_INDEX.getOneBased());
        UnmarkCommand unmarkCommand = new UnmarkCommand(FIRST_INDEX);

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_MARK_TASK_SUCCESS,
                taskToUnmark.toString());

        taskOperation.unmarkTask(FIRST_INDEX.getOneBased());
        expectedModel.updateList();

        assertCommandSuccess(unmarkCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskOperation.getTaskListSize() + 1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex);

        assertCommandFailure(unmarkCommand, actualModel,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, expectedModel);
    }

    @Test
    public void equals() {
        UnmarkCommand firstUnmarkCommand = new UnmarkCommand(FIRST_INDEX);
        UnmarkCommand secondUnmarkCommand = new UnmarkCommand(SECOND_INDEX);

        // same object -> returns true
        assertEquals(firstUnmarkCommand, firstUnmarkCommand);

        // same values -> returns true
        UnmarkCommand firstMarkCommandCopy = new UnmarkCommand(FIRST_INDEX);
        assertEquals(firstUnmarkCommand, firstMarkCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstUnmarkCommand);

        // null -> returns false
        assertNotEquals(null, firstUnmarkCommand);

        // different object -> returns false
        assertNotEquals(firstUnmarkCommand, secondUnmarkCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(targetIndex);
        String expected = UnmarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, unmarkCommand.toString());
    }
}
