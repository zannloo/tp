package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
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
        logger.fine("Parsing delete task command with arguments: " + args);
        try {
            Index index = ParserUtil.parseIndex(args);
            logger.fine("Index parsed (One Based): " + index.getOneBased());
            return new DeleteTaskCommand(index);
        } catch (ParseException pe) {
            logger.warning("Error parsing delete task command: " + pe.getMessage());
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
