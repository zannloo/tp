package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

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

        logger.fine("Parsing unmark task command with arguments: " + args);

        ParserUtil.verifyAllOptionsValid(args, OPTION_HELP);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return UnmarkCommand.HELP_MESSAGE;
        }

        Index index = ParserUtil.parseIndex(args);
        logger.fine("Index parsed (One Based): " + index.getOneBased());
        return new UnmarkCommand(index);
    }
}
