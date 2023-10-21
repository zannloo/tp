package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.ChangeDirectoryCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;


/**
 * Parses input arguments and creates a new ChangeDirectoryCommand object
 */
public class ChangeDirectoryCommandParser implements Parser<ChangeDirectoryCommand> {
    private static final Logger logger = LogsCenter.getLogger(ChangeDirectoryCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeDirectoryCommand
     * and returns an ChangeDirectoryCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeDirectoryCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, ChangeDirectoryCommand.MESSAGE_USAGE));
        }

        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        logger.info("Creating ChangeDirectoryCommand with dest: " + path.toString());

        return new ChangeDirectoryCommand(targetPath);
    }
}
