package seedu.address.model.profbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.taskmanager.TaskList;

/**
 * Encapsulates the logic of a ProfBookModel that contains children
 * As of v1.2 it is only root and group class
 * @param <T> to represent the children type, as of v1.2 only student and group
 */
public class ChildrenManager<T extends IChildElement> extends TaskListManager {
    /**
     * Maps the id to the children
     */
    private final Map<Id, T> children;

    /**
     * Constructs a new child manager instance
     * @param taskList - For ProfBookModel constructor
     * @param children - Map of id to their children
     */
    public ChildrenManager(TaskList taskList, Map<Id, T> children) {
        super(taskList);
        this.children = children;
    }

    /**
     * Adds the child to list of children
     * @param id - Unique identifier of the child
     * @param child - The child in question
     * @throws DuplicateChildException If attempting to add child with the same ID
     */
    public void addChild(Id id, T child) throws DuplicateChildException {
        T currChild = this.children.get(id);
        if (currChild != null) {
            throw new DuplicateChildException(id.toString());
        }
        this.children.put(id, child);
    }

    /**
     * Deletes the child specified by the id
     * @param id - Unique identifier of the child
     * @return The deleted Child
     * @throws NoSuchChildException If there is no such Child found
     */
    public T deleteChild(Id id) throws NoSuchChildException {
        T child = this.getChild(id);
        this.children.remove(id);
        return child;
    }

    /**
     * Returns the child specified by the id
     * @param id - Unique identifier of the child
     * @return The specified Child
     * @throws NoSuchChildException If there is no such Child found
     */
    public T getChild(Id id) throws NoSuchChildException {
        T child = this.children.get(id);
        if (child == null) {
            throw new NoSuchChildException(id.toString());
        }
        return child;
    }

    /**
     * Returns a list of all current children
     * @return list of all current children
     */
    public List<T> getAllChildren() {
        return new ArrayList<>(this.children.values());
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Id, T> entry : this.children.entrySet()) {
            ret.append(entry.getKey().toString()).append(": ").append(entry.getValue().toString()).append("\n");
        }
        return ret.toString();
    }
}
