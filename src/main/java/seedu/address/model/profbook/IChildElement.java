package seedu.address.model.profbook;

/**
 * Encapsulate required logic for child element of ProfBok Model
 */
public interface IChildElement {

    /**
     * Creates a clone of the current element, this is to achieve immutability
     *
     * @return The clone of the IChildElement
     */
    IChildElement clone();
}
