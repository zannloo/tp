package seedu.address.model.id;

import java.util.Objects;

/**
 * Represents an identifier (ID) with a string value.
 */
public class Id {
    private final String id;

    /**
     * Constructs an {@code Id} object with the specified ID value.
     *
     * @param id The ID value to store.
     */
    public Id(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Id)) {
            return false;
        }
        Id id1 = (Id) o;
        return id.equals(id1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
