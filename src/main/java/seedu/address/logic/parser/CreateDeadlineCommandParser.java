package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CreateDeadlineCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.task.Deadline;

/**
 * Parses input arguments and creates a new CreateDeadlineForGroupCommand object
 */
public class CreateDeadlineCommandParser implements Parser<CreateDeadlineCommand> {
    //deadline: only need one deadline command
    /**
     * Parses the given {@code String} of arguments in the context of the CreateDeadlineCommand
     * and returns an CreateDeadlineCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateDeadlineCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return CreateDeadlineCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyAllOptionsValid(args,
                OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC, OPTION_DATETIME)) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        // If no path given, default to current path.
        AbsolutePath fullTargetPath = null;
        if (argMultimap.getPreamble().isEmpty()) {
            fullTargetPath = currPath;
        } else {
            RelativePath target = ParserUtil.parseRelativePath(argMultimap.getPreamble());
            fullTargetPath = ParserUtil.resolvePath(currPath, target);
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        Deadline deadline = ParserUtil.parseDeadline(
                argMultimap.getValue(OPTION_DESC).get(),
                argMultimap.getValue(OPTION_DATETIME).get());

        if (argMultimap.getValue(OPTION_ALL).isEmpty()) {
            return new CreateDeadlineCommand(fullTargetPath, deadline, Category.NONE);
        }
        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());
        return new CreateDeadlineCommand(fullTargetPath, deadline, category);
    }
}
