package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.pathelement.PathElement;
import seedu.address.model.pathelement.PathElementType;
import seedu.address.model.pathelement.exceptions.InvalidPathElementException;

public class Path {
    protected final List<PathElement> pathElements;
    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    public Path() throws InvalidPathException {
        this.pathElements = new ArrayList<>();
    }

    public Path(List<PathElement> pathElements) {
        this.pathElements = pathElements;
    }

    protected void commonConstructor(String path) throws InvalidPathException {
        String[] elementStrs = path.split("/");
        List<PathElement> elements = new ArrayList<>();

        for (String elementStr : elementStrs) {
            try {
                PathElement element = PathElement.parsePathElement(elementStr);
                elements.add(element);
            } catch (InvalidPathElementException e) {
                throw new InvalidPathException("Encountered invalid path element.");
            }
        }

        appendPathElements(this.pathElements, elements);
    }

    protected static void appendPathElements(List<PathElement> destination, List<PathElement> source) 
            throws InvalidPathException{
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

    @Override
    public String toString() {
        List<String> pathElementStrings = pathElements.stream()
                .map(PathElement::toString)
                .collect(Collectors.toList());

        return String.join("/", pathElementStrings);
    }
}
