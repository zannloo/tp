package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProfBook;
import seedu.address.model.person.Person;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.TaskList;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Root toModelType() throws IllegalValueException {
        Root profbook = new Root(new TaskList(new ArrayList<>()), new HashMap<>());
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group grp = jsonAdaptedGroup.toModelType();
            if (profbook.hasChild(grp.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            profbook.addChild(grp.getId(), grp);
        }
        return profbook;
    }
}
