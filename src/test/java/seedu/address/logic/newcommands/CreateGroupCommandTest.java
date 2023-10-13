package seedu.address.logic.newcommands;

import org.junit.jupiter.api.Test;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.TaskList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class CreateGroupCommandTest {

    @Test
    public void constructor_nullRelativePathAndGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateGroupCommand(null, null));
    }

    @Test
    public void equals_sameInstance_success() throws InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Student> students = new HashMap<>();
        Name name = new Name("Group 1");
        GroupId id = new GroupId("grp-001");
        Group group = new Group(taskList, students, name, id);

        RelativePath relativePath = new RelativePath("~/grp-001");
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(relativePath, group);
        CreateGroupCommand duplicateCreateGroupCommand= new CreateGroupCommand(relativePath, group);
        assertEquals(createGroupCommand, duplicateCreateGroupCommand);
    }

    @Test
    public void testOutputString() throws InvalidPathException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Map<Id, Student> students = new HashMap<>();
        Name name = new Name("Group 1");
        GroupId id = new GroupId("grp-001");
        Group group = new Group(taskList, students, name, id);

        RelativePath relativePath = new RelativePath("~/grp-001");
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(relativePath, group);
        String expected = "seedu.address.logic.newcommands.CreateGroupCommand{toCreateGroup=seedu.address.model.profbook.Group{Group Id=grp-001, name=Group 1, Students=}}";
        assertEquals(expected, createGroupCommand.toString());
    }
}
