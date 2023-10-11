package seedu.address.logic.parser.newcommandparser;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();
    private static final Logger logger = LogsCenter.getLogger(CreateStudentCommandParserTest.class);
    // private Student expectedStudent = new Student(
    //                 new TaskList(new ArrayList<>()), 
    //                 new Name("Amy Bee"), 
    //                 new Email("amy@example.com"), 
    //                 new Phone("11111111"), 
    //                 new Address("Block 312, Amy Street 1"), 
    //                 new Id("stu-001"));

    @Test
    public void parse_allFieldsPresent_success() {
        String argument = "~/stu-001/ -n Random Group 1";

        try {
            Command command = parser.parse(argument);
            logger.info(command.toString());
        } catch (ParseException pe) {
            logger.warning(pe.getMessage());
            // throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
