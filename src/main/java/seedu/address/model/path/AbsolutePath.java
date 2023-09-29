package seedu.address.model.path;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
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
}
