package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
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
        ) -> new CreateStudentCommand(new AbsolutePath("~/grp-001/0001Y"), null));
    }

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateStudentCommand(null, validStudent));
    }

    @Test
    void execute_studentAcceptedByGroup_success() throws Exception {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");

        Map<Id, Group> groups = new HashMap<>();
        Group grp = new GroupBuilder().build();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-001/0002Y");

        Student bob = new StudentBuilder()
                .withName("Bob")
                .withEmail("bobthebuilder@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("0002Y").build();

        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, bob);
        State state = new StateManager(currPath, root, new UserPrefs());
        CommandResult commandResult = createStudentCommand.execute(state);

        assertEquals(String.format(CreateStudentCommand.MESSAGE_SUCCESS, bob.toString()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() throws InvalidPathException {
        AbsolutePath currPath = new AbsolutePath("~/grp-001/");
        Student duplicatedStudent = new StudentBuilder()
                .withName("alice")
                .withEmail("aliceinwonderland@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("friends")
                .withId("0001Y").build();
        Map<Id, Student> studentMap = new HashMap<>();
        studentMap.put(duplicatedStudent.getId(), duplicatedStudent);
        Group grp = new Group(new TaskList(null), studentMap, new Name("ProfBook"), new GroupId("grp-001"));
        Map<Id, Group> groups = new HashMap<>();
        groups.put(new GroupId("grp-001"), grp);
        Root root = new Root(groups);

        AbsolutePath path = new AbsolutePath("~/grp-001/0001Y");

        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, validStudent);
        State state = new StateManager(currPath, root, new UserPrefs());
        assertThrows(CommandException.class,
                CreateStudentCommand.MESSAGE_DUPLICATE_STUDENT, (
                ) -> createStudentCommand.execute(state)
        );
    }

    @Test
    void testEquals() throws InvalidPathException {
        AbsolutePath path = new AbsolutePath("~/grp-001");
        Student alice = new StudentBuilder()
                .withName("Alice")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withAddress("123, Jurong West Ave 6, #08-111")
                .withId("0001Y").build();
        Student bob = new StudentBuilder()
                .withName("Bob")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("0002Y").build();
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
        AbsolutePath path = new AbsolutePath("~/grp-001");
        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, ALICE);
        String expected = CreateStudentCommand.class.getCanonicalName() + "{toCreateStudent=" + ALICE + "}";
        assertEquals(expected, createStudentCommand.toString());
    }
}
