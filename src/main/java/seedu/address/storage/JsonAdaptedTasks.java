package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import seedu.address.model.taskmanager.TaskList;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
public class JsonAdaptedTasks  {
    private String description;

    private String isDone;

    @JsonCreator
    public JsonAdaptedTasks(String description, String isDone) {
        this.description = description;
        this.isDone = isDone;
    }

}
