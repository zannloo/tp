package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ShowChildrenListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;

/**
 * Parses input arguments and creates a new ShowChildrenListCommand object
 */
public class ShowChildrenListCommandParser implements Parser<ShowChildrenListCommand> {
    public static final String MESSAGE_COMMAND_CREATED = "Created new \"cat\" command: %1$s";
    private static final Logger logger = LogsCenter.getLogger(ShowChildrenListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ShowChildrenListCommand
     * and returns an ShowChildrenListCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @return A ShowChildrenListCommand based on the input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowChildrenListCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return ShowChildrenListCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyAllOptionsValid(args);

        if (argMultimap.getPreamble().isEmpty()) {
            logger.fine(String.format(MESSAGE_COMMAND_CREATED, "Current directory"));
            return new ShowChildrenListCommand();
        }

        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath target = ParserUtil.resolvePath(currPath, path);

        logger.fine(String.format(MESSAGE_COMMAND_CREATED, path.toString()));

        return new ShowChildrenListCommand(target);
    }
}
