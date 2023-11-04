package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;

/**
 * A class to manage the storage of ProfBook.
 **/
public class ProfBookStorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(ProfBookStorageManager.class);
    private ProfBookStorage profBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Constructs a {@code ProfBookStorageManager} with the given {@code ProfBookStorage} and {@code UserPrefsStorage}.
     *
     * @param profBookStorage A storage for the ProfBook.
     * @param userPrefsStorage A storage for the user preferences.
     */
    public ProfBookStorageManager(ProfBookStorage profBookStorage, UserPrefsStorage userPrefsStorage) {
        this.profBookStorage = profBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }
    @Override
    public Path getProfBookFilePath() {
        return profBookStorage.getProfBookFilePath();
    }

    @Override
    public Optional<Root> readProfBook() throws DataLoadingException {
        return readProfBook(profBookStorage.getProfBookFilePath());
    }

    // ================ ProfBook methods ==============================
    @Override
    public Optional<Root> readProfBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return profBookStorage.readProfBook(filePath);
    }

    @Override
    public void saveProfBook(Root profBook) throws IOException {
        saveProfBook(profBook, profBookStorage.getProfBookFilePath());
    }

    @Override
    public void saveProfBook(Root profBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        profBookStorage.saveProfBook(profBook, filePath);
    }
}
