package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;



public class JsonAdaptedRoot {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final Set<JsonAdaptedGroup> groups = new HashSet<>();
    private final Set<JsonAdaptedTasks> tasks = new HashSet<>();

    @JsonCreator
    public JsonAdaptedRoot(@JsonProperty("groups") List<JsonAdaptedGroup> groups,
                            @JsonProperty("tasks") List<JsonAdaptedTasks> tasks) {
        if (groups != null) {
            this.groups.addAll(groups);
        }
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }

    }

    public Root toModelType() throws IllegalValueException {
        // Convert the list of JsonAdaptedStudent back into a map of students.
        final List<Task> taskList = new ArrayList<>();

        Map<Id, Group> groupMap = new HashMap<>();
        for (JsonAdaptedGroup groupJson : groups) {
            Group grp = groupJson.toModelType();
            groupMap.put(grp.getId(), grp);
        }
        for (JsonAdaptedTasks task : tasks) {

            taskList.add(task.toModelType());
        }

        final seedu.address.model.taskmanager.TaskList modelTList = new TaskList(taskList);

        return new Root(modelTList, groupMap);
    }
}
