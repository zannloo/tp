package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;

import seedu.address.logic.newcommands.CreateToDoForStudentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;
import seedu.address.model.taskmanager.ToDo;

public class CreateTodoForGroupCommandParser implements Parser<CreateToDoForStudentCommand> {
    //private static final String INVALID_PATH_MESSAGE = "Destination path provided is not a student directory.";

    public CreateToDoForStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTION_DESC);

        if (!ParserUtil.areOptionsPresent(argMultimap, OPTION_DESC)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "todo/usage_format"));
        }

        argMultimap.verifyNoDuplicateOptionsFor(OPTION_DESC);

        RelativePath path = ParserUtil.parsePath(argMultimap.getPreamble());

        // Id id = null;
        // try {
        //     id = path.getStudentId();
        // } catch (UnsupportedPathOperationException e) {
        //     throw new ParseException(INVALID_PATH_MESSAGE);
        // } catch (InvalidIdException e) {
        //     throw new ParseException(e.getMessage());
        // }
        
        ToDo todo = new ToDo(argMultimap.getValue(OPTION_DESC).get());


        return new CreateToDoForStudentCommand(path, todo);
    }
}
