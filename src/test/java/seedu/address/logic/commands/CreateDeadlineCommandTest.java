package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskOperation;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Deadline;
import seedu.address.testutil.TypicalGroups;
import seedu.address.testutil.TypicalRoots;
import seedu.address.testutil.TypicalStudents;
import seedu.address.testutil.TypicalTasks;


class CreateDeadlineCommandTest {
    private Model model;
    private Model expectedModel;
    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();
    private Deadline toBeAdded = TypicalTasks.DEADLINE_1;

    @BeforeEach
    public void setup() {
        model = new ModelManager(rootPath, new Root(TypicalRoots.PROFBOOK_WITH_TWO_GROUPS), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(TypicalRoots.PROFBOOK_WITH_TWO_GROUPS), new UserPrefs());
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateDeadlineCommand(null, null));
    }

    @Test
    public void execute_deadlineForStudentAccepted_addSuccessful() throws InvalidPathException,
            UnsupportedPathOperationException, CommandException {
        Student alice = TypicalStudents.ALICE;
        Group aliceGroup = TypicalGroups.GROUP_ONE;

        RelativePath groupPath = new RelativePath(aliceGroup.getId().toString());
        RelativePath alicePath = new RelativePath(alice.getId().toString());
        AbsolutePath absoluteTargetPath = rootPath.resolve(groupPath).resolve(alicePath);

        // Add task to expectedModel
        TaskOperation operation = expectedModel.taskOperation(absoluteTargetPath);
        operation.addTask(toBeAdded);

        CreateDeadlineCommand command = new CreateDeadlineCommand(absoluteTargetPath, toBeAdded);
        String expectedMessage = String.format(CreateDeadlineCommand.MESSAGE_SUCCESS,
                toBeAdded);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deadlineForAllStudentsInGroupAccepted_addSuccessful()
            throws InvalidPathException, CommandException {
        Group targetGroup = TypicalGroups.GROUP_ONE;

        RelativePath groupPath = new RelativePath(targetGroup.getId().toString());
        AbsolutePath absoluteTargetPath = rootPath.resolve(groupPath);

        ChildOperation<Student> operation = expectedModel.groupChildOperation(absoluteTargetPath);
        operation.addTaskToAllChildren(toBeAdded, 1);

        CreateDeadlineCommand command = new CreateDeadlineCommand(absoluteTargetPath, toBeAdded, "allStu");
        String expectedMessage = CreateDeadlineCommand.MESSAGE_SUCCESS_ALL_STUDENTS;

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deadlineForAllGroupsInRootAccepted_addSuccessful()
            throws InvalidPathException, CommandException {
        ChildOperation<Group> operation = expectedModel.rootChildOperation();
        operation.addTaskToAllChildren(toBeAdded, 1);

        CreateDeadlineCommand command = new CreateDeadlineCommand(rootPath, toBeAdded, "allGrp");
        String expectedMessage = CreateDeadlineCommand.MESSAGE_SUCCESS_ALL_GROUPS;

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDeadline_throwsCommandException() throws InvalidPathException {
        Student alice = TypicalStudents.ALICE;
        Group aliceGroup = TypicalGroups.GROUP_ONE;

        RelativePath groupPath = new RelativePath(aliceGroup.getId().toString());
        RelativePath alicePath = new RelativePath(alice.getId().toString());
        AbsolutePath absoluteTargetPath = rootPath.resolve(groupPath).resolve(alicePath);

        // Add task to model first
        TaskOperation operation = model.taskOperation(absoluteTargetPath);
        operation.addTask(toBeAdded);

        CreateDeadlineCommand command = new CreateDeadlineCommand(absoluteTargetPath, toBeAdded);
        String expectedMessage = CreateDeadlineCommand.MESSAGE_DUPLICATE_DEADLINE_TASK;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        CreateDeadlineCommand createDeadlineCommand1 = new CreateDeadlineCommand(rootPath, TypicalTasks.DEADLINE_1);
        CreateDeadlineCommand createDeadlineCommand2 = new CreateDeadlineCommand(rootPath, TypicalTasks.DEADLINE_2);

        // same object -> returns true
        assertEquals(createDeadlineCommand1, createDeadlineCommand1);

        // same values -> returns true
        CreateDeadlineCommand createDeadlineCommand1Copy = new CreateDeadlineCommand(rootPath, TypicalTasks.DEADLINE_1);
        assertEquals(createDeadlineCommand1, createDeadlineCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, createDeadlineCommand1);

        // null -> returns false
        assertNotEquals(null, createDeadlineCommand1);

        // different values
        assertNotEquals(createDeadlineCommand1, createDeadlineCommand2);
    }

    @Test
    public void toStringMethod() {
        CreateDeadlineCommand createDeadlineCommand = new CreateDeadlineCommand(rootPath, TypicalTasks.DEADLINE_1);
        String expected = CreateDeadlineCommand.class.getCanonicalName()
            + "{toCreateDeadline=" + TypicalTasks.DEADLINE_1 + "}";
        assertEquals(expected, createDeadlineCommand.toString());
    }
}

