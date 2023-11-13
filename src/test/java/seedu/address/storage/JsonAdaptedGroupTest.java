package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.model.profbook.Name.MESSAGE_CONSTRAINTS;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.GroupId;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.testutil.TypicalGroups;


public class JsonAdaptedGroupTest {

    private static final Group GROUP_ONE = TypicalGroups.GROUP_ONE;
    private static final Group GROUP_TWO = TypicalGroups.GROUP_TWO;

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(GROUP_ONE);
        assertEquals(GROUP_ONE, group.toModelType());
    }

    @Test
    public void toModelType_emptyTask_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(GROUP_TWO);
        assertEquals(GROUP_TWO, group.toModelType());

    }
    @Test
    public void toModelType_emptyStudent_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(GROUP_ONE);
        assertEquals(GROUP_ONE, group.toModelType());

    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGroup grp = new JsonAdaptedGroup(null, "grp-001",
                new ArrayList<JsonAdaptedStudent>(),
                new ArrayList<JsonAdaptedTasks>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        try {
            grp.toModelType();
            fail("Expected IllegalValueException to be thrown");
        } catch (IllegalValueException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGroup grp = new JsonAdaptedGroup("@Invalid!", "grp-001",
                new ArrayList<JsonAdaptedStudent>(),
                new ArrayList<JsonAdaptedTasks>());
        String expectedMessage = MESSAGE_CONSTRAINTS;
        try {
            grp.toModelType();
            fail("Expected IllegalValueException to be thrown");
        } catch (IllegalValueException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedGroup grp = new JsonAdaptedGroup("123", null, new ArrayList<JsonAdaptedStudent>(),
                new ArrayList<JsonAdaptedTasks>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupId.class.getSimpleName());
        try {
            grp.toModelType();
            fail("Expected IllegalValueException to be thrown");
        } catch (IllegalValueException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedGroup grp = new JsonAdaptedGroup("123", "001", new ArrayList<JsonAdaptedStudent>(),
                new ArrayList<JsonAdaptedTasks>());
        String expectedMessage = "Group Id should be in the format 'grp-XXX' where XXX is a 3-digit number, "
                + "and it should not be blank.";
        try {
            grp.toModelType();
            fail("Expected IllegalValueException to be thrown");
        } catch (IllegalValueException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
