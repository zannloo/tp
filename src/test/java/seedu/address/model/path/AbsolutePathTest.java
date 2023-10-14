package seedu.address.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;

public class AbsolutePathTest {

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);
    private AbsolutePath rootPath;
    private AbsolutePath studentPath;
    private AbsolutePath groupPath;

    //=========== Constructor =============================================================

    @Test
    public void constructor_pathNotStartedFromRoot_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("grp-001/stu-001");
        });
    }

    @Test
    public void constructor_validPath_returnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-001/stu-001");
            assertEquals("~/grp-001/stu-001", path.toString());
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_pathWithValidNavigation_returnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-001/../grp-002/stu-001");
            logger.info(path.toString());
            assertEquals("~/grp-002/stu-001", path.toString());
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_invalidPathStructure_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-001/../stu-001");
        });
    }

    @Test
    public void constructor_equivalentPaths_shouldBeEqual() {
        AbsolutePath pathWithDot = null;
        AbsolutePath pathWithoutDot = null;

        try {
            pathWithDot = new AbsolutePath("~/grp-001/./stu-001");
            pathWithoutDot = new AbsolutePath("~/grp-001/stu-001");
        } catch (InvalidPathException e) {
            fail("Unexpected InvalidPathException");
        }

        assertEquals(pathWithDot.toString(), pathWithoutDot.toString());
    }

    //=========== Resolve Method =============================================================

    @Test
    public void resolve_relativeStudentPathWithoutNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/");
        RelativePath relativePath = new RelativePath("stu-002");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals("~/grp-001/stu-002", resolvedPath.toString());
    }

    @Test
    public void resolve_relativeGroupPathWithoutNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/");
        RelativePath relativePath = new RelativePath("grp-002");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals("~/grp-002", resolvedPath.toString());
    }

    @Test
    public void resolve_relativePathWithOneValidNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/stu-001");
        RelativePath relativePath = new RelativePath("../stu-002");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals("~/grp-001/stu-002", resolvedPath.toString());
    }

    @Test
    public void resolve_relativePathWithTwoValidNavigation_returnNewAbsolutePath()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/stu-001");
        RelativePath relativePath = new RelativePath("../../grp-002/stu-002");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals("~/grp-002/stu-002", resolvedPath.toString());
    }

    @Test
    public void resolve_relativePathStartWithRoot()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/stu-001");
        RelativePath relativePath = new RelativePath("~/grp-002/stu-003");

        AbsolutePath resolvedPath = absolutePath.resolve(relativePath);

        assertEquals("~/grp-002/stu-003", resolvedPath.toString());
    }

    @Test
    public void resolve_relativePathWithInvalidOneNavigation_throwsInvalidPathException()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/stu-001");
        RelativePath relativePath = new RelativePath("../grp-002/stu-002");

        assertThrows(InvalidPathException.class, () -> absolutePath.resolve(relativePath));
    }

    @Test
    public void resolve_relativePathWithInvalidNavigationAboveRoot_throwsInvalidPathException()
            throws InvalidPathException {
        AbsolutePath absolutePath = new AbsolutePath("~/grp-001/stu-001");
        RelativePath relativePath = new RelativePath("../../../grp-002");

        assertThrows(InvalidPathException.class, () -> absolutePath.resolve(relativePath));
    }

    //=========== Check Directory Method =============================================================
    @BeforeEach
    public void setUp() {
        try {
            rootPath = new AbsolutePath("~/");
            studentPath = new AbsolutePath("~/grp-001/stu-001");
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
    public void getStudentId_studentPath_returnsValidStudentId()
            throws UnsupportedPathOperationException, InvalidIdException {
        Optional<StudentId> studentId = studentPath.getStudentId();
        assertTrue(studentId.isPresent());
        assertEquals("stu-001", studentId.get().toString());
    }

    @Test
    public void getGroupId_studentPath_returnsValidStudentId()
            throws UnsupportedPathOperationException, InvalidIdException {
        Optional<GroupId> groupId = studentPath.getGroupId();
        assertTrue(groupId.isPresent());
        assertEquals("grp-001", groupId.get().toString());
    }

    @Test
    public void getGroupId_groupPath_returnsValidGroupId()
            throws UnsupportedPathOperationException, InvalidIdException {
        Optional<GroupId> groupId = groupPath.getGroupId();
        assertTrue(groupId.isPresent());
        assertEquals("grp-001", groupId.get().toString());
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
