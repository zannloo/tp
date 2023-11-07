package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CreateGroupCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.GroupId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;

/**
 * Parses input arguments and creates a new CreateGroupCommand object
 */
public class CreateGroupCommandParser implements Parser<CreateGroupCommand> {
    private static final Logger logger = LogsCenter.getLogger(CreateGroupCommandParser.class);
    private static final String INVALID_PATH_MESSAGE = "Destination path provided is not a group directory.";

    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns an CreateGroupCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateGroupCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return CreateGroupCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyAllOptionsValid(args, OPTION_NAME);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_NAME);

        // Check if compulsory arguments are given
        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_NAME) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME);

        AbsolutePath targetPath = ParserUtil.resolvePath(currPath, (argMultimap.getPreamble()));

        // Check if target path is group path
        if (!targetPath.isGroupDirectory()) {
            throw new ParseException(INVALID_PATH_MESSAGE);
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get());
        GroupId id = ParserUtil.parseGroupId(targetPath);
        Group group = new Group(name, id);

        logger.finer("Created CreateGroupCommand with target path: " + targetPath + ", group: " + group);

        return new CreateGroupCommand(targetPath, group);
    }
}
