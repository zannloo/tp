package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.statemanager.IChildOperation;
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.StudentBuilder;

class CreateStudentCommandTest {
    private Student validStudent = new StudentBuilder().build();
    @Test
    public void constructor_nullPersonNullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateStudentCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
                ) -> new CreateStudentCommand(new RelativePath("~/grp-001/stu-001"), null));
    }

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateStudentCommand(null, validStudent));
    }

    @Test
    void execute_studentAcceptedByGroup_success() throws Exception {
        Student validStudent = new StudentBuilder().build();
        AbsolutePath currPath = new AbsolutePath("~/grp-001/stu-001");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-001/stu-001");

        Student bob = new StudentBuilder()
                .withName("Bob")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("stu-002").build();

        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, bob);
        CommandResult commandResult = createStudentCommand.execute(currPath, root);

        assertEquals(String.format(CreateStudentCommand.MESSAGE_SUCCESS, bob.toString()),
                    commandResult.getFeedbackToUser());
    }
    @Test
        public void execute_duplicateStudent_throwsCommandException() throws InvalidPathException {
        Student validStudent = new StudentBuilder().build();
        AbsolutePath currPath = new AbsolutePath("~/grp-001/stu-001");
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59")));
        TaskList taskList = new TaskList(defaultTaskList);

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(taskList, groups);

        RelativePath path = new RelativePath("~/grp-001/stu-001");

        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, validStudent);
        assertThrows(CommandException.class,
                CreateStudentCommand.MESSAGE_DUPLICATE_STUDENT, (
                    ) -> createStudentCommand.execute(currPath, root)
        );
    }

    @Test
    void testEquals() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001");
        Student alice = new StudentBuilder()
                .withName("Alice")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withAddress("123, Jurong West Ave 6, #08-111")
                .withId("stu-001").build();
        Student bob = new StudentBuilder()
                .withName("Bob")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("stu-002").build();
        CreateStudentCommand createAliceCommand = new CreateStudentCommand(path, alice);
        CreateStudentCommand createBobCommand = new CreateStudentCommand(path, bob);

        // same object -> returns true
        assertTrue(createAliceCommand.equals(createAliceCommand));

        // same values -> returns true
        CreateStudentCommand createAliceCommandCopy = new CreateStudentCommand(path, alice);
        assertTrue(createAliceCommand.equals(createAliceCommandCopy));

        // different types -> returns false
        assertFalse(createAliceCommand.equals(1));

        // null -> returns false
        assertFalse(createAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(createAliceCommand.equals(createBobCommand));
    }

    @Test
    void toString_sameString_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001");
        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, ALICE);
        createStudentCommand.absolutePath = new AbsolutePath("~/grp-001");
        String expected = CreateStudentCommand.class.getCanonicalName() + "{toCreateStudent=" + ALICE + "}";
        assertEquals(expected, createStudentCommand.toString());
    }

    /**
     * A default groupOperation stub that have all of the methods failing.
     */
    private class GroupOperationStub implements IChildOperation<Student> {

        @Override
        public void addChild(Id id, Student child) throws DuplicateChildException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasChild(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student deleteChild(Id id) throws NoSuchChildException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getChild(Id id) throws NoSuchChildException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateChild(Id id, Student child) throws NoSuchChildException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student[] getAllChildren() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int numOfChildren() {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A groupOperation stub that contains a single student.
     */
    private class GroupOperationStubWithStudent extends GroupOperationStub {
        private final Group baseDir;
        private Student child;
        public GroupOperationStubWithStudent(Group baseDir, Student child) {
            this.baseDir = baseDir;
            this.child = child;
        }

        public boolean hasChild(Student student) {
            requireNonNull(student);
            return true;
        }
    }

    /**
     * A GroupOperation stub that always accept the student being added.
     */
    private class GroupOperationStubAlwaysAcceptingStudent extends GroupOperationStub {
        private final Group baseDir;
        private Student child;
        GroupOperationStubAlwaysAcceptingStudent(Group baseDir, Student child) {
            this.baseDir = baseDir;
            this.child = child;
        }
        @Override
        public void addChild(Id id, Student child) throws DuplicateChildException {
            this.baseDir.addChild(id, child);
        }

        public boolean hasChild(Student student) {
            requireNonNull(student);
            return false;
        }
    }
}
