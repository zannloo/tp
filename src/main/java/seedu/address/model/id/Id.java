package seedu.address.model.id;

/**
 * Represents an identifier (ID) with a string value.
 */
public abstract class Id {
    protected final String id;

    /**
     * Constructs an {@code Id} object with the specified ID value.
     *
     * @param id The ID value to store.
     */
    public Id(String id) {
        this.id = id.toUpperCase();
    }

    @Override
    public String toString() {
        return this.id;
    }
}
