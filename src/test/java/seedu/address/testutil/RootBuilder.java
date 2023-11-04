package seedu.address.testutil;

import java.util.List;

import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;

/**
 * A utility class to help with building Root objects.
 */
public class RootBuilder {
    private Root root;

    /**
     * Constructs a RootBuilder to build a `Root` object with a collection of groups.
     */
    public RootBuilder() {
        root = new Root();
    }

    /**
     * Adds a new {@code group} to the {@code Root} that we are building.
     */
    public RootBuilder withGroup(Group group) {
        root.addChild(group.getId(), group);
        return this;
    }

    /**
     * Adds a list of {@code groups} to the {@code Root} that we are building.
     */
    public RootBuilder withGroups(List<Group> groups) {
        for (Group group : groups) {
            root.addChild(group.getId(), group);
        }
        return this;
    }

    public Root build() {
        return root;
    }

}
