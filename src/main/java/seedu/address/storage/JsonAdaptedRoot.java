package seedu.address.storage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;


/**
 * A class to adapt a Root object into a format suitable for JSON storage.
 */
public class JsonAdaptedRoot {

    private final Set<JsonAdaptedGroup> groups = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedRoot} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedRoot(@JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        if (groups != null) {
            this.groups.addAll(groups);
        }
    }

    /**
     * Constructs a JsonAdaptedRoot from a source `Root` object.
     *
     * @param source The source `Root` object to be adapted.
     */
    public JsonAdaptedRoot(Root source) {
        groups.addAll(source.getAllChildren().stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted root object into the model's Root object.
     *
     * @return The Root object that corresponds to this JsonAdaptedRoot.
     * @throws IllegalValueException If there were any data constraints violated in the adapted root.
     */
    public Root toModelType() throws IllegalValueException {
        Map<Id, Group> groupMap = new HashMap<>();
        for (JsonAdaptedGroup groupJson : groups) {
            Group grp = groupJson.toModelType();
            groupMap.put(grp.getId(), grp);
        }

        return new Root(groupMap);
    }
}
