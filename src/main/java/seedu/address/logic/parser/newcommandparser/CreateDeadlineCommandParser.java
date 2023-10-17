package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ALL;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import java.time.LocalDateTime;

import seedu.address.logic.newcommands.CreateDeadlineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;
import seedu.address.model.taskmanager.Deadline;

/**
 * Parses input arguments and creates a new CreateDeadlineForGroupCommand object
 */
public class CreateDeadlineCommandParser implements Parser<CreateDeadlineCommand> {
    //deadline: only need one deadline command
    /**
     * Parses the given {@code String} of arguments in the context of the CreateDeadlineCommand
     * and returns an CreateDeadlineCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateDeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC, OPTION_DATETIME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CreateDeadlineCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC, OPTION_DATETIME, OPTION_ALL);

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());
        LocalDateTime by = ParserUtil.parseDateTime(argMultimap.getValue(OPTION_DATETIME).get());
        Deadline deadline = new Deadline(argMultimap.getValue(OPTION_DESC).get(), by);

        if (argMultimap.getValue(OPTION_ALL).isEmpty()) {
            return new CreateDeadlineCommand(path, deadline);
        } else {
            String category = ParserUtil.parseCategory(argMultimap.getValue(OPTION_ALL).get());
            return new CreateDeadlineCommand(path, deadline, category);
        }
    }
}
