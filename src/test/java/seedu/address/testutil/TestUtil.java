package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATETIME;
import static seedu.address.logic.parser.CliSyntax.OPTION_DESC;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.CreateDeadlineCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.CreateStudentCommand;
import seedu.address.logic.commands.CreateTodoCommand;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ToDo;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    public static String getCreateStudentCommand(AbsolutePath targetPath, Student student) {
        String[] parts = new String[]{CreateStudentCommand.COMMAND_WORD, targetPath.toString(),
                OPTION_NAME.getLongName(), student.getName().fullName,
                OPTION_ADDRESS.getLongName(), student.getAddress().value,
                OPTION_EMAIL.getLongName(), student.getEmail().value,
                OPTION_PHONE.getLongName(), student.getPhone().value};

        return String.join(" ", parts);
    }

    public static String getCreateGroupCommand(AbsolutePath targetPath, Group group) {
        String[] parts = new String[]{CreateGroupCommand.COMMAND_WORD, targetPath.toString(),
                OPTION_NAME.getLongName(), group.getName().fullName};

        return String.join(" ", parts);
    }

    public static String getCreateTodoCommand(AbsolutePath targetPath, ToDo todo) {
        String[] parts = new String[]{CreateTodoCommand.COMMAND_WORD, targetPath.toString(),
                OPTION_DESC.getLongName(), todo.getDesc()};

        return String.join(" ", parts);
    }

    public static String getCreateDeadlineCommand(AbsolutePath targetPath, Deadline deadline) {
        String[] parts = new String[]{CreateDeadlineCommand.COMMAND_WORD, targetPath.toString(),
                OPTION_DESC.getLongName(), deadline.getDesc(),
                OPTION_DATETIME.getLongName(), deadline.formatDueBy()};

        return String.join(" ", parts);
    }
}
