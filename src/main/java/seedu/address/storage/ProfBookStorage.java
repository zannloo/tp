package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyProfBook;
import seedu.address.model.profbook.Root;

public interface ProfBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getProfBookFilePath();

    /**
     * Returns ProfBook data as a {@link ReadOnlyProfBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<Root> readProfBook() throws DataLoadingException;

    Optional<Root> readProfBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyProfBook} to the storage.
     * @param profBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProfBook(Root profBook) throws IOException;

    /**
     * @see #saveProfBook(Root)
     */
    void saveProfBook(Root profBook, Path filePath) throws IOException;
}
