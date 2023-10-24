package seedu.address.testutil;

import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class to help with building Root objects.
 */
public class RootBuilder {
    private Map<Id, Group> groups;

    public RootBuilder(){
        groups = new HashMap<>();

    }

    /**
     * Adds sample group to the Root being built.
     * @return A root instance for testing
     */
    public Root withGroup() {
        Group group = new GroupBuilder().build();
        Group noTasks = new  GroupBuilder().buildEmptyTask();
        Group emptyStudent = new GroupBuilder().buildWithEmptyStudent();
        groups.put(group.getId(), group);
        groups.put(noTasks.getId(), group);
        groups.put(emptyStudent.getId(), group);
        return new Root(groups);
    }

}
