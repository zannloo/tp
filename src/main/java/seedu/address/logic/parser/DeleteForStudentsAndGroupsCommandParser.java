package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import seedu.address.logic.commands.DeleteForStudentsAndGroupsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;


/**
 * Parses input arguments and creates a new DeleteForStudentsAndGroupsCommand object
 */
public class DeleteForStudentsAndGroupsCommandParser implements Parser<DeleteForStudentsAndGroupsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteForStudentsAndGroupsCommand
     * and returns an DeleteForStudentsAndGroupsCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteForStudentsAndGroupsCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ParserUtil.verifyAllOptionsValid(args, OPTION_HELP);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return DeleteForStudentsAndGroupsCommand.HELP_MESSAGE;
        }

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteForStudentsAndGroupsCommand.MESSAGE_USAGE));
        }
        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        return new DeleteForStudentsAndGroupsCommand(targetPath);
    }
}
