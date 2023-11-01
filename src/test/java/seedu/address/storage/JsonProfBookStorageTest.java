package seedu.Prof.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.profbook.Root;
import seedu.address.storage.JsonProfBookStorage;

public class JsonProfBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProfBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProfBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProfBook(null));
    }

    private java.util.Optional<Root> readProfBook(String filePath) throws Exception {
        return new JsonProfBookStorage(Paths.get(filePath)).readProfBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProfBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readProfBook("notJsonFormatProfBook.json"));
    }

    @Test
    public void readProfBook_invalidAndValidRoot_throwsDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readProfBook("invalidAndValidRoot.json"));
    }

    @Test
    public void saveProfBook_nullProfBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ProfBook} at the specified {@code filePath}.
     */
    private void saveProfBook(Root ProfBook, String filePath) {
        try {
            new JsonProfBookStorage(Paths.get(filePath))
                    .saveProfBook(ProfBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProfBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfBook(new Root(), null));
    }
}
