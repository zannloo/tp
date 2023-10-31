package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import seedu.address.logic.commands.MoveStudentToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;

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
        ParserUtil.verifyAllOptionsValid(args, OPTION_HELP);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return MoveStudentToGroupCommand.HELP_MESSAGE;
        }

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MoveStudentToGroupCommand.MESSAGE_USAGE));
        }

        String[] paths = argMultimap.getPreamble().split(" ");

        if (paths.length != 2) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MoveStudentToGroupCommand.MESSAGE_USAGE));
        }

        RelativePath source = ParserUtil.parseRelativePath(paths[0]);
        AbsolutePath absoluteSource = null;
        try {
            absoluteSource = currPath.resolve(source);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        RelativePath dest = ParserUtil.parseRelativePath(paths[1]);
        AbsolutePath absoluteDest = null;
        try {
            absoluteDest = currPath.resolve(dest);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        return new MoveStudentToGroupCommand(absoluteSource, absoluteDest);
    }
}
