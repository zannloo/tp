package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.newcommands.HelpCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input to create a `HelpCommand`. This parser handles commands to display help messages.
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    /**
     * Parses the given command arguments and creates a `HelpCommand` object.
     *
     * @param args The command arguments to be parsed.
     * @return A `HelpCommand` object.
     * @throws ParseException If the command arguments are invalid or if parsing fails.
     */
    public HelpCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        return new HelpCommand();
    }
}
