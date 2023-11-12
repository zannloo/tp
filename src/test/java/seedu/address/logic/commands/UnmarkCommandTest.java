package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UnmarkCommand.MESSAGE_MARK_TASK_SUCCESS;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalTasks.TASK_LIST_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskOperation;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.task.Task;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.RootBuilder;

public class UnmarkCommandTest {

    private Model actualModel;

    private Model expectedModel;

    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    private AbsolutePath displayPath;

    @BeforeEach
    public void setup() throws InvalidPathException {
        Group groupOneWithTasks = new GroupBuilder(GROUP_ONE).withTaskList(TASK_LIST_1).build();
        Root root = new RootBuilder().withGroup(groupOneWithTasks).build();
        actualModel = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());

        // Display task
        RelativePath groupPath = new RelativePath(groupOneWithTasks.getId().toString());
        displayPath = rootPath.resolve(groupPath);

        actualModel.setDisplayPath(displayPath);
        actualModel.showTaskList();
        expectedModel.setDisplayPath(displayPath);
        expectedModel.showTaskList();
    }

    @Test
    public void execute_validIndex_success() {
        TaskOperation operation = expectedModel.taskOperation(displayPath);

        Task unmarkedTask = operation.unmarkTask(FIRST_INDEX.getOneBased());
        expectedModel.updateList();

        UnmarkCommand command = new UnmarkCommand(FIRST_INDEX);
        String expectedMessage = String.format(MESSAGE_MARK_TASK_SUCCESS,
                unmarkedTask.toString());

        assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        TaskOperation operation = actualModel.taskOperation(displayPath);
        Index outOfBoundIndex = Index.fromOneBased(operation.getTaskListSize() + 1);
        UnmarkCommand deleteCommand = new UnmarkCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, actualModel, MESSAGE_INVALID_INDEX);
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
    public void toString_successfullyUnmarkTask_returnExpectedString() {
        Index targetIndex = Index.fromOneBased(1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(targetIndex);
        String expected = UnmarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, unmarkCommand.toString());
    }
}
