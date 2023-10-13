package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.element.PathElement;
import seedu.address.model.path.element.PathElementType;
import seedu.address.model.path.element.exceptions.InvalidPathElementException;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
/**
 * Represents a path in our application.
 */
public abstract class Path {
    private static final Logger logger = LogsCenter.getLogger(Path.class);
    protected final List<PathElement> pathElements;

    /**
     * Constructs an empty {@code Path}.
     */
    public Path() {
        this.pathElements = new ArrayList<>();
    }

    /**
     * Constructs a {@code Path} from a list of path elements.
     *
     * @param pathElements The list of path elements.
     */
    public Path(List<PathElement> pathElements) {
        this.pathElements = pathElements;
    }

    /**
     * Common constructor logic for initializing a {@code Path} from a string representation.
     *
     * @param path The string representation of the path.
     * @throws InvalidPathException If an invalid path element is encountered.
     */
    protected void commonConstructor(String path) throws InvalidPathException {
        if (path.startsWith("/")) {
            path = path.substring(1, path.length());
        }

        if (path.endsWith("/")) {
            path = path.substring(0, path.length()-1);
        }

        String[] elementStrs = path.split("/");
        List<PathElement> elements = new ArrayList<>();

        for (String elementStr : elementStrs) {
            try {
                PathElement element = PathElement.parsePathElement(elementStr);
                elements.add(element);
            } catch (InvalidPathElementException e) {
                throw new InvalidPathException("Encountered invalid path element: " + elementStr);
            }
        }

        appendPathElements(this.pathElements, elements);
    }

    /**
     * Appends a list of path elements to an existing list of path elements.
     * Handles path element traversal, validation, and structure checks.
     *
     * @param destination The list to which path elements will be appended.
     * @param source The list of path elements to append.
     * @throws InvalidPathException If an invalid path element is encountered or if the path structure is invalid.
     */
    protected static void appendPathElements(List<PathElement> destination, List<PathElement> source)
            throws InvalidPathException {
        for (PathElement element : source) {

            if (element.getType() == PathElementType.CURRENT) {
                continue;
            }

            if (element.getType() == PathElementType.PARENT && !destination.isEmpty()) {
                PathElement prevElement = destination.get(destination.size() - 1);

                if (prevElement.getType() == PathElementType.ROOT) {
                    throw new InvalidPathException("Unable to navigate above home directory");
                }
                if (prevElement.getType() != PathElementType.PARENT) {
                    destination.remove(destination.size() - 1);
                    continue;
                }

            } else if (!destination.isEmpty()) {
                PathElement prevElement = destination.get(destination.size() - 1);

                int priorityDiff = element.getPriorityDiff(prevElement);

                // Make sure curr element is has 1 level lower
                if (priorityDiff != -1 && prevElement.getType() != PathElementType.PARENT) {
                    throw new InvalidPathException("Invalid path structure.");
                }
            }
            destination.add(element);
        }
        logger.info(destination.toString());
    }

    /**
     * Checks whether the path represents a group directory.
     *
     * @return {@code true} if the path is a group directory; {@code false} otherwise.
     */
    public boolean isGroupDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.GROUPID;
    }

    /**
     * Checks whether the path represents a student directory.
     *
     * @return {@code true} if the path is a student directory; {@code false} otherwise.
     */
    public boolean isStudentDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.STUDENTID;
    }

    /**
     * Checks whether the path represents a root directory.
     *
     * @return {@code true} if the path is a root directory; {@code false} otherwise.
     */
    public boolean isRootDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.ROOT;
    }

    /**
     * Retrieves the student ID from the path directory.
     *
     * @return The student ID.
     * @throws UnsupportedPathOperationException If the operation is not supported based on the directory's state.
     * @throws InvalidIdException If the retrieved ID is invalid.
     */
    public Optional<StudentId> getStudentId() {
        if (this.isGroupDirectory() || this.isRootDirectory()) {
            return Optional.empty();
        }

        for (int i = 0; i < this.pathElements.size(); i++) {
            PathElement currElement = this.pathElements.get(i);
            if (currElement.getType() == PathElementType.STUDENTID) {
                return Optional.of(new StudentId(currElement.toString()));
            }
        }

        return Optional.empty();
    }

    /**
     * Retrieves the group ID from the path directory.
     *
     * @return The group ID.
     * @throws UnsupportedPathOperationException If the operation is not supported based on the directory's state.
     * @throws InvalidIdException If the retrieved ID is invalid.
     */
    public Optional<GroupId> getGroupId() {
        if (this.isRootDirectory()) {
            return Optional.empty();
        }

        for (int i = 0; i < this.pathElements.size(); i++) {
            PathElement currElement = this.pathElements.get(i);
            if (currElement.getType() == PathElementType.GROUPID) {
                return Optional.of(new GroupId(currElement.toString()));
            }
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        List<String> pathElementStrings = pathElements.stream()
                .map(PathElement::toString)
                .collect(Collectors.toList());

        return String.join("/", pathElementStrings);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Path)) {
            return false;
        }

        Path other = (Path) obj;

        return this.pathElements.equals(other.pathElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathElements);
    }
}
