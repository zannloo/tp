package seedu.address.logic.parser.newcommandparser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.ShowChildrenListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
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
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowChildrenListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            logger.fine(String.format(MESSAGE_COMMAND_CREATED, "Current directory"));
            return new ShowChildrenListCommand();
        }

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        logger.fine(String.format(MESSAGE_COMMAND_CREATED, path.toString()));

        return new ShowChildrenListCommand(path);
    }
}
