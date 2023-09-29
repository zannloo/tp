package seedu.address.model.profbook;
import java.util.Map;

import seedu.address.model.id.Id;
import seedu.address.model.taskmanager.TaskList;

/**
 * Encapsulates logic for the whole application data
 */
public class Root extends ChildrenManager<Group> {

    /**
     * Constructs a new prof book instance
     *
     * @param taskList - The Task list associated with this object
     * @param children - The Groups under the root
     */
    public Root(TaskList taskList, Map<Id, Group> children) {
        super(taskList, children);
    }
}
