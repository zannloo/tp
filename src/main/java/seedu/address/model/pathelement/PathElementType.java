package seedu.address.model.pathelement;

/**
 * The {@code PathElementType} enum represents the types of elements that can appear in a path.
 */
public enum PathElementType {
    STUDENTID(0), GROUPID(1), ROOT(2), PARENT(-1), CURRENT(-2);

    private final int priority;

    PathElementType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }
}
