package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.DeleteTaskCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    private static final Logger logger = LogsCenter.getLogger(DeleteTaskCommandParser.class);
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteForStudentsAndGroupsCommand
     * and returns an DeleteForStudentsAndGroupsCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ParserUtil.verifyAllOptionsValid(args, OPTION_HELP);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return DeleteTaskCommand.HELP_MESSAGE;
        }

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        logger.fine("Parsing delete task command with arguments: " + args);
        if (!ArgumentTokenizer.extractAllOptionNames(args).isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_NO_OPTIONS,
                    DeleteTaskCommand.COMMAND_WORD));
        }
        Index index = ParserUtil.parseIndex(args);
        logger.fine("Index parsed (One Based): " + index.getOneBased());
        return new DeleteTaskCommand(index);
    }
}
