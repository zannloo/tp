package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The HelpCommand class represents a command to display program usage instructions to the user.
 * When executed, it displays a help message with information on how to use the program.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "ChangeDirectoryCommand: cd [targetPath]\n"
            + "CreateStudentCommand: touch [groupId/studentId] -n [name] -p [phone] -e [email] -a [address]\n"
            + "CreateGroupCommand: mkdir [groupId] -n [groupName]\n"
            + "CreateTodoCommand: todo -d [task]\n"
            + "CreateDeadlineCommand: “deadline -d [task] -dt [yyyy/MM/dd hh:mm]\n"
            + "DeleteCommand: rm -type [targetType] -target [targetId]\n"
            + "EditCommand: edit [path]\n"
            + "MarkCommand: mark -d [task] -level [student/group] -target [StudentID/groupId]\n"
            + "UnmarkCommand: unmark -d [task] -level [student/group] -target [StudentID/groupId]\n"
            + "SearchCommand: search [task]\n"
            + "HelpCommand: help\n"
            + "MoveStudentToGroupCommand: mv [StudentID] [sourcePath] [destinationPath]\n"
            + "ShowChildrenListCommand: ls\n"
            + "ShowTaskListCommand: cat\n";

    /**
     * Executes the HelpCommand to display program usage instructions.
     *
     * @param model The current program model.
     * @return A CommandResult containing the help message and related flags.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
