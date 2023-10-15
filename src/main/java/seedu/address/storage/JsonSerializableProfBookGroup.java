package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyProfBook;

import java.util.ArrayList;
import java.util.List;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "profbook student")
public class JsonSerializableProfBookGroup {
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate group(s).";

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    public ReadOnlyProfBook toModelType() throws IllegalValueException {

    }
}
