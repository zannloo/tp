package seedu.address.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.exceptions.InvalidPathException;

public class AbsolutePathTest {

    private AbsolutePath rootPath;
    private AbsolutePath studentPath;
    private AbsolutePath groupPath;

    //=========== Constructor =============================================================

    @Test
    public void constructor_pathNotStartedFromRoot_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("grp-001/0001Y");
        });
    }

    @Test
    public void constructor_emptyPath_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("");
        });
    }

    @Test
    public void constructor_invalidPathElement_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/K001Y");
        });
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/ABCDE");
        });
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/0001");
        });
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/123456");
        });
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/group1/0001Y");
        });
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/group-001/0001Y");
        });
    }

    @Test
    public void constructor_validPath_returnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-001/0001Y");
            assertEquals(new AbsolutePath("~/grp-001/0001Y"), path);
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_pathWithValidNavigation_returnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-001/../grp-002/0001Y");
            assertEquals(new AbsolutePath("~/grp-002/0001Y"), path);
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_invalidPathStructure_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/../0001Y");
        });
    }

    @Test
    public void constructor_equivalentPaths_shouldBeEqual() {
        AbsolutePath pathWithDot = null;
        AbsolutePath pathWithoutDot = null;

        try {
            pathWithDot = new AbsolutePath("~/grp-001/./0001Y");
            pathWithoutDot = new AbsolutePath("~/grp-001/0001Y");
        } catch (InvalidPathException e) {
            fail("Unexpected InvalidPathException");
        }

        assertEquals(pathWithDot, pathWithoutDot);
    }

    @Test
    public void equalsMethod() throws InvalidPathException {
        AbsolutePath path = new AbsolutePath("~/grp-001/0001Y");

        // same values -> returns true
        assertTrue(path.equals(new AbsolutePath("~/grp-001/0001Y")));

        // same object -> returns true
        assertTrue(path.equals(path));

        // null -> returns false
        assertFalse(path.equals(null));

        // different values -> returns false
        assertFalse(path.equals(new AbsolutePath("~/grp-001/")));
    }

    @Test
    public void hashCodeMethod() throws InvalidPathException {
        AbsolutePath path1 = new AbsolutePath("~/grp-001/0001Y");
        AbsolutePath path1Copy = new AbsolutePath("~/grp-001/0001Y");
        AbsolutePath path2 = new AbsolutePath("~/grp-001/");
        assertEquals(path1.hashCode(), path1Copy.hashCode());
        assertNotEquals(path1.hashCode(), path2.hashCode());
    }

    //=========== Resolve Method =============================================================

    @Test
    public void resolve_relativeStudentPathWithoutNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/");
        RelativePath relativePath = new RelativePath("0002Y");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals(new AbsolutePath("~/grp-001/0002Y"), resolvedPath);
    }

    @Test
    public void resolve_relativeGroupPathWithoutNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("grp-002");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals(new AbsolutePath("~/grp-002"), resolvedPath);
    }

    @Test
    public void resolve_relativePathWithOneValidNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/0001Y");
        RelativePath relativePath = new RelativePath("../0002Y");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals(new AbsolutePath("~/grp-001/0002Y"), resolvedPath);
    }

    @Test
    public void resolve_relativePathWithTwoValidNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/0001Y");
        RelativePath relativePath = new RelativePath("../../grp-002/0002Y");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals(new AbsolutePath("~/grp-002/0002Y"), resolvedPath);
    }

    @Test
    public void resolve_relativePathStartWithRoot()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/0001Y");
        RelativePath relativePath = new RelativePath("~/grp-002/0003Y");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals(new AbsolutePath("~/grp-002/0003Y"), resolvedPath);
    }

    @Test
    public void resolve_relativePathWithInvalidOneNavigation_throwsInvalidPathException()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/0001Y");
        RelativePath relativePath = new RelativePath("../grp-002/0002Y");

        assertThrows(InvalidPathException.class, () -> absolutePath.resolve(relativePath));
    }

    @Test
    public void resolve_relativePathWithInvalidNavigationAboveRoot_throwsInvalidPathException()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/0001Y");
        RelativePath relativePath = new RelativePath("../../../grp-002");

        assertThrows(InvalidPathException.class, () -> absolutePath.resolve(relativePath));
    }

    //=========== Check Directory Method =============================================================
    @BeforeEach
    public void setUp() {
        try {
            rootPath = new AbsolutePath("~/");
            studentPath = new AbsolutePath("~/grp-001/0001Y");
            groupPath = new AbsolutePath("~/grp-001");
        } catch (InvalidPathException e) {
            fail("Unexpected Exception.");
        }
    }

    @Test
    public void isRootDirectory_rootPath_returnsTrue() {
        assertTrue(rootPath.isRootDirectory());
    }

    @Test
    public void isRootDirectory_studentPath_returnsFalse() {
        assertFalse(studentPath.isRootDirectory());
    }

    @Test
    public void isRootDirectory_groupPath_returnsFalse() {
        assertFalse(groupPath.isRootDirectory());
    }

    @Test
    public void isStudentDirectory_studentPath_returnsTrue() {
        assertTrue(studentPath.isStudentDirectory());
    }

    @Test
    public void isStudentDirectory_groupPath_returnsFalse() {
        assertFalse(groupPath.isStudentDirectory());
    }

    @Test
    public void isStudentDirectory_rootPath_returnsFalse() {
        assertFalse(rootPath.isStudentDirectory());
    }

    @Test
    public void isGroupDirectory_groupPath_returnsTrue() {
        assertTrue(groupPath.isGroupDirectory());
    }

    @Test
    public void isGroupDirectory_studentPath_returnsFalse() {
        assertFalse(studentPath.isGroupDirectory());
    }

    @Test
    public void isGroupDirectory_rootPath_returnsFalse() {
        assertFalse(rootPath.isGroupDirectory());
    }

    //=========== Get ID Method =============================================================
    @Test
    public void getStudentId_studentPath_returnsValidStudentId() {
        Optional<StudentId> studentId = studentPath.getStudentId();
        assertTrue(studentId.isPresent());
        assertEquals(new StudentId("0001Y"), studentId.get());
    }

    @Test
    public void getGroupId_studentPath_returnsValidStudentId() {
        Optional<GroupId> groupId = studentPath.getGroupId();
        assertTrue(groupId.isPresent());
        assertEquals(new GroupId("GRP-001"), groupId.get());
    }

    @Test
    public void getGroupId_groupPath_returnsValidGroupId() {
        Optional<GroupId> groupId = groupPath.getGroupId();
        assertTrue(groupId.isPresent());
        assertEquals(new GroupId("GRP-001"), groupId.get());
    }

    @Test
    public void getStudentId_rootPath_returnEmptyOptional() {
        assertFalse(rootPath.getStudentId().isPresent());
    }

    @Test
    public void getStudentId_groupPath_returnEmptyOptional() {
        assertFalse(groupPath.getStudentId().isPresent());
    }

    @Test
    public void getGroupId_rootPath_returnEmptyOptional() {
        assertFalse(rootPath.getGroupId().isPresent());
    }
}
