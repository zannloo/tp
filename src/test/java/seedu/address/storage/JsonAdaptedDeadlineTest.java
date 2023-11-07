package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

public class JsonAdaptedDeadlineTest {

    public static final String VALID_DESCRIPTION = "Buy groceries";

    @Test
    public void toModelType_validDeadlineWithIsDoneTrue_returnsDeadlineWithIsDoneTrue() {
        String isDone = "true";
        String time = "Monday, October 24, 2022 03:45 PM";

        JsonAdaptedDeadline jsonAdaptedDeadline = new JsonAdaptedDeadline(VALID_DESCRIPTION, isDone, time);
        Task modelToDo = jsonAdaptedDeadline.toModelType();

        assertTrue(modelToDo instanceof Deadline);
        Deadline deadline = (Deadline) modelToDo;

        assertEquals(VALID_DESCRIPTION, deadline.getDesc());
        assertTrue(deadline.getStatus());
    }

}
