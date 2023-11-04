package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.profbook.Root;

/**
 * A class for handling storage of ProfBook in JSON format.
 */
public class JsonProfBookStorage implements Storage {

    private static final Logger logger = LogsCenter.getLogger(JsonProfBookStorage.class);

    private Path filePath;

    public JsonProfBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * @return Path
     */
    @Override
    public Path getUserPrefsFilePath() {
        return null;
    }

    /**
     * @return Empty Optional
     * @throws DataLoadingException
     */
    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return Optional.empty();
    }

    /**
     * @param userPrefs cannot be null.
     * @throws IOException
     */
    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {

    }

    public Path getProfBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<Root> readProfBook() throws DataLoadingException {
        return readProfBook(filePath);
    }

    /**
     * Reads the ProfBook from a specified file path.
     *
     * @param filePath The path to the file.
     * @return An Optional containing the Root if it exists, else an empty Optional.
     * @throws DataLoadingException If there was a problem loading data from file.
     */
    public Optional<Root> readProfBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableProfBookRoot> jsonProfBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableProfBookRoot.class);
        if (jsonProfBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProfBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveProfBook(Root profBook) throws IOException {
        saveProfBook(profBook, filePath);
    }

    /**
     * Saves the ProfBook to a specified file path.
     *
     * @param profBook The Root to save.
     * @param filePath The path to the file.
     * @throws IOException If there was a problem writing to the file.
     */
    public void saveProfBook(Root profBook, Path filePath) throws IOException {
        requireNonNull(profBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProfBookRoot(profBook), filePath);
    }

}
