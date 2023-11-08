package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;


/**
 * Parses input arguments and creates a new DeleteForStudentsAndGroupsCommand object
 */
public class DeleteForStudentsAndGroupsCommandParser implements Parser<DeleteForStudentsAndGroupsCommand> {
    private static final Logger logger = LogsCenter.getLogger(DeleteForStudentsAndGroupsCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteForStudentsAndGroupsCommand
     * and returns an DeleteForStudentsAndGroupsCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteForStudentsAndGroupsCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return DeleteForStudentsAndGroupsCommand.HELP_MESSAGE;
        }

        // Checks no option is given
        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        AbsolutePath targetPath = ParserUtil.resolvePath(currPath, preamble);

        logger.finer("Created DeleteForStudentsAndGroupsCommand with target: " + targetPath);

        return new DeleteForStudentsAndGroupsCommand(targetPath);
    }
}
