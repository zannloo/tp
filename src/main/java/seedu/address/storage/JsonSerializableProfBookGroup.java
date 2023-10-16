package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProfBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "profbook student")
public class JsonSerializableProfBookGroup {
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate group(s).";

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    @JsonCreator
    public JsonSerializableProfBookGroup(@JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.groups.addAll(groups);
    }

    public JsonSerializableProfBookGroup(ReadOnlyProfBook source) {
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

//    public ReadOnlyProfBook toModelType() throws IllegalValueException {
//
//    }
}
