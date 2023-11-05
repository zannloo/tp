package seedu.address.model.path;

import static seedu.address.logic.Messages.MESSAGE_INTERNAL_ERROR;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.element.PathElement;
import seedu.address.model.path.element.PathElementType;
import seedu.address.model.path.exceptions.InvalidPathException;

/**
 * Absolute Path for storing group and student id.
 */
public class AbsolutePath extends Path {
    public static final AbsolutePath ROOT_PATH;
    private static final Logger logger = LogsCenter.getLogger(AbsolutePath.class);

    static {
        try {
            ROOT_PATH = new AbsolutePath("~");
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Construct {@code AbsolutePath} from a path string.
     *
     * @param path The path string.
     * @throws InvalidPathException if the given path string is invalid.
     */
    public AbsolutePath(String path) throws InvalidPathException {
        super();

        if (!path.startsWith("~")) {
            throw new InvalidPathException("Absolute path should start with ~/");
        }

        commonConstructor(path);
        logger.info(this.toString());
    }

    /**
     * Construct {@code AbsolutePath} with path element list.
     *
     * @param fullPathElements The list of elements for the full path.
     */
    public AbsolutePath(List<PathElement> fullPathElements) {
        super(fullPathElements);
    }

    /**
     * Resolves a relative path against this absolute path, resulting in a new absolute path.
     *
     * @param relative The relative path to resolve against this absolute path.
     * @return A new AbsolutePath representing the resolved path.
     * @throws InvalidPathException If an invalid path element is encountered during resolution.
     */
    public AbsolutePath resolve(RelativePath relative) throws InvalidPathException {
        List<PathElement> fullPathElements = new ArrayList<>(this.pathElements);
        List<PathElement> relativePathElements = relative.pathElements;

        // If relativePath start from root, should return the relativePath
        if (relativePathElements.get(0).getType() == PathElementType.ROOT) {
            return new AbsolutePath(relativePathElements);
        }

        Path.appendPathElements(fullPathElements, relativePathElements);
        return new AbsolutePath(fullPathElements);
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
     * Retrieves the {@code StudentId} associated with this directory.
     *
     * @return An Optional containing the GroupId if it exists in the directory, or an empty Optional
     *         if the directory is a root or group directory.
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

        throw new IllegalArgumentException(
                String.format(MESSAGE_INTERNAL_ERROR, "Student Id element not found in path."));
    }

    /**
     * Retrieves the {@code GroupId} associated with this directory.
     *
     * @return An Optional containing the StudentId if it exists in the directory, or an empty Optional
     *         if the directory is a root directory.
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

        throw new IllegalArgumentException(
                String.format(MESSAGE_INTERNAL_ERROR, "Group Id element not found in path."));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbsolutePath)) {
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), "ABSOLUTEPATH");
    }
}
