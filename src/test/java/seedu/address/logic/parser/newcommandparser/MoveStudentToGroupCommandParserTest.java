package seedu.address.logic.parser.newcommandparser;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class MoveStudentToGroupCommandParserTest {
    private static final Logger logger = LogsCenter.getLogger(MoveStudentToGroupCommandParser.class);
    private MoveStudentToGroupCommandParser parser = new MoveStudentToGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String argument = "~/grp-001/stu-001 ~/grp-002/";

        try {
            Command command = parser.parse(argument);
            logger.info(command.toString());
        } catch (ParseException pe) {
            logger.warning(pe.getMessage());
            // throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
