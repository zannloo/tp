package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_FORMAT;
import static seedu.address.logic.LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_TASK_LIST_NOT_SHOWN;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ShowChildrenListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;
import seedu.address.storage.JsonProfBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.ProfBookStorageManager;

public class LogicManagerTest {
    @TempDir
    public Path temporaryFolder;

    private Model model;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(ROOT_PATH, PROFBOOK_WITH_TWO_GROUPS, new UserPrefs());
        JsonProfBookStorage addressBookStorage =
                new JsonProfBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        ProfBookStorageManager storage = new ProfBookStorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertThrows(ParseException.class,
                String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE), (
                ) -> logic.execute(invalidCommand));;
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String removeTask = "rmt 1";
        assertThrows(CommandException.class,
                MESSAGE_TASK_LIST_NOT_SHOWN, (
                ) -> logic.execute(removeTask));;
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ShowChildrenListCommand.COMMAND_WORD;
        logic.execute(listCommand);
    }

    @Test
    public void execute_accessDeniedExceptionError_throwsCommandException() {
        AccessDeniedException e = new AccessDeniedException("Access Denied Exception");
        assertThrowsForStorageException(e, FILE_OPS_PERMISSION_ERROR_FORMAT);
    }

    @Test
    public void execute_iOException_throwsCommandException() {
        IOException e = new IOException("IO exception");
        assertThrowsForStorageException(e, FILE_OPS_ERROR_FORMAT);
    }

    @Test
    public void getDisplayList() {
        assertEquals(model.getDisplayList(), logic.getDisplayList());
    }

    @Test
    public void getCurrPath() {
        assertEquals(model.getCurrPath().toString(), logic.getCurrPath());
    }

    @Test
    public void isShowTaskList() {
        assertEquals(model.isShowTaskList(), logic.isShowTaskList());
    }

    @Test
    public void getAddressBookFilePath() {
        assertEquals(model.getProfBookFilePath(), logic.getProfBookFilePath());
    }

    @Test
    public void getGuiSettings() {
        assertEquals(model.getGuiSettings(), logic.getGuiSettings());
    }

    private void assertThrowsForStorageException(IOException e, String errorMsg) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");
        // Inject LogicManager with an ProfBookStorage that throws the IOException e when saving
        JsonProfBookStorage profBookStorage = new JsonProfBookStorage(prefPath) {
            @Override
            public void saveProfBook(Root profBook, Path filePath) throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        ProfBookStorageManager storage = new ProfBookStorageManager(profBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);
        assertThrows(CommandException.class,
                String.format(errorMsg, e.getMessage()), (
                ) -> logic.execute(ShowChildrenListCommand.COMMAND_WORD));;
    }
}
