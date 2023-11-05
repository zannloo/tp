package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The HelpCommand class represents a command to display program usage instructions to the user.
 * When executed, it displays a help message with information on how to use the program.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "To view the list of available commands, please type 'help'.";

    public static final String SHOWING_HELP_MESSAGE =
            "Use any command with --help for more information.\n"
            + "Note: clear, exit, and help commands do not have a --help option\n"
            + "cd        Change directory\n"
            + "mkdir     Create group\n"
            + "ls        Display children list\n"
            + "mv        Move student from one group to another group\n"
            + "touch     Create student\n"
            + "rm        Delete group or student\n"
            + "edit      Edit field\n"
            + "todo      Create todo task\n"
            + "deadline  Create deadline task\n"
            + "cat       Display task list\n"
            + "rmt       Delete task\n"
            + "mark      Mark task\n"
            + "unmark    Unmark task\n"
            + "clear     Erase the application's data\n"
            + "exit      Exit the program\n"
            + "help      Show this menu";

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
