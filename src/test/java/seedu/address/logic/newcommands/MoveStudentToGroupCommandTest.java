package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;

public class MoveStudentToGroupCommandTest {

    @Test
    public void constructor_nullSourcePathAndDestinationPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoveStudentToGroupCommand(null, null));
    }

    @Test
    public void equals() throws InvalidPathException {
        RelativePath sourcePath = new RelativePath("~/grp-001/stu-001");
        RelativePath destinationPath = new RelativePath("~/grp-002");
        MoveStudentToGroupCommand command1 = new MoveStudentToGroupCommand(sourcePath, destinationPath);
        MoveStudentToGroupCommand command2 = new MoveStudentToGroupCommand(sourcePath, destinationPath);
        assertEquals(command1, command2);
    }

    @Test
    public void testOutputString() throws InvalidPathException {
        RelativePath relativeSourcePath = new RelativePath("~/grp-001/stu-001");
        RelativePath relativeDestinationPath = new RelativePath("~/grp-002");
        MoveStudentToGroupCommand moveStudentToGroupCommand =
                new MoveStudentToGroupCommand(relativeSourcePath, relativeDestinationPath);
        String expected =
                "seedu.address.logic.newcommands.MoveStudentToGroupCommand{toMoveThisStudentToAnotherGroup=null}";
        assertEquals(expected, moveStudentToGroupCommand.toString());
    }
}
