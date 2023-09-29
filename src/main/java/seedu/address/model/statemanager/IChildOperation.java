package seedu.address.model.statemanager;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;

/**
 * Interface for classes that operations that involve children, ensures that all
 * basic functions are present to interact
 * with ChildManager instance.
 *
 * @param <T> Type of the child
 */
public interface IChildOperation<T extends IChildElement> {
    /**
     * Adds the child to list of children
     * 
     * @param id    - Unique identifier of the child
     * @param child - The child in question
     * @throws DuplicateChildException If attempting to add child with the same ID
     */
    void addChild(Id id, T child) throws DuplicateChildException;

    /**
     * Deletes the child specified by the id
     * 
     * @param id - Unique identifier of the child
     * @return The deleted Child
     * @throws NoSuchChildException If there is no such Child found
     */
    T deleteChild(Id id) throws NoSuchChildException;

    /**
     * Returns the child specified by the id
     * 
     * @param id - Unique identifier of the child
     * @return The specified Child
     * @throws NoSuchChildException If there is no such Child found
     */
    T getChild(Id id) throws NoSuchChildException;

    /**
     * Returns a list of all current children
     * 
     * @return list of all current children
     */
    T[] getAllChildren();
}
