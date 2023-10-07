package seedu.address.logic.newcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.taskmanager.ToDo;

class CreateToDoForStudentCommandTest {

    //      to be implemented
    //    @Test
    //    void execute() {
    //    }
    @Test
    void equals_sameInstance_success() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        ToDo toDo = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command = new CreateToDoForStudentCommand(path, toDo);
        assertTrue(command.equals(command));
    }

    @Test
    void equals_sameCommand_success() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        ToDo toDo1 = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command1 = new CreateToDoForStudentCommand(path1, toDo1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        ToDo toDo2 = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command2 = new CreateToDoForStudentCommand(path2, toDo2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-001");
        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_differentDescription_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        ToDo toDo1 = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command1 = new CreateToDoForStudentCommand(path1, toDo1);

        RelativePath path2 = new RelativePath("~/grp-001/stu-001");
        ToDo toDo2 = new ToDo("Assignment 2");
        CreateToDoForStudentCommand command2 = new CreateToDoForStudentCommand(path2, toDo2);
        assertFalse(command1.equals(command2));
    }

    @Test
    void equals_differentPath_fail() throws InvalidPathException {
        RelativePath path1 = new RelativePath("~/grp-001/stu-001");
        ToDo toDo1 = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command1 = new CreateToDoForStudentCommand(path1, toDo1);
        command1.absolutePath = new AbsolutePath("~/grp-001/stu-001");

        RelativePath path2 = new RelativePath("~/grp-001/stu-002");
        ToDo toDo2 = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command2 = new CreateToDoForStudentCommand(path2, toDo2);
        command2.absolutePath = new AbsolutePath("~/grp-001/stu-002");

        assertFalse(command1.equals(command2));
    }

    @Test
    void toString_sameString_true() throws InvalidPathException {
        RelativePath path = new RelativePath("~/grp-001/stu-001");
        ToDo deadline = new ToDo("Assignment 1");
        CreateToDoForStudentCommand command = new CreateToDoForStudentCommand(path, deadline);
        String expected = CreateToDoForStudentCommand.class.getCanonicalName()
                + "{toCreateToDoForStudent=" + command.stu + "}";
        assertEquals(expected, command.toString());
    }
}
