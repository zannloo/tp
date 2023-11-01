package seedu.address.model.profbook;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.exceptions.DuplicateChildException;
import seedu.address.model.profbook.exceptions.NoSuchChildException;

/**
 * Encapsulates the logic of a ProfBookModel that contains children
 * As of v1.2 it is only root and group class
 *
 * @param <T> to represent the children type, as of v1.2 only student and group
 */
public class ChildrenManager<T extends IChildElement<T>> implements IChildrenManager<T> {
    /**
     * Maps the id to the children
     */
    private final Map<Id, T> children;

    /**
     * Construct a children manager with given task list and children map.
     */
    public ChildrenManager(Map<Id, T> children) {
        requireAllNonNull(children);
        Map<Id, T> tempMap = new HashMap<>();
        Id key;
        Iterator<Id> it = children.keySet().iterator();
        while (it.hasNext()) {
            key = it.next();
            tempMap.put(key, children.get(key).deepCopy());
        }
        this.children = tempMap;
    }

    /**
     * Construct a new children manager.
     */
    public ChildrenManager() {
        children = new HashMap<>();
    }

    /**
     * Constructs a {@code ChildrenManager} with the data in {@code toBeCopied}.
     * @param toBeCopied
     */
    public ChildrenManager(ChildrenManager<T> toBeCopied) {
        this(toBeCopied.children);
    }

    @Override
    public void addChild(Id id, T child) throws DuplicateChildException {
        T currChild = this.children.get(id);
        if (currChild != null) {
            throw new DuplicateChildException(id.toString());
        }
        this.children.put(id, child);
    }

    @Override
    public T deleteChild(Id id) throws NoSuchChildException {
        T child = this.getChild(id);
        this.children.remove(id);
        return child;
    }

    @Override
    public boolean hasChild(Id id) {
        return this.children.containsKey(id);
    }

    @Override
    public T getChild(Id id) throws NoSuchChildException {
        T child = this.children.get(id);
        if (child == null) {
            throw new NoSuchChildException(id.toString());
        }
        return child;
    }

    @Override
    public int numOfChildren() {
        return this.children.size();
    }

    @Override
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

    public Map<Id, T> getChildren() {
        return new HashMap<>(this.children);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChildrenManager<?>)) {
            return false;
        }

        ChildrenManager<?> otherChildrenManger = (ChildrenManager<?>) other;
        return this.children.equals(otherChildrenManger.children);
    }
}
