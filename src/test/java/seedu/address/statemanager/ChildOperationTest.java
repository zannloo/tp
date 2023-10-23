package seedu.address.statemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
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
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.ChildOperation;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.StateManager;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.testutil.StudentBuilder;

public class ChildOperationTest {
    private Root root;
    private Group group;
    private Student student;

    private AbsolutePath rootPath;
    private AbsolutePath grpPath;
    private AbsolutePath stuPath;

    private State state;

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
    public void getChildOperation_noErrorReturn() {
        assertEquals(new ChildOperation<>(this.root), state.rootChildOperation());
        assertEquals(new ChildOperation<>(this.group.getChildrenManger()), state.groupChildOperation(grpPath));
        assertEquals(new ChildOperation<>(this.group.getChildrenManger()), state.groupChildOperation(stuPath));
    }

    @Test
    public void getGroupChildOperationWithRoot_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> state.groupChildOperation(rootPath));
    }

    @Test
    public void childOperationVerifyDeleteAndAdd_noError() {
        StudentId stu = new StudentId("0001Y");
        ChildOperation<Student> opr = state.groupChildOperation(grpPath);
        assertTrue(opr.hasChild(stu));
        opr.deleteChild(stu);
        assertFalse(opr.hasChild(stu));
        opr.addChild(stu, this.student);
        assertTrue(opr.hasChild(stu));
    }

    @Test
    public void childOperationVerifyGet_noError() {
        StudentId stu = new StudentId("0001Y");
        ChildOperation<Student> opr = state.groupChildOperation(grpPath);
        assertTrue(opr.hasChild(stu));
        assertEquals(this.student, opr.getChild(stu));
    }

    @Test
    public void childOperationAddDuplicateChild_exceptionThrown() {
        StudentId stu = new StudentId("0001Y");
        try {
            ChildOperation<Student> opr = state.groupChildOperation(grpPath);
            assertTrue(opr.hasChild(stu));
            opr.addChild(stu, this.student);
            fail();
        } catch (DuplicateChildException e) {
            assertEquals("Operation would result in duplicate " + stu, e.getMessage());
        }
    }

    @Test
    public void childOperationVerifyGetAll_noError() {
        ChildOperation<Student> opr = state.groupChildOperation(grpPath);
        ArrayList<Student> list = new ArrayList<>();
        list.add(this.student);
        assertEquals(opr.getAllChildren(), list);
    }

    @Test
    public void childOperationVerifyUpdate_noError() {
        StudentId stu = new StudentId("0001Y");
        Student newStu = new StudentBuilder()
                .withName("angel")
                .withEmail("angelyipenqi@example.com")
                .withPhone("1234567")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withTags("owesMoney", "friends")
                .withId("0001Y").build();
        ChildOperation<Student> opr = state.groupChildOperation(grpPath);
        assertTrue(opr.hasChild(stu));
        opr.updateChild(stu, newStu);
        assertEquals(newStu, opr.getChild(stu));
    }
}
