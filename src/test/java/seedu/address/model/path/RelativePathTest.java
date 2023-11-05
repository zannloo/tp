package seedu.address.model.path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.exceptions.InvalidPathException;

public class RelativePathTest {
    @Test
    public void constructor_invalidPathElement_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("/grp-001/K001Y");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("ABCDE");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("~/grp-001/0001");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("~/grp-001/123456");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("~/group1/0001Y");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("~/group-001/0001Y");
        });
    }

    @Test
    public void constructor_emptyPath_throwInvalidPathException() {
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("");
        });
    }

    @Test
    public void constructor_validPath_returnValidPath() throws InvalidPathException {
        new RelativePath("grp-001/0001Y");
        new RelativePath(".");
        new RelativePath("~");
        new RelativePath("0001Y");
        new RelativePath("grp-001");
        new RelativePath("~/grp-001/0001Y");
        new RelativePath("~/grp-001/./0001Y");
        new RelativePath("~/./grp-001/./0001Y");
    }

    @Test
    public void constructor_invalidPathStructure_throwsInvalidPathException() throws InvalidPathException {
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("~/0001Y");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("./0001Y/grp-001");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("grp-001/~");
        });
        assertThrows(InvalidPathException.class, () -> {
            new RelativePath("0001Y/~");
        });
    }

    @Test
    public void equals() throws InvalidPathException {
        RelativePath path = new RelativePath("grp-001/0001Y");

        // same values -> returns true
        assertTrue(path.equals(new RelativePath("grp-001/0001Y")));

        // same object -> returns true
        assertTrue(path.equals(path));

        // null -> returns false
        assertFalse(path.equals(null));

        // different values -> returns false
        assertFalse(path.equals(new RelativePath("grp-001")));
    }

    @Test
    public void hashCodeMethod() throws InvalidPathException {
        RelativePath path1 = new RelativePath("../grp-001");
        RelativePath path1Copy = new RelativePath("../grp-001");
        RelativePath path2 = new RelativePath("../");
        assertEquals(path1.hashCode(), path1Copy.hashCode());
        assertNotEquals(path1.hashCode(), path2.hashCode());
    }
}
