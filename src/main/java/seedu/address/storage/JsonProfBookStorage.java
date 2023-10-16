package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProfBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonProfBookStorage implements ProfBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProfBookStorage.class);

    private Path filePath;

    public JsonProfBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProfBook> readProfBook() throws DataLoadingException {
        return readProfBook(filePath);
    }
    public Optional<ReadOnlyProfBook> readProfBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableProfBookStudent> jsonProfBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableProfBookStudent.class);
        if (!jsonProfBook.isPresent()) {
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
    public void saveProfBook(ReadOnlyProfBook profBook) throws IOException {
        saveProfBook(profBook, filePath);
    }
    public void saveProfBook(ReadOnlyProfBook profBook, Path filePath) throws IOException {
        requireNonNull(profBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProfBookRoot(profBook, filePath);
    }

}
