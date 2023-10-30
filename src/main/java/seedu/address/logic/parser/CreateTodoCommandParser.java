package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CreateTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.task.ToDo;

/**
 * Parses input arguments and creates a new CreateTodoForGroupCommand object
 */
public class CreateTodoCommandParser implements Parser<CreateTodoCommand> {
    //todo only need one todo command for both group and student

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTodoForGroupCommand
     * and returns an CreateTodoForGroupCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTodoCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_ALL);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CreateTodoCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_ALL);

        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }
        ToDo todo = new ToDo(argMultimap.getValue(OPTION_DESC).get());

        if (argMultimap.getValue(OPTION_ALL).isEmpty()) {
            return new CreateTodoCommand(targetPath, todo, Category.NONE);
        }
        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());
        return new CreateTodoCommand(targetPath, todo, category);
    }
}
