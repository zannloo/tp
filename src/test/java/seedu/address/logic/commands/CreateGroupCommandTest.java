package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CreateGroupCommand.MESSAGE_DUPLICATE_GROUP_ID;
import static seedu.address.logic.commands.CreateGroupCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.testutil.RootBuilder;
import seedu.address.testutil.TypicalGroups;

public class CreateGroupCommandTest {

    private Model model;

    private Model expectedModel;

    private AbsolutePath rootPath = CommandTestUtil.getValidRootAbsolutePath();

    private Group toBeAdded = TypicalGroups.GROUP_TWO;

    @BeforeEach
    public void setup() {
        Root root = new RootBuilder().withGroup(TypicalGroups.GROUP_ONE).build();
        model = new ModelManager(rootPath, new Root(root), new UserPrefs());
        expectedModel = new ModelManager(rootPath, new Root(root), new UserPrefs());
    }

    @Test
    public void constructor_nullRelativePathAndGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateGroupCommand(null, null));
    }

    @Test
    public void constructor_nullRelativePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateGroupCommand(null, toBeAdded));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() throws InvalidPathException {
        RelativePath groupTwo = new RelativePath(toBeAdded.getId().toString());
        AbsolutePath groupTwoAbsolutePath = rootPath.resolve(groupTwo);

        assertThrows(NullPointerException.class, () -> new CreateGroupCommand(groupTwoAbsolutePath, null));
    }

    @Test
    public void execute_createGroup_success() throws InvalidPathException {
        RelativePath groupTwo = new RelativePath(toBeAdded.getId().toString());
        AbsolutePath groupTwoAbsolutePath = rootPath.resolve(groupTwo);

        ChildOperation<Group> operation = expectedModel.rootChildOperation();
        operation.addChild(toBeAdded.getId(), toBeAdded);
        expectedModel.updateList();

        CreateGroupCommand command = new CreateGroupCommand(groupTwoAbsolutePath, toBeAdded);
        String expectedMessage = String.format(MESSAGE_SUCCESS, Messages.format(toBeAdded));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwCommandException() throws InvalidPathException {
        RelativePath groupTwo = new RelativePath(toBeAdded.getId().toString());
        AbsolutePath groupTwoAbsolutePath = rootPath.resolve(groupTwo);

        CreateGroupCommand command = new CreateGroupCommand(groupTwoAbsolutePath, toBeAdded);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_duplicateGroup_throwCommandException() throws InvalidPathException {
        RelativePath groupTwo = new RelativePath(toBeAdded.getId().toString());
        AbsolutePath groupTwoAbsolutePath = rootPath.resolve(groupTwo);

        ChildOperation<Group> operation = model.rootChildOperation();
        operation.addChild(toBeAdded.getId(), toBeAdded);
        model.updateList();

        CreateGroupCommand command = new CreateGroupCommand(groupTwoAbsolutePath, toBeAdded);
        String expectedMessage = MESSAGE_DUPLICATE_GROUP_ID;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        CreateGroupCommand createGroupCommand1 = new CreateGroupCommand(rootPath, TypicalGroups.GROUP_ONE);
        CreateGroupCommand createGroupCommand2 = new CreateGroupCommand(rootPath, TypicalGroups.GROUP_TWO);

        // same object -> returns true
        assertEquals(createGroupCommand1, createGroupCommand1);

        // same values -> returns true
        CreateGroupCommand createGroupCommand1Copy = new CreateGroupCommand(rootPath, TypicalGroups.GROUP_ONE);
        assertEquals(createGroupCommand1, createGroupCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, createGroupCommand1);

        // null -> returns false
        assertNotEquals(null, createGroupCommand1);

        // different values
        assertNotEquals(createGroupCommand1, createGroupCommand2);
    }

    @Test
    public void toString_successfullyCreateGroup_returnExpectedString() {
        CreateGroupCommand createGroupCommand = new CreateGroupCommand(rootPath, TypicalGroups.GROUP_TWO);
        String expected = CreateGroupCommand.class.getCanonicalName()
                + "{toCreateGroup=" + toBeAdded + "}";
        assertEquals(expected, createGroupCommand.toString());
    }
}
