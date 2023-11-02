package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;

import java.time.LocalDateTime;

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

        ParserUtil.verifyAllOptionsValid(args,
                OPTION_DESC, OPTION_DATETIME, OPTION_ALL, OPTION_HELP);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_DATETIME, OPTION_ALL, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return CreateDeadlineCommand.HELP_MESSAGE;
        }

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC, OPTION_DATETIME)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CreateDeadlineCommand.MESSAGE_USAGE));
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

        LocalDateTime by = ParserUtil.parseDateTime(argMultimap.getValue(OPTION_DATETIME).get());
        Deadline deadline = new Deadline(argMultimap.getValue(OPTION_DESC).get(), by);

        if (argMultimap.getValue(OPTION_ALL).isEmpty()) {
            return new CreateDeadlineCommand(fullTargetPath, deadline, Category.NONE);
        }
        Category category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());
        return new CreateDeadlineCommand(fullTargetPath, deadline, category);
    }
}