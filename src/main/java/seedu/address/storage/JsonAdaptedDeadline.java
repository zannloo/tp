package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class JsonAdaptedDeadline extends JsonAdaptedTasks {

    private String type;
    private String date;

    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty String type, @JsonProperty("description") String description,
                               @JsonProperty("isDone") String isDone, @JsonProperty("date") String date) {
        super(description,isDone);
        this.type = type;
        this.date = date;
    }

    @Override
    public Task toModelType() {
        //change localdatetime later ask nereus for implementation
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, formatter);
        Task t = new Deadline(description, parsedDateTime);
        if(Objects.equals(isDone, "true")) {
            t.mark();
        }
        return t;
    }
}
