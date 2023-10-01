package seedu.address.model.profbook;

import seedu.address.model.id.Id;

/**
 * Encapsulate required logic for child element of ProfBok Model
 */
public interface IChildElement {

    /**
     * Returns true if child element has same id as param id
     *
     * @param id - Id to compare to
     * @return true if it is equals using ID's equals method
     */
    boolean isSameAs(Id id);

    /**
     * Creates a clone of the current element, this is to achieve immutability
     *
     * @return The clone of the IChildElement
     */
    IChildElement clone();
}
