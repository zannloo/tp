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
 * An Immutable ProfBookRoot that is serializable to JSON format.
 */
@JsonRootName(value = "profbook root")
public class JsonSerializableProfBookRoot {
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate group(s).";

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    @JsonCreator
    public JsonSerializableProfBookRoot(@JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.groups.addAll(groups);
    }

    public JsonSerializableProfBookRoot(ReadOnlyProfBook source) {
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

//    public ReadOnlyProfBook toModelType() throws IllegalValueException {
//
//    }
}