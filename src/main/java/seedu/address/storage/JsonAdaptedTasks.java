package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
public abstract class JsonAdaptedTasks {
    protected String description;

    protected String isDone;

    /**
     * Constructs a {@code JsonAdaptedTasks} with the given task details.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    @JsonCreator
    public JsonAdaptedTasks(@JsonProperty("description") String description, @JsonProperty("isDone") String isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     *
     * @param source The source Task object.
     */
    public JsonAdaptedTasks(Task source) {
        this.description = source.getDesc();
        this.isDone = source.statusString();
    }



    abstract Task toModelType();

}
