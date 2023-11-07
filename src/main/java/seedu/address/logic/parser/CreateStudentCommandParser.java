package seedu.address.logic.parser;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.address.logic.commands.CreateStudentCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;

/**
 * Parses input arguments and creates a new CreateStudentCommand object
 */
public class CreateStudentCommandParser implements Parser<CreateStudentCommand> {
    private static final Logger logger = LogsCenter.getLogger(CreateStudentCommandParser.class);
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
        requireAllNonNull(args, currPath);

        if (ParserUtil.hasHelpOption(args)) {
            return CreateStudentCommand.HELP_MESSAGE;
        }

        ParserUtil.verifyAllOptionsValid(args, OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_NAME) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_ARGUMENT.apply(COMMAND_WORD));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_NAME, OPTION_PHONE, OPTION_EMAIL, OPTION_ADDRESS);

        AbsolutePath targetPath = ParserUtil.resolvePath(currPath, argMultimap.getPreamble());

        if (!targetPath.isStudentDirectory()) {
            throw new ParseException(INVALID_PATH_MESSAGE);
        }

        Student student = parseStudent(argMultimap, targetPath);

        logger.finer("Created CreateStudentCommand with target path: " + targetPath + ", student: " + student);

        return new CreateStudentCommand(targetPath, student);
    }

    private Student parseStudent(ArgumentMultimap argMultimap, AbsolutePath targetPath) throws ParseException {
        Name name = ParserUtil.parseName(argMultimap.getValue(OPTION_NAME).get());
        StudentId id = ParserUtil.parseStudentId(targetPath);
        Phone phone = ParserUtil.isOptionPresent(argMultimap, OPTION_PHONE)
                ? ParserUtil.parsePhone(argMultimap.getValue(OPTION_PHONE).get())
                : Phone.PLACEHOLDER;
        Email email = ParserUtil.isOptionPresent(argMultimap, OPTION_EMAIL)
                ? ParserUtil.parseEmail(argMultimap.getValue(OPTION_EMAIL).get())
                : Email.PLACEHOLDER;
        Address address = ParserUtil.isOptionPresent(argMultimap, OPTION_ADDRESS)
                ? ParserUtil.parseAddress(argMultimap.getValue(OPTION_ADDRESS).get())
                : Address.PLACEHOLDER;

        return new Student(name, email, phone, address, id);
    }
}
