package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import java.util.ArrayList;

import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.TaskList;

/**
 * Parses input arguments and creates a new CreateStudentCommand object
 */
public class CreateStudentCommandParser implements Parser<CreateStudentCommand> {
    private static final String INVALID_PATH_MESSAGE = "Destination path provided is not a student directory.";

    /**
     * Parses the given {@code String} of arguments in the context of the CreateStudentCommand
     * and returns an CreateStudentCommand object for execution.
     *
     * @param args The command arguments to be parsed.
     * @param currPath The current path of the application.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateStudentCommand parse(String args, AbsolutePath currPath) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);

        //todo: need usage format from command class
        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_NAME, OPTION_ADDRESS, OPTION_PHONE, OPTION_EMAIL)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CreateStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);

        RelativePath path = ParserUtil.parseRelativePath(argMultimap.getPreamble());
        AbsolutePath targetPath = null;
        try {
            targetPath = currPath.resolve(path);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }

        //todo: is possible to create student without provide id -> will auto generate id
        if (!targetPath.isStudentDirectory()) {
            throw new ParseException(INVALID_PATH_MESSAGE);
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(OPTION_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(OPTION_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(OPTION_ADDRESS).get());
        StudentId id = ParserUtil.parseStudentId(targetPath);

        Student student = new Student(new TaskList(new ArrayList<>()), name, email, phone, address, id);

        return new CreateStudentCommand(targetPath, student);
    }
}
