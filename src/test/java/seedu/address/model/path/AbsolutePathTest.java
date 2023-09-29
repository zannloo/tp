package seedu.address.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.path.exceptions.InvalidPathException;

public class AbsolutePathTest {
    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

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

}
