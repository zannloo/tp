package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

public class DeleteTaskCommandTest {
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

        Task deletedTask = operation.deleteTask(FIRST_INDEX.getOneBased());
        expectedModel.updateList();

        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(FIRST_INDEX);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                deletedTask.toString());

        assertCommandSuccess(deleteCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        TaskOperation operation = actualModel.taskOperation(displayPath);
        Index outOfBoundIndex = Index.fromOneBased(operation.getTaskListSize() + 1);
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, actualModel, MESSAGE_INVALID_INDEX);
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
