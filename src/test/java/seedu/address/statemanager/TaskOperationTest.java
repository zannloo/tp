package seedu.address.statemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.statemanager.TaskOperation;
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;
import seedu.address.testutil.StudentBuilder;


public class TaskOperationTest {

    private Root root;
    private Group group;
    private Student student;

    private AbsolutePath rootPath;
    private AbsolutePath grpPath;
    private AbsolutePath stuPath;
    private State state;
    private final Task task = new Deadline("Assignment 3", LocalDateTime.parse("2023-12-03T23:59"));

    @BeforeEach
    public void init() {
        try {
            rootPath = new AbsolutePath("~/");
            grpPath = new AbsolutePath("~/grp-001");
            stuPath = new AbsolutePath("~/grp-001/0001Y");
        } catch (InvalidPathException e) {
            fail();
            return;
        }
        this.student = new StudentBuilder()
                .withName("zann")
                .withEmail("zannwhatudoing@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("0001Y").build();
        Map<Id, Student> studentMap = new HashMap<>();
        studentMap.put(new StudentId("0001Y"), this.student);
        this.group = new Group(new TaskList(null), studentMap, new Name("gary"), new GroupId("grp-001"));
        Map<Id, Group> groups = new HashMap<>();
        groups.put(new GroupId("grp-001"), this.group);
        this.root = new Root(groups);
        state = new StateManager(rootPath, root, new UserPrefs());
    }

    @Test
    public void getTaskOperation_noErrorReturn() {
        assertEquals(new TaskOperation(this.group.getTaskListManager()), state.taskOperation(grpPath));
        assertEquals(new TaskOperation(this.student), state.taskOperation(stuPath));
    }

    @Test
    public void taskOperationVerifyDeleteMethod_noErrorReturn() {
        TaskOperation opr;
        opr = state.taskOperation(stuPath);
        assertTrue(this.student.checkDuplicates(task));
        try {
            for (Task t : this.student.getAllTask()) {
                System.out.println(t);
            }
            opr.deleteTask(1);
            for (Task t : this.student.getAllTask()) {
                System.out.println(t);
            }

        } catch (NoSuchTaskException e) {
            fail();
        }
        assertFalse(this.student.checkDuplicates(task));
    }

    @Test
    public void taskOperationVerifyAdd_noErrorReturn() {
        TaskOperation opr;
        opr = state.taskOperation(stuPath);
        opr.deleteTask(1);
        assertFalse(opr.hasTask(task));
        opr.addTask(task);
        assertTrue(this.student.checkDuplicates(task));
    }

    @Test
    public void taskOperationVerifyMarkUnmark_noErrorReturn() {
        TaskOperation opr;
        opr = state.taskOperation(stuPath);
        List<Task> ret = opr.findTask("Assignment");
        assertEquals(ret.get(0), this.task);
        ret = opr.findTask("not here");
        assertEquals(0, ret.size());
    }

    @Test
    public void taskOperationVerifyGetTasks_noErrorReturn() {
        TaskOperation opr;
        opr = state.taskOperation(stuPath);
        assertEquals(opr.getTask(1), this.task);
    }

    @Test
    public void taskOperationVerifyGetAllTasks_noErrorReturn() {
        TaskOperation opr;
        opr = state.taskOperation(stuPath);
        ArrayList<Task> list = new ArrayList<>();
        list.add(this.task);
        assertEquals(opr.getAllTasks(), list);
    }
}
