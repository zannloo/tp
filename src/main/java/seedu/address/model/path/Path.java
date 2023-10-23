package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.path.element.PathElement;
import seedu.address.model.path.element.PathElementType;
import seedu.address.model.path.element.exceptions.InvalidPathElementException;
import seedu.address.model.path.exceptions.InvalidPathException;
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
            path = path.substring(0, path.length() - 1);
        }

        String[] elementStrs = path.split("/");

        if (elementStrs.length == 0) {
            throw new InvalidPathException("Path cannot be empty.");
        }

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
        if (destination.size() == 0) {
            try {
                destination.add(PathElement.parsePathElement("."));
            } catch (InvalidPathElementException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        logger.info(destination.toString());
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
