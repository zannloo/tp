package seedu.address.logic.parser.newcommandparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.newcommands.MoveStudentToGroupCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.RelativePath;

public class MoveStudentToGroupCommandParser implements Parser<MoveStudentToGroupCommand> {

    public MoveStudentToGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "todo/usage_format"));
        }

        String[] paths = argMultimap.getPreamble().split(" ");

        if (paths.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "todo/usage_format"));
        }

        RelativePath source = ParserUtil.parsePath(paths[0]);
        RelativePath dest = ParserUtil.parsePath(paths[1]);

        return new MoveStudentToGroupCommand(source, dest);
    }
}
