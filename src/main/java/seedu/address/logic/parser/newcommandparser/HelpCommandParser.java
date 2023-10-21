package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.newcommands.HelpCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * The HelpCommandParser class is responsible for parsing user input and creating a HelpCommand
 * based on the input. It implements the Parser interface for parsing commands.
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    /**
     * Parses the user input to create a HelpCommand.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @return A HelpCommand based on the input.
     * @throws ParseException If the user input is invalid or in the wrong format.
     */
    public HelpCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        return new HelpCommand();
    }
}
