package seedu.address.model.path;

import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.path.exceptions.InvalidPathException;

/**
 * Relative Path for storing group and student id.
 */
public class RelativePath extends Path {
    public static final RelativePath PARENT;
    public static final RelativePath CURRENT;
    private static final Logger logger = LogsCenter.getLogger(RelativePath.class);

    static {
        try {
            PARENT = new RelativePath("..");
            CURRENT = new RelativePath(".");
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Construct {@code RelativePath} from a path string.
     * @param path The path string.
     * @throws InvalidPathException if the given path string is invalid.
     */
    public RelativePath(String path) throws InvalidPathException {
        super();
        commonConstructor(path);
        logger.finest("Created new relative path object: " + this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RelativePath)) {
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), "RELATIVEPATH");
    }
}

