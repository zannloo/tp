package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;
import seedu.address.testutil.TypicalRoots;

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

    @Test
    public void getUserPrefFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

    @Test
    public void profBookReadSave() throws Exception {
        Root original = TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
        storageManager.saveProfBook(original);
        Root retrieved = storageManager.readProfBook().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getProfBookFilePath() {
        assertNotNull(storageManager.getProfBookFilePath());
    }

    @Test
    public void getUserPFilePath() {
        assertNotNull(storageManager.getProfBookFilePath());
    }
}
