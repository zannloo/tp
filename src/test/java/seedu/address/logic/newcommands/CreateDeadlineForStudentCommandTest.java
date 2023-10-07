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

class CreateDeadlineForStudentCommandTest {
    //    to be implemented
    //    @Test
    //    void execute() {
    //    }

    @Test
    void equals_sameInstance_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineForStudentCommand command = new CreateDeadlineForStudentCommand(path, deadline);

        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameCommand_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineForStudentCommand command1 = new CreateDeadlineForStudentCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineForStudentCommand command2 = new CreateDeadlineForStudentCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_differentDescription_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineForStudentCommand command1 = new CreateDeadlineForStudentCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineForStudentCommand command2 = new CreateDeadlineForStudentCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentPath_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineForStudentCommand command1 = new CreateDeadlineForStudentCommand(path1, deadline1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineForStudentCommand command2 = new CreateDeadlineForStudentCommand(path2, deadline2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-002");
        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentDuedate_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineForStudentCommand command1 = new CreateDeadlineForStudentCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 1", duedate2);
        CreateDeadlineForStudentCommand command2 = new CreateDeadlineForStudentCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentFields_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate1 = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline1 = new Deadline("Assignment 1", duedate1);
        CreateDeadlineForStudentCommand command1 = new CreateDeadlineForStudentCommand(path1, deadline1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        LocalDateTime duedate2 = LocalDateTime.parse("2023-12-03T23:59");
        Deadline deadline2 = new Deadline("Assignment 2", duedate2);
        CreateDeadlineForStudentCommand command2 = new CreateDeadlineForStudentCommand(path2, deadline2);

        assertFalse(command1.equals(command2));
    }

    @Test
    void toString_sameString_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        LocalDateTime duedate = LocalDateTime.parse("2023-12-03T23:58");
        Deadline deadline = new Deadline("Assignment 1", duedate);
        CreateDeadlineForStudentCommand command = new CreateDeadlineForStudentCommand(path, deadline);
        String expected = CreateDeadlineForStudentCommand.class.getCanonicalName()
                + "{toCreateDeadlineForStudent=" + command.stu + "}";

        assertEquals(expected, command.toString());
    }
}
