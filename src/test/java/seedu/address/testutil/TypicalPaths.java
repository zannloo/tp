package seedu.address.testutil;

import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import seedu.address.model.path.AbsolutePath;

/**
 * A utility class containing a list of {@code Path} objects to be used in tests.
 */
public class TypicalPaths {
    public static final AbsolutePath PATH_TO_GROUP_ONE = new PathBuilder()
            .withGroup(GROUP_ONE).build();
    public static final AbsolutePath PATH_TO_GROUP_TWO = new PathBuilder()
            .withGroup(GROUP_TWO).build();
    public static final AbsolutePath PATH_TO_ALICE = new PathBuilder()
            .withGroup(GROUP_ONE).withStudent(ALICE).build();
    public static final AbsolutePath PATH_TO_FIONA = new PathBuilder()
            .withGroup(GROUP_TWO).withStudent(FIONA).build();

    private TypicalPaths() {} // prevents instantiation

}
