package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.taskmanager.Deadline;

class CreateDeadlineCommandTest {
    //    to be implemented
    //    @Test
    //    void execute() {
    //    }

    @Test
    void equals_sameInstanceForStudentDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);

        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameInstanceForGroupDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);

        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameCommandForStudentDirectory_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_sameCommandForGroupDirectory_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001");

        RelativePath path2 = new RelativePath("~/grp-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001");

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_differentDescriptionForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentPathForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-002");
        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentDuedateForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentFieldsForStudentDirectory_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineCommand command1 = new CreateDeadlineCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineCommand command2 = new CreateDeadlineCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void toString_sameStringForStudentDirectory_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineCommand command = new CreateDeadlineCommand(path, deadline);
        String expected = CreateDeadlineCommand.class.getCanonicalName()
                + "{toCreateDeadlineForStudent=" + command.stu + "}";

        assertEquals(expected, command.toString());
    }
}
