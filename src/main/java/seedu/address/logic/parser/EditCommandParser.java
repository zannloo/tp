package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_HELP;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;

/**
 * Parses user input to create an `EditCommand` for editing student or group details.
 * This parser handles commands that allow users to modify information of existing students or groups.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given command arguments and creates an `EditCommand` object.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @return An `EditCommand` object based on the parsed arguments.
     * @throws ParseException If the command arguments are invalid or if parsing fails.
     */
    public EditCommand parse(String args, AbsolutePath currPath) throws ParseException {
        requireAllNonNull(args, currPath);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, OPTION_HELP);

        if (ParserUtil.isOptionPresent(argMultimap, OPTION_HELP)) {
            return EditCommand.HELP_MESSAGE;
        }

        String preamble = ArgumentTokenizer.extractPreamble(args);

        AbsolutePath targetPath = null;
        if (preamble.isEmpty()) {
            targetPath = currPath;
        } else {
            RelativePath path = ParserUtil.parseRelativePath(preamble);
            targetPath = ParserUtil.resolvePath(currPath, path);
        }

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
