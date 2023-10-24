package seedu.address.model.path.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.path.element.exceptions.InvalidPathElementException;

public class PathElementTest {
    private PathElement validRootElement;
    private PathElement validGroupElement;
    private PathElement validStudentElement;
    private PathElement validParentElement;
    private PathElement validCurrentElement;
    private PathElement validTutorialSlotElement;


    @BeforeEach
    public void setup() throws InvalidPathElementException {
        validRootElement = PathElement.parsePathElement("~");
        validGroupElement = PathElement.parsePathElement("grp-001");
        validStudentElement = PathElement.parsePathElement("0001Y");
        validParentElement = PathElement.parsePathElement("..");
        validCurrentElement = PathElement.parsePathElement(".");
        validTutorialSlotElement = PathElement.parsePathElement("tut-001");
    }

    @Test
    public void parsePathElement_validStudentId_returnsStudentIdType() throws InvalidPathElementException {
        PathElement pathElement = PathElement.parsePathElement("0002Y");
        assertEquals(PathElementType.STUDENTID, pathElement.getType());
    }

    @Test
    public void parsePathElement_validGroupId_returnsGroupIdType() throws InvalidPathElementException {
        PathElement pathElement = PathElement.parsePathElement("grp-002");
        assertEquals(PathElementType.GROUPID, pathElement.getType());
    }

    @Test
    public void parsePathElement_rootElement_returnsRootType() throws InvalidPathElementException {
        PathElement pathElement = PathElement.parsePathElement("~");
        assertEquals(PathElementType.ROOT, pathElement.getType());
    }

    @Test
    public void parsePathElement_parentElement_returnsParentType() throws InvalidPathElementException {
        PathElement pathElement = PathElement.parsePathElement("..");
        assertEquals(PathElementType.PARENT, pathElement.getType());
    }

    @Test
    public void parsePathElement_currentElement_returnsCurrentType() throws InvalidPathElementException {
        PathElement pathElement = PathElement.parsePathElement(".");
        assertEquals(PathElementType.CURRENT, pathElement.getType());
    }

    @Test
    public void parsePathElement_invalidElement_throwsInvalidPathElementException() {
        assertThrows(InvalidPathElementException.class, () -> {
            PathElement.parsePathElement("invalid");
        });
    }

    @Test
    public void getPriorityDiff_higherPriority_returnsPositiveDifference() throws InvalidPathElementException {
        assertTrue(validRootElement.getPriorityDiff(validTutorialSlotElement) == 1);
        assertTrue(validTutorialSlotElement.getPriorityDiff(validGroupElement) == 1);
        assertTrue(validGroupElement.getPriorityDiff(validStudentElement) == 1);
    }

    @Test
    public void getPriorityDiff_lowerPriority_returnsNegativeDifference() throws InvalidPathElementException {
        assertTrue(validStudentElement.getPriorityDiff(validGroupElement) == -1);
        assertTrue(validGroupElement.getPriorityDiff(validTutorialSlotElement) == -1);
        assertTrue(validTutorialSlotElement.getPriorityDiff(validRootElement) == -1);
    }

    @Test
    public void equals_samePathElement_returnsTrue() throws InvalidPathElementException {
        PathElement rootCopy = PathElement.parsePathElement("~");
        assertEquals(rootCopy, validRootElement);
    }

    @Test
    public void equals_differentPathElements_returnsFalse() throws InvalidPathElementException {
        assertNotEquals(validRootElement, validParentElement);
        assertNotEquals(validCurrentElement, null);
    }

    @Test
    public void hashCode_samePathElement_returnsSameHashCode() throws InvalidPathElementException {
        PathElement rootCopy = PathElement.parsePathElement("~");
        assertEquals(rootCopy.hashCode(), validRootElement.hashCode());
    }

    @Test
    public void hashCode_differentPathElements_returnsDifferentHashCodes() throws InvalidPathElementException {
        assertNotEquals(validRootElement.hashCode(), validGroupElement.hashCode());
    }
}
