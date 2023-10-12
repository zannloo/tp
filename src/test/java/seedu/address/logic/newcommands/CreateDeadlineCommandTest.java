package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.newcommands.CreateDeadlineCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.TaskListManager;
import seedu.address.model.statemanager.GroupOperation;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.StudentOperation;
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;
import seedu.address.testutil.GroupBuilder;


class CreateDeadlineCommandTest {
    @Test
    public void execute_deadlineForStudentAccepted_addSuccessful() throws InvalidPathException,
            UnsupportedPathOperationException, NoSuchTaskException, CommandException, InvalidIdException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/stu-001");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 3", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);
        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(grp.getId(), grp);
        Root root = new Root(taskList, groups);

        StateManagerStubAcceptingDeadlineAdded managerStub =
                new StateManagerStubAcceptingDeadlineAdded(new TaskListManager(taskList));

        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 3", duedate);

        RelativePath path = new RelativePath("~/grp-001/stu-001");
        AbsolutePath absolutePath = currPath.resolve(path);

        StudentOperation student = managerStub.studentOperation(root, absolutePath);
        student.addTask(deadline);

        GroupOperation group = managerStub.groupOperation(root, absolutePath);
        StudentId temp = absolutePath.getStudentId().get();
        Student stu = group.getChild(temp);

        CommandResult returnStatement =
                new CommandResult(String.format(MESSAGE_SUCCESS, stu.toString()));

        assertEquals(String.format(CreateDeadlineCommand.MESSAGE_SUCCESS, stu.toString()),
                returnStatement.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateDeadline_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/stu-001");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-001/stu-001");
        Deadline deadline = new Deadline("Assignment 3", LocalDateTime.parse("2023-12-03T23:59"));

        CreateDeadlineCommand createDeadlineCommand = new CreateDeadlineCommand(path, deadline);

        assertThrows(CommandException.class,
                CreateDeadlineCommand.MESSAGE_DUPLICATE_DEADLINE_TASK, (
                    ) -> createDeadlineCommand.execute(currPath, root)
        );
    }

    @Test
    void equals_sameInstanceForStudentDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);

        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameInstanceForGroupDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);

        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameCommandForStudentDirectory_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_sameCommandForGroupDirectory_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001");

        RelativePath path2 = new RelativePath("~/grp-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001");

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_differentDescriptionForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentPathForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-002");
        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentDueDateForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentFieldsForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void toString_sameStringForStudentDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);
        String expected = "seedu.address.logic.newcommands.CreateDeadlineCommand{"
                        + "toCreateDeadline=Deadline has been added:\n"
                        + "[D][ ] Assignment 1(by: 2023-12-03T23:58)}";

        assertEquals(expected, command.toString());
    }

    private class StateManagerStub extends StateManager {
        private final Logger logger = LogsCenter.getLogger(JsonUtil.class);

        public StateManagerStub(TaskListManager baseDir) {
            super(baseDir);
        }

        @Override
        public void addTask(Task t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task deleteTask(int index) throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task markTask(int index) throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task unmarkTask(int index) throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Task> findTask(String query) throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task getTask(int index) throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Task> getAllTasks() throws NoSuchTaskException {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class StateManagerStubAcceptingDeadlineAdded extends StateManagerStub {
        private final Logger logger = LogsCenter.getLogger(JsonUtil.class);

        private final TaskListManager baseDir;

        StateManagerStubAcceptingDeadlineAdded(TaskListManager baseDir) {
            super(baseDir);
            requireNonNull(baseDir);
            this.baseDir = baseDir;
        }

        void stateLogger(String log) {
            this.logger.info(log);
        }

        void stateErrorLogger(String errMsg) {
            this.logger.severe(errMsg);
        }

        @Override
        public void addTask(Task task) {
            this.baseDir.addTask(task);
            this.stateLogger("Adding" + task.toString());
        }
    }
}

