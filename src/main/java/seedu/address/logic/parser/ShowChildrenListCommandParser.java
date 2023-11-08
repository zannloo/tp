package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.ShowChildrenListCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ShowChildrenListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;

/**
 * Parses input arguments and creates a new ShowChildrenListCommand object
 */
public class ShowChildrenListCommandParser implements Parser<ShowChildrenListCommand> {
    public static final String MESSAGE_COMMAND_CREATED = "Created new \"ls\" command: %1$s";
    private static final Logger logger = LogsCenter.getLogger(ShowChildrenListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ShowChildrenListCommand
     * and returns an ShowChildrenListCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowChildrenListCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return ShowChildrenListCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            logger.finer("Created ShowChildrenListCommand with target path: " + currPath);
            return new ShowChildrenListCommand();
        }

        AbsolutePath target = ParserUtil.resolvePath(currPath, preamble);

        logger.finer("Created ShowChildrenListCommand with target path: " + target);

        return new ShowChildrenListCommand(target);
    }
}
