package seedu.address.logic.parser.newcommandparser;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class CreateDeadlineCommandParserTest {
    private static final Logger logger = LogsCenter.getLogger(CreateDeadlineCommandParser.class);
    private CreateDeadlineCommandParser parser = new CreateDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String argument = "~/grp-001/ -d assignment 2 -dt 2023-09-22 11:30";

        try {
            Command command = parser.parse(argument);
            logger.info(command.toString());
        } catch (ParseException pe) {
            logger.warning(pe.getMessage());
            // throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
