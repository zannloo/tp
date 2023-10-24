package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.profbook.Group;
import seedu.address.testutil.GroupBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonAdaptedGroupTest {

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().build();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());
    }

    @Test
    public void toModelType_EmptyTask_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().buildEmptyTask();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());

    }
    @Test
    public void toModelType_EmptyStudent_returnsGroup() throws Exception {
        Group grp = new GroupBuilder().buildWithEmptyStudent();
        JsonAdaptedGroup group = new JsonAdaptedGroup(grp);
        assertEquals(grp, group.toModelType());

    }
}
