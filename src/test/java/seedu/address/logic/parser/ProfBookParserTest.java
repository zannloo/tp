package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_TWO;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalTasks.DEADLINE_1;
import static seedu.address.testutil.TypicalTasks.TODO_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.ChangeDirectoryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.logic.commands.CreateTodoCommand;
import seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.MoveStudentToGroupCommand;
import seedu.address.logic.commands.ShowChildrenListCommand;
import seedu.address.logic.commands.ShowTaskListCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.TaskListManager;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TestUtil;

public class ProfBookParserTest {
    private final ProfBookParser parser = new ProfBookParser();

    @Test
    public void parseCommand_createStudent() throws ParseException {
        Student aliceWithNoTask = new StudentBuilder(ALICE).withTaskList(new TaskListManager()).build();
        CreateStudentCommand command = (CreateStudentCommand) parser.parseCommand(
                TestUtil.getCreateStudentCommand(PATH_TO_ALICE, ALICE),
                ROOT_PATH);
        assertEquals(new CreateStudentCommand(PATH_TO_ALICE, aliceWithNoTask), command);
    }

    @Test
    public void parseCommand_createGroup() throws ParseException {
        Group groupOneWithNoTaskNoStudent = new GroupBuilder(GROUP_ONE)
            .withTaskList(new TaskListManager()).withNoStudent().build();
        CreateGroupCommand command = (CreateGroupCommand) parser.parseCommand(
                TestUtil.getCreateGroupCommand(PATH_TO_GROUP_ONE, GROUP_ONE),
                ROOT_PATH);
        assertEquals(new CreateGroupCommand(PATH_TO_GROUP_ONE, groupOneWithNoTaskNoStudent), command);
    }

    @Test
    public void parseCommand_createTodo() throws ParseException {
        CreateTodoCommand command = (CreateTodoCommand) parser.parseCommand(
                TestUtil.getCreateTodoCommand(PATH_TO_GROUP_ONE, TODO_1),
                ROOT_PATH);
        assertEquals(new CreateTodoCommand(PATH_TO_GROUP_ONE, TODO_1, Category.NONE), command);
    }

    @Test
    public void parseCommand_createDeadline() throws ParseException {
        CreateDeadlineCommand command = (CreateDeadlineCommand) parser.parseCommand(
                TestUtil.getCreateDeadlineCommand(PATH_TO_GROUP_ONE, DEADLINE_1),
                ROOT_PATH);
        assertEquals(new CreateDeadlineCommand(PATH_TO_GROUP_ONE, DEADLINE_1, Category.NONE), command);
    }

    @Test
    public void parseCommand_moveStudentToGroup() throws ParseException {
        MoveStudentToGroupCommand command = (MoveStudentToGroupCommand) parser.parseCommand(
                MoveStudentToGroupCommand.COMMAND_WORD + " " + PATH_TO_ALICE + " " + PATH_TO_GROUP_TWO,
                ROOT_PATH);
        assertEquals(new MoveStudentToGroupCommand(PATH_TO_ALICE, PATH_TO_GROUP_TWO), command);
    }

    @Test
    public void parseCommand_changeDirectory() throws ParseException {
        ChangeDirectoryCommand command = (ChangeDirectoryCommand) parser.parseCommand(
            ChangeDirectoryCommand.COMMAND_WORD + " " + PATH_TO_GROUP_ONE,
            ROOT_PATH);
        assertEquals(new ChangeDirectoryCommand(PATH_TO_GROUP_ONE), command);
    }

    @Test
    public void parseCommand_showTaskList() throws ParseException {
        ShowTaskListCommand command = (ShowTaskListCommand) parser.parseCommand(
            ShowTaskListCommand.COMMAND_WORD + " " + PATH_TO_ALICE,
            ROOT_PATH);
        assertEquals(new ShowTaskListCommand(PATH_TO_ALICE), command);
    }

    @Test
    public void parseCommand_showChildrenList() throws ParseException {
        ShowChildrenListCommand command = (ShowChildrenListCommand) parser.parseCommand(
            ShowChildrenListCommand.COMMAND_WORD + " " + PATH_TO_GROUP_ONE,
            ROOT_PATH);
        assertEquals(new ShowChildrenListCommand(PATH_TO_GROUP_ONE), command);
    }

    @Test
    public void parseCommand_deleteStudentsAndGroups() throws ParseException {
        DeleteForStudentsAndGroupsCommand command = (DeleteForStudentsAndGroupsCommand) parser.parseCommand(
            DeleteForStudentsAndGroupsCommand.COMMAND_WORD + " " + PATH_TO_GROUP_ONE,
            ROOT_PATH);
        assertEquals(new DeleteForStudentsAndGroupsCommand(PATH_TO_GROUP_ONE), command);
    }

    @Test
    public void parseCommand_edit() throws ParseException {
        EditCommand command = (EditCommand) parser.parseCommand(
            EditCommand.COMMAND_WORD + " " + PATH_TO_ALICE + " "
                    + OPTION_NAME.getLongName() + " " + BENSON.getName().fullName,
            ROOT_PATH);
        EditStudentDescriptor expectedDescriptor = new EditStudentDescriptor();
        expectedDescriptor.setName(BENSON.getName());
        assertEquals(new EditCommand(PATH_TO_ALICE, expectedDescriptor), command);
    }

    @Test
    public void parseCommand_help() throws ParseException {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, ROOT_PATH) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", ROOT_PATH) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_deleteTask() throws ParseException {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
            DeleteTaskCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased(),
            ROOT_PATH);
        assertEquals(new DeleteTaskCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_markTask() throws ParseException {
        MarkCommand command = (MarkCommand) parser.parseCommand(
            MarkCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased(),
            ROOT_PATH);
        assertEquals(new MarkCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws ParseException {
        UnmarkCommand command = (UnmarkCommand) parser.parseCommand(
            UnmarkCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased(),
            ROOT_PATH);
        assertEquals(new UnmarkCommand(FIRST_INDEX), command);
    }


    @Test
    public void parseCommand_exit() throws ParseException {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, ROOT_PATH) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", ROOT_PATH) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_clear() throws ParseException {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, ROOT_PATH) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", ROOT_PATH) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand("", ROOT_PATH));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand("unknownCommand", ROOT_PATH));
    }
}
