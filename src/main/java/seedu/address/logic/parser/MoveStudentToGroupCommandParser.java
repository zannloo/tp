package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.MoveStudentToGroupCommand.COMMAND_WORD;

import seedu.address.logic.commands.MoveStudentToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;

/**
 * Parses input arguments and creates a new MoveStudentToGroupCommand object
 */
public class MoveStudentToGroupCommandParser implements Parser<MoveStudentToGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MoveStudentToGroupCommand
     * and returns an MoveStudentToGroupCommand object for execution.
     *
     * @param args The user input string.
     * @param currPath The current path of the application.
     * @return A MoveStudentToGroupCommand based on the input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MoveStudentToGroupCommand parse(String args, AbsolutePath currPath) throws ParseException {
        if (ParserUtil.hasHelpOption(args)) {
            return MoveStudentToGroupCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyNoOption(args, COMMAND_WORD);

        String preamble = ArgumentTokenizer.extractPreamble(args);

        if (preamble.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        String[] paths = preamble.split(" ");

        if (paths.length != 2) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        RelativePath source = ParserUtil.parseRelativePath(paths[0]);
        AbsolutePath absoluteSource = ParserUtil.resolvePath(currPath, source);

        RelativePath dest = ParserUtil.parseRelativePath(paths[1]);
        AbsolutePath absoluteDest = ParserUtil.resolvePath(currPath, dest);

        return new MoveStudentToGroupCommand(absoluteSource, absoluteDest);
    }
}
