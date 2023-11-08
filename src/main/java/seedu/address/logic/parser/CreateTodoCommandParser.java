package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CreateTodoCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CreateTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.task.ToDo;

/**
 * Parses input arguments and creates a new CreateTodoCommand object
 */
public class CreateTodoCommandParser implements Parser<CreateTodoCommand> {
    private static final Logger logger = LogsCenter.getLogger(CreateTodoCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTodoCommand
     * and returns an CreateTodoCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTodoCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return CreateTodoCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyAllOptionsValid(args, OPTION_DESC, OPTION_ALL);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_ALL);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC)) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_ALL);

        // If no path given, default to current path.
        AbsolutePath fullTargetPath = argMultimap.getPreamble().isEmpty()
                ? currPath
                : ParserUtil.resolvePath(currPath, argMultimap.getPreamble());

        ToDo todo = ParserUtil.parseToDo(argMultimap.getValue(OPTION_DESC).get());

        // Check if all option provided
        if (!ParserUtil.isOptionPresent(argMultimap, OPTION_ALL)) {
            return new CreateTodoCommand(fullTargetPath, todo, Category.NONE);
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());

        logger.finer("Created CreateTodoCommand with target path: " + fullTargetPath + ", todo: " + todo);

        return new CreateTodoCommand(fullTargetPath, todo, category);
    }
}
