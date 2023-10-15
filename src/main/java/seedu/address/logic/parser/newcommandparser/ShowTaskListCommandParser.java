package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.ShowTaskListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowTaskListCommand object
 */
public class ShowTaskListCommandParser implements Parser<ShowTaskListCommand> {
    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ShowTaskListCommand
     * and returns an ShowTaskListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowTaskListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, ShowTaskListCommand.MESSAGE_USAGE));
        }

        return new ShowTaskListCommand();
    }
}
