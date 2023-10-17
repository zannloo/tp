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
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.ToDo;

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
        groups.addAll(source.getAllChildren().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
        tasks.addAll(source.getAllTask().stream().
                map(task -> (task instanceof ToDo)
                        ? new JsonAdaptedToDo((ToDo) task)
                        : new JsonAdaptedDeadline((Deadline) task))
                .collect(Collectors.toList()));
    }

    public Root toModelType() throws IllegalValueException {
        Root profBook = new Root(new TaskList(new ArrayList<>()), new HashMap<>());
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group grp = jsonAdaptedGroup.toModelType();
            if (profBook.hasChild(grp.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            profBook.addChild(grp.getId(), grp);
        }
        for (JsonAdaptedTasks jsonAdaptedtasks : tasks) {
            Task task = jsonAdaptedtasks.toModelType();
            if (profBook.checkDuplicates(task)){
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            profBook.addTask(task);
        }
        return profBook;
    }

}