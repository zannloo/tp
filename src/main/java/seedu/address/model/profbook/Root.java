package seedu.address.model.profbook;
import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.taskmanager.TaskList;

/**
 * Encapsulates logic for the whole application data
 */
public class Root extends ChildrenManager<Group> {

    /**
     * Constructs a profbook instance with task list and children.
     *
     * @param taskList - The Task list associated with this object
     * @param children - The Groups under the root
     */
    public Root(TaskList taskList, Map<Id, Group> children) {
        super(taskList, children);
    }

    /**
     * Constructs a new prof book instance.
     */
    public Root() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", "Root")
                .add("Students", super.toString())
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Root)) {
            return false;
        }

        Root otherRoot = (Root) other;
        return super.equals(otherRoot);
    }
}
