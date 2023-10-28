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

public class MarkCommandTest {

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
        Task taskToMark = taskOperation.markTask(FIRST_INDEX.getOneBased());
        MarkCommand markCommand = new MarkCommand(FIRST_INDEX);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS,
                taskToMark.toString());

        taskOperation.markTask(FIRST_INDEX.getOneBased());
        expectedModel.updateList();

        assertCommandSuccess(markCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskOperation.getTaskListSize() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, actualModel,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, expectedModel);
    }

    @Test
    public void equals() {
        MarkCommand firstMarkCommand = new MarkCommand(FIRST_INDEX);
        MarkCommand secondMarkCommand = new MarkCommand(SECOND_INDEX);

        // same object -> returns true
        assertEquals(firstMarkCommand, firstMarkCommand);

        // same values -> returns true
        MarkCommand firstMarkCommandCopy = new MarkCommand(FIRST_INDEX);
        assertEquals(firstMarkCommand, firstMarkCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstMarkCommand);

        // null -> returns false
        assertNotEquals(null, firstMarkCommand);

        // different object -> returns false
        assertNotEquals(firstMarkCommand, secondMarkCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(targetIndex);
        String expected = MarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, markCommand.toString());
    }
}
