package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleProfBook;

public class ProfBookStorageManagerTest {

    @TempDir
    public Path testFolder;

    private ProfBookStorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonProfBookStorage profBookStorage = new JsonProfBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new ProfBookStorageManager(profBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

//    @Test
//    public void addressBookReadSave() throws Exception {
//        Root original = SampleProfBook.getRoot();
//        storageManager.saveProfBook(original);
//        Root retrieved = storageManager.readProfBook().get();
//        Set<Task> originalTasks = new HashSet<>(original.);
//        Set<Task> retrievedTasks = new HashSet<>(retrieved.getTaskList());
//
//        assertEquals(originalTasks, retrievedTasks);
//        assertEquals(original, retrieved);
//    }

    @Test
    public void getProfBookFilePath() {
        assertNotNull(storageManager.getProfBookFilePath());
    }

}
