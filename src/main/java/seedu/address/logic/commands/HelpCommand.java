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
            + "CreateStudentCommand: touch [path to student] -n [name] -p [phone] -e [email] -a [address]\n"
            + "CreateGroupCommand: mkdir [groupId] -n [groupName]\n"
            + "CreateTodoCommand(For group): todo [groupId] -d [task] --all [category]\n"
            + "CreateTodoCommand(For student): todo [studentId] -d [task]\n"
            + "CreateDeadlineCommand(For group): “deadline [groupId] -d [task] -dt [yyyy/MM/dd hh:mm] -all [category]\n"
            + "CreateDeadlineCommand(For student): “deadline [studentId] -d [task] -dt [yyyy/MM/dd hh:mm]\n"
            + "DeleteCommand: rm [groupId/studentId]\n"
            + "DeleteTaskCommand: rmt [index_number]\n"
            + "EditCommand: edit [path to student] [field to be updated]\n"
            + "MarkCommand: mark [index_number]\n"
            + "UnmarkCommand: unmark [index_number]\n"
            + "HelpCommand: help\n"
            + "MoveStudentToGroupCommand: mv [path to student] [path to group]\n"
            + "ShowChildrenListCommand: ls \n"
            + "ShowTaskListCommand: cat [path to student]\n";

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
