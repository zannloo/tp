package seedu.address.model.path;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.path.exceptions.InvalidPathException;

/**
 * Relative Path for storing group and student id.
 */
public class RelativePath extends Path {
    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    /**
     * Construct {@code RelativePath} from a path string.
     * @param path The path string.
     * @throws InvalidPathException if the given path string is invalid.
     */
    public RelativePath(String path) throws InvalidPathException {
        super();
        commonConstructor(path);
        logger.info(path.toString());
    }
}

