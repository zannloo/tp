package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProfBook;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;

/**
 * An Immutable ProfBookRoot that is serializable to JSON format.
 */
@JsonRootName(value = "profbook root")
public class JsonSerializableProfBookRoot {
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate group(s).";

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    private final List<JsonAdaptedTasks> tasks = new ArrayList<>();

    @JsonCreator
    public JsonSerializableProfBookRoot(@JsonProperty("groups") List<JsonAdaptedGroup> groups,
                                        @JsonProperty("tasks") List<JsonAdaptedTasks> tasks) {
        this.groups.addAll(groups);
        this.tasks.addAll(tasks);
    }

    public JsonSerializableProfBookRoot(Root source) {
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(source.getAllChildren().stream().map())
        tasks.addAll()
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
        for (JsonAdaptedTasks jsonAdaptedtasks : tasks) {
            Task task = jsonAdaptedtasks.toModelType();
            if (profbook.checkDuplicates(task)){
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            profbook.addTask(task);
        }
        return profbook;
    }

}