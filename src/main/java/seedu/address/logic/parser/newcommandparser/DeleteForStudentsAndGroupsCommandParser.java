package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.newcommands.DeleteForStudentsAndGroupsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;


/**
 * Parses input arguments and creates a new DeleteForStudentsAndGroupsCommand object
 */
public class DeleteForStudentsAndGroupsCommandParser implements Parser<DeleteForStudentsAndGroupsCommand> {
    private static final String INVALID_PATH_MESSAGE = "Destination path provided is not a group or student directory.";
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteForStudentsAndGroupsCommand
     * and returns an DeleteForStudentsAndGroupsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteForStudentsAndGroupsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteForStudentsAndGroupsCommand.MESSAGE_USAGE));
        }
        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());
        return new DeleteForStudentsAndGroupsCommand(path);
    }
}
