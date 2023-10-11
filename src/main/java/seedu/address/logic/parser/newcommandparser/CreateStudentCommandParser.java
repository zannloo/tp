package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.newcommands.CreateStudentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Option;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.TaskList;

public class CreateStudentCommandParser implements Parser<CreateStudentCommand> {
    private static final String INVALID_PATH_MESSAGE = "Destination path provided is not a student directory.";

    public CreateStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS, OPTION_TAG);

        if (!areOptionsPresent(argMultimap, OPTION_NAME, OPTION_ADDRESS, OPTION_PHONE, OPTION_EMAIL)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "todo/usage_format"));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);
        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        Id id = null;
        try {
            id = path.getStudentId();
        } catch (UnsupportedPathOperationException e) {
            throw new ParseException(INVALID_PATH_MESSAGE);
        } catch (InvalidIdException e) {
            throw new ParseException(e.getMessage());
        }
        
        Name name = ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(OPTION_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(OPTION_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(OPTION_ADDRESS).get());

        Student student = new Student(new TaskList(new ArrayList<>()), name, email, phone, address, id);

        return new CreateStudentCommand(path, student);
    }

    /**
     * Returns true if none of the options contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areOptionsPresent(ArgumentMultimap argumentMultimap, Option... options) {
        return Stream.of(options).allMatch(option -> argumentMultimap.getValue(option).isPresent());
    }
}
