package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.MoveStudentToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * Parses input arguments and creates a new MoveStudentToGroupCommand object
 */
public class MoveStudentToGroupCommandParser implements Parser<MoveStudentToGroupCommand> {
    private static final Logger logger = LogsCenter.getLogger(MoveStudentToGroupCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the MoveStudentToGroupCommand
     * and returns an MoveStudentToGroupCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MoveStudentToGroupCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return MoveStudentToGroupCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        String[] paths = preamble.split(" ");

        if (paths.length < 2) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        if (paths.length > 2) {
            throw new ParseException(MESSAGE_TOO_MANY_ARGUMENTS.apply(COMMAND_WORD));
        }

        AbsolutePath absoluteSource = ParserUtil.resolvePath(currPath, paths[0]);

        AbsolutePath absoluteDest = ParserUtil.resolvePath(currPath, paths[1]);

        logger.finer("Created MoveStudentToGroupCommand with source path: " + absoluteSource
                + ", dest path: " + absoluteDest);

        return new MoveStudentToGroupCommand(absoluteSource, absoluteDest);
    }
}
