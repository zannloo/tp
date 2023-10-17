package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.profbook.Root;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

public class ProfBookStorageManager implements ProfBookStorage{

    private static final Logger logger = LogsCenter.getLogger(ProfBookStorageManager.class);
    private ProfBookStorage profBookStorage;

    public ProfBookStorageManager(ProfBookStorage profBookStorage) {
        this.profBookStorage = profBookStorage;
    }
    @Override
    public Path getProfBookFilePath() {
        return profBookStorage.getProfBookFilePath();
    }

    @Override
    public Optional<Root> readProfBook() throws DataLoadingException {
        return readProfBook(profBookStorage.getProfBookFilePath());
    }

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
