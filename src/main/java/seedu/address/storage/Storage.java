package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;

/**
 * This interface defines the methods that classes responsible for handling storage operations should implement.
 */
public interface Storage extends ProfBookStorage, UserPrefsStorage {
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Returns the file path of the data file.
     */
    Path getProfBookFilePath();

    /**
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<Root> readProfBook() throws DataLoadingException;

    Optional<Root> readProfBook(Path filePath) throws DataLoadingException;

    /**
     * @param profBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProfBook(Root profBook) throws IOException;

    /**
     * @see #saveProfBook(Root)
     */
    void saveProfBook(Root profBook, Path filePath) throws IOException;
}

