package seedu.address.logic.parser.newcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_ID;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import seedu.address.logic.newcommands.EditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.field.EditGroupDescriptor;
import seedu.address.model.field.EditStudentDescriptor;
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
     * @return An `EditCommand` object based on the parsed arguments.
     * @throws ParseException If the command arguments are invalid or if parsing fails.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_ID);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_ID);

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        if (path.isStudentDirectory()) {
            EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
            if (argMultimap.getValue(OPTION_NAME).isPresent()) {
                editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get()));
            }
            if (argMultimap.getValue(OPTION_ID).isPresent()) {
                editStudentDescriptor.setId(ParserUtil.parseStudentId(path));
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
            if (!editStudentDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return new EditCommand(path, editStudentDescriptor);
        }

        if (path.isGroupDirectory()) {
            EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();
            if (argMultimap.getValue(OPTION_NAME).isPresent()) {
                editGroupDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get()));
            }
            if (argMultimap.getValue(OPTION_ID).isPresent()) {
                editGroupDescriptor.setId(ParserUtil.parseGroupId(path));
            }
            if (!editGroupDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return new EditCommand(path, editGroupDescriptor);
        }
        return null;
    }
}
