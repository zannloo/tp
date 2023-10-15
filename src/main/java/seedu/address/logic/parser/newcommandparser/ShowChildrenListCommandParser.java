package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.ShowChildrenListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowChildrenListCommand object
 */
public class ShowChildrenListCommandParser implements Parser<ShowChildrenListCommand> {
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

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, ShowChildrenListCommand.MESSAGE_USAGE));
        }

        return new ShowChildrenListCommand();
    }
}
