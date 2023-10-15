package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
public abstract class JsonAdaptedTasks  {
    protected String description;

    protected String isDone;

    @JsonCreator
    public JsonAdaptedTasks(@JsonProperty("description") String description, @JsonProperty("isDone") String isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    abstract Task toModelType();

}
