package seedu.address.model.profbook;

import seedu.address.model.id.Id;
import seedu.address.ui.Displayable;

/**
 * Encapsulate required logic for child element of ProfBok Model
 */
public interface IChildElement<T> extends Displayable {

    /**
     * Creates a clone of the current element, this is to achieve immutability.
     *
     * @return The clone of the IChildElement
     */
    T deepCopy();

    /**
     * Returns the unique id.
     */
    Id getId();
}
