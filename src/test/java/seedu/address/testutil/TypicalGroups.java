package seedu.address.testutil;

import seedu.address.model.profbook.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {
    public static final Group GROUP_ONE = new GroupBuilder()
            .withId("grp-001").withName("Group One").withStudents(TypicalStudents.STUDENT_LIST_1).build();
    public static final Group GROUP_TWO = new GroupBuilder()
            .withId("grp-002").withName("Group Two").withStudents(TypicalStudents.STUDENT_LIST_2).build();
    public static final Group EMPTY_GROUP = new GroupBuilder()
            .withId("grp-000").withName("Empty Group").build();
    private TypicalGroups() {} // prevents instantiation
}
