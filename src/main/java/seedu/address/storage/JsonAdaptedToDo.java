package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.ToDo;
public class JsonAdaptedToDo extends JsonAdaptedTasks{

    @JsonCreator
    public JsonAdaptedToDo(@JsonProperty("description") String description,
                           @JsonProperty("isDone") String isDone) {
        super(description,isDone);

    }

    public JsonAdaptedToDo(ToDo source) {
        super(source.getDesc(), source.statusString());
    }

    @Override
    public Task toModelType() {
        Task t = new ToDo(description);
        if (isDone == "true") {
            t.mark();
        }
        return t;
    }

}
