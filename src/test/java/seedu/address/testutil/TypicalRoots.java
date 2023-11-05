package seedu.address.testutil;

import seedu.address.model.profbook.Root;

/**
 * A utility class containing {@code Root} objects to be used in tests.
 */
public class TypicalRoots {
    public static final Root PROFBOOK_WITH_TWO_GROUPS = new RootBuilder()
            .withGroup(TypicalGroups.GROUP_ONE).withGroup(TypicalGroups.GROUP_TWO).build();
    public static final Root PROFBOOK_WITH_GROUP_ONE = new RootBuilder()
            .withGroup(TypicalGroups.GROUP_ONE).build();

    private TypicalRoots() {} // prevents instantiation
}
