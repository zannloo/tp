package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

public class JsonAdaptedToDoTest {

    public static final String VALID_DESCRIPTION = "Buy groceries";

    @Test
    public void toModelType_validToDoWithIsDoneTrue_returnsToDoWithIsDoneTrue() {
        String isDone = "true";

        JsonAdaptedToDo jsonAdaptedToDo = new JsonAdaptedToDo(VALID_DESCRIPTION, isDone);
        Task modelToDo = jsonAdaptedToDo.toModelType();

        assertTrue(modelToDo instanceof ToDo);
        ToDo toDo = (ToDo) modelToDo;

        assertEquals(VALID_DESCRIPTION, toDo.getDesc());
        assertTrue(toDo.getStatus());
    }

    @Test
    public void toModelType_validToDoWithIsDoneFalse_returnsToDoWithIsDoneFalse() {
        String isDone = "false";

        JsonAdaptedToDo jsonAdaptedToDo = new JsonAdaptedToDo(VALID_DESCRIPTION, isDone);
        Task modelToDo = jsonAdaptedToDo.toModelType();

        assertTrue(modelToDo instanceof ToDo);
        ToDo toDo = (ToDo) modelToDo;

        assertEquals(VALID_DESCRIPTION, toDo.getDesc());
        assertFalse(toDo.getStatus());
    }
}
