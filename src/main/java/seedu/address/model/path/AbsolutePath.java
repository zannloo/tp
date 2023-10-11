package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;

import seedu.address.model.path.element.PathElement;
import seedu.address.model.path.element.PathElementType;
import seedu.address.model.path.exceptions.InvalidPathException;

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

        // If relativePath start with ~/ return relativePath
        if (relativePathElements.get(0).getType() == PathElementType.ROOT) {
            return new AbsolutePath(relativePathElements);
        }

        Path.appendPathElements(fullPathElements, relativePathElements);

        return new AbsolutePath(fullPathElements);
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
