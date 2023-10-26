package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;

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

    public JsonSerializableProfBookRoot(Root source) {
        groups.addAll(source.getAllChildren().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this book into the model's {@code Root} object.
     *
     * @return The Root object corresponding to this book.
     * @throws IllegalValueException If there were any data constraints violated in the adapted book.
     */
    public Root toModelType() throws IllegalValueException {
        Root profBook = new Root(new HashMap<>());
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group grp = jsonAdaptedGroup.toModelType();
            if (profBook.hasChild(grp.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            profBook.addChild(grp.getId(), grp);
        }
        return profBook;
    }

}
