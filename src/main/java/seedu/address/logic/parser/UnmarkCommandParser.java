package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.UnmarkCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * The UnmarkCommandParser class is responsible for parsing user input and creating UnmarkCommand objects.
 * It specifically handles the parsing of the "unmark" command.
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {
    private static final Logger logger = LogsCenter.getLogger(UnmarkCommandParser.class);

    /**
     * Parses the user input and creates an UnmarkCommand object.
     *
     * @param args     The user input string to be parsed.
     * @param currPath The current path in the application.
     * @return An UnmarkCommand object representing the parsed command.
     * @throws ParseException If the input does not conform to the expected format.
     */
    public UnmarkCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return UnmarkCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        Index index = ParserUtil.parseIndex(preamble);

        logger.finer("Created UnmarkCommand with index (one-based): " + index.getOneBased());

        return new UnmarkCommand(index);
    }
}
