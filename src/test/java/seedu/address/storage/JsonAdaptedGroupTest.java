package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.profbook.Group;
import seedu.address.testutil.GroupBuilder;



public class JsonAdaptedGroupTest {

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().build();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());
    }

    @Test
    public void toModelType_emptyTask_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().build();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());

    }
    @Test
    public void toModelType_emptyStudent_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().build();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());

    }
}
