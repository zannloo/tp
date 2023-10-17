package seedu.address.logic.parser.newcommandparser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.ShowTaskListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;

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
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowTaskListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            logger.fine(String.format(MESSAGE_COMMAND_CREATED, "Current directory"));
            return new ShowTaskListCommand();
        }

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        logger.fine(String.format(MESSAGE_COMMAND_CREATED, path.toString()));

        return new ShowTaskListCommand(path);
    }
}
