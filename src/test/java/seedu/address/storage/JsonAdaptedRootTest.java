package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.RootBuilder;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonAdaptedRootTest {

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        Root root = new RootBuilder().withGroup();
        System.out.println(root);
        JsonAdaptedRoot jsonRoot = new JsonAdaptedRoot(root);
        assertEquals(root, jsonRoot.toModelType());
    }
}
