package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.newcommands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * The MarkCommandParser class is responsible for parsing user input and creating MarkCommand objects.
 * It specifically handles the parsing of the "mark" command.
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    private static final Logger logger = LogsCenter.getLogger(MarkCommandParser.class);

    /**
     * Parses the user input and creates a MarkCommand object.
     *
     * @param args     The user input string to be parsed.
     * @param currPath The current path in the application.
     * @return A MarkCommand object representing the parsed command.
     * @throws ParseException If the input does not conform to the expected format.
     */
    public MarkCommand parse(String args, AbsolutePath currPath) throws ParseException {

        logger.fine("Parsing mark task command with arguments: " + args);

        try {
            Index index = ParserUtil.parseIndex(args);
            logger.fine("Index parsed (One Based): " + index.getOneBased());
            return new MarkCommand(index);
        } catch (ParseException pe) {
            logger.warning("Error parsing mark task command: " + pe.getMessage());
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }
}
