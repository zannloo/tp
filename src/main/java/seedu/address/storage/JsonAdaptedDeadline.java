package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;


public class JsonAdaptedDeadline extends JsonAdaptedTasks {

    private String type;
    private String date;

    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty String type, @JsonProperty("description") String description,
                               @JsonProperty("isDone") String isDone, @JsonProperty("date") String date) {
        super(description, isDone);
        this.type = type;
        this.date = date;
    }

    @Override
    public Task toModelType() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, formatter);
        Task t = new Deadline(description, parsedDateTime);
        if (isDone == "true") {
            t.mark();
        }
        return t;
    }
}
