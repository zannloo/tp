package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CreateDeadlineCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.task.Deadline;

/**
 * Parses input arguments and creates a new CreateDeadlineCommand object
 */
public class CreateDeadlineCommandParser implements Parser<CreateDeadlineCommand> {
    private static final Logger logger = LogsCenter.getLogger(CreateDeadlineCommandParser.class);

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

        ParserUtil.verifyAllOptionsValid(args, OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        // Check if compulsory arguments are given
        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC, OPTION_DATETIME)) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        // If no path given, default to current path.
        AbsolutePath fullTargetPath = argMultimap.getPreamble().isEmpty()
                ? currPath
                : ParserUtil.resolvePath(currPath, argMultimap.getPreamble());

        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(OPTION_DESC).get(),
                argMultimap.getValue(OPTION_DATETIME).get());

        // Check if all option provided
        if (!ParserUtil.isOptionPresent(argMultimap, OPTION_ALL)) {
            return new CreateDeadlineCommand(fullTargetPath, deadline, Category.NONE);
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());

        logger.finer("Created CreateDeadlineCommand with target path: " + fullTargetPath + ", deadline: " + deadline);

        return new CreateDeadlineCommand(fullTargetPath, deadline, category);
    }
}
