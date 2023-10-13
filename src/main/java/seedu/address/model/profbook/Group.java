package seedu.address.model.profbook;

import java.util.ArrayList;
import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * Encapsulates logic for a group within a tutorial group
 */
public class Group extends ChildrenManager<Student> implements IChildElement {

    /**
     * Name of the group
     */
    private final Name name;

    /**
     * Unique identifier of the group
     */
    private final Id id;

    /**
     * Constructs a new Group instance
     *
     * @param taskList - The task list associated with this group
     * @param students - The list of students in this group
     * @param name     - The group name
     * @param id       - Unique identifier of the group
     */
    public Group(TaskList taskList, Map<Id, Student> students, Name name, Id id) {
        super(taskList, students);
        this.name = name;
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    /**
     * Creates a clone of the current element, this is to achieve immutability
     *
     * @return The clone of the IChildElement
     */
    @Override
    public Group clone() {
        try {
            return new Group(new TaskList(this.getAllTask()), this.getChildren(),
                    new Name(this.name.fullName), this.id);
        } catch (NoSuchTaskException e) {
            return new Group(new TaskList(new ArrayList<>()), this.getChildren(),
                    new Name(this.name.fullName), this.id);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Group Id", id)
                .add("name", name)
                .add("Students", super.toString())
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return super.equals(otherGroup)
                && this.name.equals(otherGroup.name)
                && this.id.equals(otherGroup.id);
    }

}

