package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ChangeDirectoryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
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
import seedu.address.model.path.AbsolutePath;

/**
 * Parses user input.
 */
public class ProfBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ProfBookParser.class);
    private final Map<String, Parser<? extends Command>> commandParsers = new HashMap<>();
    private final Map<String, Command> commandsWithoutParser = new HashMap<>();

    /**
     * Initialize the command parsers map in the constructor
     */
    public ProfBookParser() {
        commandParsers.put(CreateStudentCommand.COMMAND_WORD, new CreateStudentCommandParser());
        commandParsers.put(CreateGroupCommand.COMMAND_WORD, new CreateGroupCommandParser());
        commandParsers.put(CreateTodoCommand.COMMAND_WORD, new CreateTodoCommandParser());
        commandParsers.put(CreateDeadlineCommand.COMMAND_WORD, new CreateDeadlineCommandParser());
        commandParsers.put(MoveStudentToGroupCommand.COMMAND_WORD, new MoveStudentToGroupCommandParser());
        commandParsers.put(ChangeDirectoryCommand.COMMAND_WORD, new ChangeDirectoryCommandParser());
        commandParsers.put(ShowTaskListCommand.COMMAND_WORD, new ShowTaskListCommandParser());
        commandParsers.put(ShowChildrenListCommand.COMMAND_WORD, new ShowChildrenListCommandParser());
        commandParsers.put(DeleteForStudentsAndGroupsCommand.COMMAND_WORD,
                new DeleteForStudentsAndGroupsCommandParser());
        commandParsers.put(EditCommand.COMMAND_WORD, new EditCommandParser());
        commandParsers.put(DeleteTaskCommand.COMMAND_WORD, new DeleteTaskCommandParser());
        commandParsers.put(MarkCommand.COMMAND_WORD, new MarkCommandParser());
        commandParsers.put(UnmarkCommand.COMMAND_WORD, new UnmarkCommandParser());
        commandsWithoutParser.put(HelpCommand.COMMAND_WORD, new HelpCommand());
        commandsWithoutParser.put(ExitCommand.COMMAND_WORD, new ExitCommand());
        commandsWithoutParser.put(ClearCommand.COMMAND_WORD, new ClearCommand());
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param currPath The current path of the applicaiton
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, AbsolutePath currPath) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        if (commandParsers.containsKey(commandWord)) {
            return commandParsers.get(commandWord).parse(arguments, currPath);
        }

        if (commandsWithoutParser.containsKey(commandWord)) {
            return commandsWithoutParser.get(commandWord);
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));
    }

}

