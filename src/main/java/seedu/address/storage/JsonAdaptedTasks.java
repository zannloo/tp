package seedu.address.storage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedDeadline.class, name = "Deadline"),
    @JsonSubTypes.Type(value = JsonAdaptedToDo.class, name = "ToDo")
})
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

    abstract Task toModelType();

}
