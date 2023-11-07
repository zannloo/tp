package seedu.address.logic.parser;

import static seedu.address.logic.commands.ShowTaskListCommand.COMMAND_WORD;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ShowTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;

/**
 * Parses input arguments and creates a new ShowTaskListCommand object
 */
public class ShowTaskListCommandParser implements Parser<ShowTaskListCommand> {
    public static final String MESSAGE_COMMAND_CREATED = "Created new \"cat\" command: %1$s";
    private static final Logger logger = LogsCenter.getLogger(ShowTaskListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ShowTaskListCommand
     * and returns an ShowTaskListCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @return A ShowTaskListCommand based on the input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowTaskListCommand parse(String args, AbsolutePath currPath) throws ParseException {
        if (ParserUtil.hasHelpOption(args)) {
            return ShowTaskListCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            logger.fine(String.format(MESSAGE_COMMAND_CREATED, "Current directory"));
            return new ShowTaskListCommand();
        }

        RelativePath path = ParserUtil.parseRelativePath(preamble);
        AbsolutePath target = ParserUtil.resolvePath(currPath, path);

        logger.fine(String.format(MESSAGE_COMMAND_CREATED, path.toString()));

        return new ShowTaskListCommand(target);
    }
}
