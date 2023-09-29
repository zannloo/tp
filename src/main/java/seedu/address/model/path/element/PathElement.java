package seedu.address.model.path.element;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.element.exceptions.InvalidPathElementException;

/**
 * Represent an element in a path.
 */
public class PathElement {
    private String elementStr;
    private PathElementType type;

    /**
    * Constructs a {@code PathElement} with the element string and type.
    *
    * @param elementStr The element string.
    * @param type The type of the element.
    */
    public PathElement(String elementStr, PathElementType type) {
        this.elementStr = elementStr;
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
            return new PathElement(element, PathElementType.ROOT);
        } else if (element.equals("..")) {
            return new PathElement(element, PathElementType.PARENT);
        } else if (element.equals(".")) {
            return new PathElement(element, PathElementType.CURRENT);
        } else if (StudentId.STUDENT_ID_VALIDATOR.isValid(element)) {
            return new PathElement(element, PathElementType.STUDENTID);
        } else if (GroupId.GROUP_ID_VALIDATOR.isValid(element)) {
            return new PathElement(element, PathElementType.GROUPID);
        } else {
            throw new InvalidPathElementException();
        }
    }

    /**
     * Gets the type of the path element.
     *
     * @return The type of the path element.
     */
    public PathElementType getType() {
        return this.type;
    }

    /**
     * Calculates the priority difference between two path elements.
     *
     * @param other The other path element to compare with.
     * @return The priority difference.
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PathElement otherId = (PathElement) obj;
        return elementStr.equals(otherId.elementStr) && type == otherId.type;
    }

    @Override
    public int hashCode() {
        return elementStr.hashCode() * 31 + type.hashCode();
    }
}

