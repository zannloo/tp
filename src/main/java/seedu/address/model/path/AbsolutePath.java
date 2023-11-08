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
 * Represents absolute path in ProfBook.
 */
public class AbsolutePath extends Path {
    public static final String ROOT_ELEMENT = "~";
    public static final String MESSAGE_NO_ROOT_ELEMENT = "Absolute path must start with ~";
    public static final String MESSAGE_STUDENT_ID_NOT_FOUND = "Student Id element not found in path.";
    public static final String MESSAGE_GROUP_ID_NOT_FOUND = "Group Id element not found in path.";
    public static final AbsolutePath ROOT_PATH;
    private static final Logger logger = LogsCenter.getLogger(AbsolutePath.class);

    static {
        try {
            ROOT_PATH = new AbsolutePath(ROOT_ELEMENT);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Constructs {@code AbsolutePath} from a {@code String} path.
     *
     * @param path The string path.
     * @throws InvalidPathException if the given path string is invalid.
     */
    public AbsolutePath(String path) throws InvalidPathException {
        super();

        if (!path.startsWith(ROOT_ELEMENT)) {
            throw new InvalidPathException(MESSAGE_NO_ROOT_ELEMENT);
        }

        commonConstructor(path);

        logger.finest("Created new absolute path object: " + this);
    }

    /**
     * Constructs {@code AbsolutePath} with path element list.
     *
     * @param fullPathElements The list of elements for the full path.
     */
    private AbsolutePath(List<PathElement> fullPathElements) {
        super(fullPathElements);
    }

    /**
     * Resolves a {@code RelativePath} against this {@code AbsolutePath}, and returns the resolved path.
     *
     * @param relative The relative path to resolve against this absolute path.
     * @throws InvalidPathException If the resolution path.
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
     * Returns {@code true} if the path is a group directory.
     */
    public boolean isGroupDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.GROUPID;
    }

    /**
     * Returns {@code true} if the path is a student directory.
     */
    public boolean isStudentDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.STUDENTID;
    }

    /**
     * Returns {@code true} if the path is a root directory.
     */
    public boolean isRootDirectory() {
        PathElement lastElement = this.pathElements.get(this.pathElements.size() - 1);
        return lastElement.getType() == PathElementType.ROOT;
    }

    /**
     * Retrieves the {@code StudentId} in the path.
     *
     * @return An {@code Optional} containing the {@code StudentId} if it exists in the path,
     *         or an empty {@code Optional}  if the path is a root or group directory.
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
                String.format(MESSAGE_INTERNAL_ERROR, MESSAGE_STUDENT_ID_NOT_FOUND));
    }

    /**
     * Retrieves the {@code GroupId} associated with this path.
     *
     * @return An {@code Optional} containing the {@code GroupId} if it exists in the path,
     *         or an empty {@code Optional} if the path is a root directory.
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
                String.format(MESSAGE_INTERNAL_ERROR, MESSAGE_GROUP_ID_NOT_FOUND));
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
