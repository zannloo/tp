package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.taskmanager.Deadline;
import seedu.address.model.taskmanager.Task;

/**
 * A class to adapt a Deadline object into a format suitable for JSON storage.
 */
public class JsonAdaptedDeadline extends JsonAdaptedTasks {

    private String date;

    /**
     * Constructs a {@code JsonAdaptedDeadline} with the given Deadline details.
     */
    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("description") String description,
                               @JsonProperty("isDone") String isDone, @JsonProperty("date") String date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Converts a given Deadline into this class for Jackson use.
     *
     * @param source The source Deadline object.
     */
    public JsonAdaptedDeadline(Deadline source) {
        super(source.getDesc(), source.statusString());
        this.date = source.getDeadline();
    }

    @Override
    public Task toModelType() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy hh:mm a");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, formatter);
        Task t = new Deadline(description, parsedDateTime);
        if (isDone == "true") {
            t.mark();
        }
        return t;
    }
}
