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
            + "Change directory: cd\n"
            + "Create student: touch\n"
            + "Create group: mkdir\n"
            + "Create todo task: todo\n"
            + "Create deadline task: deadline\n"
            + "Delete group or student: rm\n"
            + "Delete task: rmt\n"
            + "Edit field: edit\n"
            + "Mark task: mark\n"
            + "Unmark task: unmark\n"
            + "Help: help\n"
            + "Move student from one group to another group: mv\n"
            + "Display children list: ls \n"
            + "Display task list: cat\n"
            + "Clear the input field: clear\n"
            + "Exit the program: exit";

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
