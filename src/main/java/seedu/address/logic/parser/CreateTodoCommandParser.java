package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CreateTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
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
        ParserUtil.verifyAllOptionsValid(args, OPTION_DESC, OPTION_ALL, OPTION_HELP);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_ALL, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return CreateTodoCommand.HELP_MESSAGE;
        }

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CreateTodoCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_ALL);

        // If no path given, default to current path.
        AbsolutePath fullTargetPath = null;
        if (argMultimap.getPreamble().isEmpty()) {
            fullTargetPath = currPath;
        } else {
            RelativePath target = ParserUtil.parseRelativePath(argMultimap.getPreamble());
            fullTargetPath = ParserUtil.resolvePath(currPath, target);
        }

        ToDo todo = new ToDo(argMultimap.getValue(OPTION_DESC).get());

        if (argMultimap.getValue(OPTION_ALL).isEmpty()) {
            return new CreateTodoCommand(fullTargetPath, todo, Category.NONE);
        }
        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());
        return new CreateTodoCommand(fullTargetPath, todo, category);
    }
}
