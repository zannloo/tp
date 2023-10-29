package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * A class to adapt a ToDo object into a format suitable for JSON storage.
 */
@JsonTypeName("ToDo")
public class JsonAdaptedToDo extends JsonAdaptedTasks {

    /**
     * Constructs a {@code JsonAdaptedToDo} with the given task details.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    @JsonCreator
    public JsonAdaptedToDo(@JsonProperty("description") String description,
                           @JsonProperty("isDone") String isDone) {
        super(description, isDone);

    }

    public JsonAdaptedToDo(ToDo source) {
        super(source.getDesc(), source.statusString());
    }

    @Override
    public Task toModelType() {
        Task t = null;
        if (isDone.equals("true")) {
            t = new ToDo(description, true);
        } else {
            t = new ToDo(description, false);
        }
        return t;
    }

}
