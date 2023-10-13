package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import seedu.address.logic.newcommands.CreateTodoCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;
import seedu.address.model.taskmanager.ToDo;

/**
 * Parses input arguments and creates a new CreateTodoForGroupCommand object
 */
public class CreateTodoCommandParser implements Parser<CreateTodoCommand> {
    //todo only need one todo command for both group and student

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTodoForGroupCommand
     * and returns an CreateTodoForGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTodoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "todo/usage_format"));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC);

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        ToDo todo = new ToDo(argMultimap.getValue(OPTION_DESC).get());

        return new CreateTodoCommand(path, todo);
    }
}
