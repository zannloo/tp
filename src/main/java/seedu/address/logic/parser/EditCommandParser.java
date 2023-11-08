package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.path.AbsolutePath;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {
    private static final Logger logger = LogsCenter.getLogger(EditCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException If the command arguments are invalid or if parsing fails.
     */
    public EditCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return EditCommand.HELP_MESSAGE;
        }

        String preamble = ArgumentTokenizer.extractPreamble(args);

        AbsolutePath targetPath = preamble.isEmpty()
                ? currPath
                : ParserUtil.resolvePath(currPath, preamble);

        if (targetPath.isStudentDirectory()) {
            return parseEditStudent(args, targetPath);
        }

        if (targetPath.isGroupDirectory()) {
            return parseEditGroup(args, targetPath);
        }

        throw new ParseException(EditCommand.MESSAGE_INCORRECT_DIRECTORY_ERROR);
    }

    private EditCommand parseEditStudent(String args, AbsolutePath target) throws ParseException {
        ParserUtil.verifyAllOptionsValid(args,
                OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_ID);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_ID);

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_ID);

        EditStudentDescriptor editStudentDescriptor = getEditStudentDescriptor(argMultimap);
        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        logger.finer("Created EditCommand (Student) with target path: " + target
                + ", descriptor: " + editStudentDescriptor);

        return new EditCommand(target, editStudentDescriptor);
    }

    private EditCommand parseEditGroup(String args, AbsolutePath target) throws ParseException {
        ParserUtil.verifyAllOptionsValid(args, OPTION_NAME, OPTION_ID);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_NAME, OPTION_ID);

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_ID);

        EditGroupDescriptor editGroupDescriptor = getEditGrouDescriptor(argMultimap);
        if (!editGroupDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        logger.finer("Created EditCommand (Group) with target path: " + target
                + ", descriptor: " + editGroupDescriptor);

        return new EditCommand(target, editGroupDescriptor);
    }

    private EditStudentDescriptor getEditStudentDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(OPTION_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get()));
        }
        if (argMultimap.getValue(OPTION_ID).isPresent()) {
            editStudentDescriptor.setId(ParserUtil.parseStudentId(argMultimap.getValue(OPTION_ID).get()));
        }
        if (argMultimap.getValue(OPTION_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(OPTION_PHONE).get()));
        }
        if (argMultimap.getValue(OPTION_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(OPTION_EMAIL).get()));
        }
        if (argMultimap.getValue(OPTION_ADDRESS).isPresent()) {
            editStudentDescriptor
                .setAddress(ParserUtil.parseAddress(argMultimap.getValue(OPTION_ADDRESS).get()));
        }
        return editStudentDescriptor;
    }

    private EditGroupDescriptor getEditGrouDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
        if (argMultimap.getValue(OPTION_NAME).isPresent()) {
            editGroupDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get()));
        }
        if (argMultimap.getValue(OPTION_ID).isPresent()) {
            editGroupDescriptor.setId(ParserUtil.parseGroupId(argMultimap.getValue(OPTION_ID).get()));
        }
        return editGroupDescriptor;
    }
}
