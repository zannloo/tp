package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * A class to adapt a Deadline object into a format suitable for JSON storage.
 */
@JsonTypeName("Deadline")
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
        Task t = null;
        if (isDone.equals("true")) {
            t = new Deadline(description, parsedDateTime, true);
        } else {
            t = new Deadline(description, parsedDateTime);
        }
        return t;
    }
}
