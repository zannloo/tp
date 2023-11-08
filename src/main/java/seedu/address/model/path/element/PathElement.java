package seedu.address.model.path.element;

import java.util.Objects;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.element.exceptions.InvalidPathElementException;

/**
 * Represents an element in a path.
 */
public class PathElement {
    public static final PathElement ELEMENT_CURRENT = new PathElement(".", PathElementType.CURRENT);
    public static final PathElement ELEMENT_ROOT = new PathElement("~", PathElementType.ROOT);
    public static final PathElement ELEMENT_PARENT = new PathElement("..", PathElementType.PARENT);

    private String elementStr;
    private PathElementType type;

    /**
    * Constructs a {@code PathElement} with the element string and type.
    *
    * @param elementStr The element string.
    * @param type The type of the element.
    */
    private PathElement(String elementStr, PathElementType type) {
        this.elementStr = elementStr.toLowerCase();
        this.type = type;
    }

    /**
     * Parses a path element from a string and returns a {@code PathElement} object.
     *
     * @param element The string representation of the path element.
     * @return A PathElement object representing the parsed path element.
     * @throws InvalidPathElementException If the path element is invalid.
     */
    public static PathElement parsePathElement(String element) throws InvalidPathElementException {
        if (element.equals("~")) {
            return ELEMENT_ROOT;
        } else if (element.equals("..")) {
            return ELEMENT_PARENT;
        } else if (element.equals(".")) {
            return ELEMENT_CURRENT;
        } else if (StudentId.isValidStudentId(element)) {
            return new PathElement(element, PathElementType.STUDENTID);
        } else if (GroupId.isValidGroupId(element)) {
            return new PathElement(element, PathElementType.GROUPID);
        } else {
            throw new InvalidPathElementException();
        }
    }

    /**
     * Retrieves the type of the path element.
     */
    public PathElementType getType() {
        return this.type;
    }

    /**
     * Calculates the priority difference between two path elements.
     *
     * @param other The other path element to compare with.
     */
    public int getPriorityDiff(PathElement other) {
        return this.type.getPriority() - other.type.getPriority();
    }

    @Override
    public String toString() {
        return this.elementStr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PathElement)) {
            return false;
        }

        PathElement other = (PathElement) obj;

        return elementStr.equals(other.elementStr) && type == other.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementStr, type);
    }
}

