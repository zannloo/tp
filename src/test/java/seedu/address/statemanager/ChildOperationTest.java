package seedu.address.statemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.statemanager.ChildOperation;
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

    @BeforeEach
    public void init() {
        try {
            rootPath = new AbsolutePath("~/");
            grpPath = new AbsolutePath("~/grp-001");
            stuPath = new AbsolutePath("~/grp-001/stu-001");
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
                .withId("stu-001").build();
        Map<Id, Student> studentMap = new HashMap<>();
        studentMap.put(new StudentId("stu-001"), this.student);
        this.group = new Group(new TaskList(null), studentMap, new Name("gary"), new GroupId("grp-001"));
        Map<Id, Group> groups = new HashMap<>();
        groups.put(new GroupId("grp-001"), this.group);
        this.root = new Root(new TaskList(null), groups);
    }

    @Test
    public void getChildOperation_noErrorReturn() {
        try {
            assertEquals(new ChildOperation<>(this.root), StateManager.rootChildOperation(root));
            assertEquals(new ChildOperation<>(this.group), StateManager.groupChildOperation(root, grpPath));
            assertEquals(new ChildOperation<>(this.group), StateManager.groupChildOperation(root, stuPath));
        } catch (UnsupportedPathOperationException e) {
            fail();
        }
    }

    @Test
    public void getGroupChildOperationWithRoot_exceptionThrown() {
        try {
            assertEquals(new ChildOperation<>(this.group), StateManager.groupChildOperation(root, rootPath));
            fail();
        } catch (UnsupportedPathOperationException e) {
            assertEquals("Not a group directory or a student directory", e.getMessage());
        }
    }

    @Test
    public void childOperationVerifyDeleteAndAdd_noError() {
        try {
            StudentId stu = new StudentId("stu-001");
            ChildOperation<Student> opr = StateManager.groupChildOperation(root, grpPath);
            assertTrue(opr.hasChild(stu));
            opr.deleteChild(stu);
            assertFalse(opr.hasChild(stu));
            opr.addChild(stu, this.student);
            assertTrue(opr.hasChild(stu));
        } catch (UnsupportedPathOperationException | DuplicateChildException e) {
            fail();
        }
    }

    @Test
    public void childOperationVerifyGet_noError() {
        try {
            StudentId stu = new StudentId("stu-001");
            ChildOperation<Student> opr = StateManager.groupChildOperation(root, grpPath);
            assertTrue(opr.hasChild(stu));
            assertEquals(this.student, opr.getChild(stu));
        } catch (UnsupportedPathOperationException e) {
            fail();
        }
    }

    @Test
    public void childOperationAddDuplicateChild_exceptionThrown() {
        StudentId stu = new StudentId("stu-001");
        try {
            ChildOperation<Student> opr = StateManager.groupChildOperation(root, grpPath);
            assertTrue(opr.hasChild(stu));
            opr.addChild(stu, this.student);
            fail();
        } catch (DuplicateChildException e) {
            assertEquals("Operation would result in duplicate " + stu, e.getMessage());
        } catch (UnsupportedPathOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void childOperationVerifyGetAll_noError() {
        try {
            ChildOperation<Student> opr = StateManager.groupChildOperation(root, grpPath);
            ArrayList<Student> list = new ArrayList<>();
            list.add(this.student);
            assertEquals(opr.getAllChildren(), list);
        } catch (UnsupportedPathOperationException e) {
            fail();
        }
    }

    @Test
    public void childOperationVerifyUpdate_noError() {
        try {
            StudentId stu = new StudentId("stu-001");
            Student newStu = new StudentBuilder()
                    .withName("angel")
                    .withEmail("angelyipenqi@example.com")
                    .withPhone("1234567")
                    .withAddress("311, Clementi Ave 2, #02-25")
                    .withTags("owesMoney", "friends")
                    .withId("stu-001").build();
            ChildOperation<Student> opr = StateManager.groupChildOperation(root, grpPath);
            assertTrue(opr.hasChild(stu));
            opr.updateChild(stu, newStu);
            assertEquals(newStu, opr.getChild(stu));
        } catch (UnsupportedPathOperationException e) {
            fail();
        }
    }
}
