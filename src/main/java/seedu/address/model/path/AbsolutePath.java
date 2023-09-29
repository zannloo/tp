package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.element.PathElement;
import seedu.address.model.path.element.PathElementType;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;

/**
 * Absolute Path for storing group and student id.
 */
public class AbsolutePath extends Path {
    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    /**
     * Construct {@code AbsolutePath} from a path string.
     * @param path The path string.
     * @throws InvalidPathException if the given path string is invalid.
     */
    public AbsolutePath(String path) throws InvalidPathException {
        super();

        if (!path.startsWith("~")) {
            throw new InvalidPathException("Absolute path should start with ~");
        }

        commonConstructor(path);
        logger.info(this.toString());
    }

    /**
     * Construct {@code AbsolutePath} with path element list.
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
     * Retrieves the student ID from the path directory.
     *
     * @return The student ID.
     * @throws UnsupportedPathOperationException If the operation is not supported based on the directory's state.
     * @throws InvalidIdException If the retrieved ID is invalid.
     */
    public StudentId getStudentId() throws UnsupportedPathOperationException, InvalidIdException {
        if (this.isGroupDirectory()) {
            throw new UnsupportedPathOperationException("Can't find student ID in the group directory");
        } else if (this.isRootDirectory()) {
            throw new UnsupportedPathOperationException("Can't find student ID in the root directory");
        }

        StudentId id = StudentId.createStudentId(this.pathElements.get(2).toString());

        return id;
    }

    /**
     * Retrieves the group ID from the path directory.
     *
     * @return The group ID.
     * @throws UnsupportedPathOperationException If the operation is not supported based on the directory's state.
     * @throws InvalidIdException If the retrieved ID is invalid.
     */
    public GroupId getGroupId() throws UnsupportedPathOperationException, InvalidIdException {
        if (this.isRootDirectory()) {
            throw new UnsupportedPathOperationException("Can't find group ID in the root directory");
        }

        GroupId id = GroupId.createGroupId(this.pathElements.get(1).toString());

        return id;
    }
}
