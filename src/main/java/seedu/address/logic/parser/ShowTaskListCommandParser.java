package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ShowTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;

/**
 * Parses input arguments and creates a new ShowTaskListCommand object
 */
public class ShowTaskListCommandParser implements Parser<ShowTaskListCommand> {
    public static final String MESSAGE_COMMAND_CREATED = "Created new \"ls\" command: %1$s";
    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ShowTaskListCommand
     * and returns an ShowTaskListCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @return A ShowTaskListCommand based on the input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowTaskListCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ParserUtil.verifyAllOptionsValid(args, OPTION_HELP);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return ShowTaskListCommand.HELP_MESSAGE;
        }

        if (argMultimap.getPreamble().isEmpty()) {
            logger.fine(String.format(MESSAGE_COMMAND_CREATED, "Current directory"));
            return new ShowTaskListCommand();
        }

        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath target = null;
        try {
            target = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        logger.fine(String.format(MESSAGE_COMMAND_CREATED, path.toString()));

        return new ShowTaskListCommand(target);
    }
}
