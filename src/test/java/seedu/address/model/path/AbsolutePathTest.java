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
    public void constructor_validPath_shouldReturnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-1/stu-1");
            assertEquals("~/grp-1/stu-1", path.toString());
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_pathWithValidNavigation_shouldReturnValidPath() {
        try {
            AbsolutePath path = new AbsolutePath("~/grp-1/../grp-2/stu-1");
            logger.info(path.toString());
            assertEquals("~/grp-2/stu-1", path.toString());
        } catch (InvalidPathException e) {
            fail("Expected no InvalidPathException, but got one.");
        }
    }

    @Test
    public void constructor_invalidPathStructure_shouldThrowInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new AbsolutePath("~/grp-1/../stu-1");
        });
    }

    @Test
    public void constructor_equivalentPaths_shouldBeEqual() {
        // Create two AbsolutePath objects representing the same path
        AbsolutePath pathWithDot = null;
        AbsolutePath pathWithoutDot = null;

        try {
            pathWithDot = new AbsolutePath("~/grp-1/./stu-1");
            pathWithoutDot = new AbsolutePath("~/grp-1/stu-1");
        } catch (InvalidPathException e) {
            fail("Unexpected InvalidPathException");
        }

        // Check that the two paths are equal
        assertEquals(pathWithDot.toString(), pathWithoutDot.toString());
    }
}
    