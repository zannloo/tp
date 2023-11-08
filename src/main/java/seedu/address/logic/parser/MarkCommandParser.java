package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.MarkCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * Parses input arguments and creates a new MarkCommand object.
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    private static final Logger logger = LogsCenter.getLogger(MarkCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTodoCommand
     * and returns an CreateTodoCommand object for execution.
     *
     * @param args     The user input string to be parsed.
     * @param currPath The current path in the application.
     * @throws ParseException If the input does not conform to the expected format.
     */
    public MarkCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return MarkCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        Index index = ParserUtil.parseIndex(preamble);

        logger.finer("Created MarkCommand with index (one-based): " + index.getOneBased());

        return new MarkCommand(index);
    }
}
