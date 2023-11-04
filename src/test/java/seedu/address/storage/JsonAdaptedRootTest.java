package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.profbook.Root;
import seedu.address.testutil.RootBuilder;
import seedu.address.testutil.TypicalGroups;

public class JsonAdaptedRootTest {

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        Root root = new RootBuilder().withGroup(TypicalGroups.GROUP_ONE).build();
        System.out.println(root);
        JsonAdaptedRoot jsonRoot = new JsonAdaptedRoot(root);
        assertEquals(root, jsonRoot.toModelType());
    }
}
