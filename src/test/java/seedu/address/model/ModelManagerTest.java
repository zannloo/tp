//@@author mingyuanc
package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalGroups.GROUP_TWO;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_FIONA;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_TWO;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_GROUP_ONE;
import static seedu.address.testutil.TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.LEO;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Root;
import seedu.address.testutil.PathBuilder;
import seedu.address.testutil.TypicalRoots;

public class ModelManagerTest {

    private ModelManager model;

    @BeforeEach
    public void setUp() {
        AbsolutePath currPath = ROOT_PATH;
        Root root = TypicalRoots.PROFBOOK_WITH_GROUP_ONE;
        UserPrefs userPrefs = new UserPrefs();
        model = new ModelManager(currPath, root, userPrefs);
    }

    @Test
    public void constructor_withCurrentPathAndRootAndUserPrefs_validParameters() {
        AbsolutePath currPath = ROOT_PATH;
        Root root = TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
        UserPrefs userPrefs = new UserPrefs();

        ModelManager model = new ModelManager(currPath, root, userPrefs);

        // Perform assertions to verify that the object is created correctly
        assertEquals(currPath, model.getCurrPath());
        assertEquals(root, model.getRoot());
        assertEquals(userPrefs, model.getUserPrefs());
        assertEquals(currPath, model.getDisplayPath());
        assertFalse(model.isShowTaskList());
    }

    @Test
    public void constructor_withAllFields_validParameters() throws InvalidPathException {
        // Create a new model manager with valid parameters
        AbsolutePath currPath = ROOT_PATH;
        Root root = TypicalRoots.PROFBOOK_WITH_TWO_GROUPS;
        UserPrefs userPrefs = new UserPrefs();
        AbsolutePath displayPath = new AbsolutePath("~/grp-001");
        boolean showTaskList = true;

        ModelManager model = new ModelManager(currPath, root, userPrefs, displayPath, showTaskList);

        assertEquals(currPath, model.getCurrPath());
        assertEquals(root, model.getRoot());
        assertEquals(userPrefs, model.getUserPrefs());
        assertEquals(displayPath, model.getDisplayPath());
        assertTrue(model.isShowTaskList());
    }

    @Test
    public void constructor_withEmptyData() {
        // Create a new model manager with empty data
        ModelManager model = new ModelManager();

        // Perform assertions to verify that the object is created correctly with default values.
        assertEquals(ROOT_PATH, model.getCurrPath());
        assertEquals(ROOT_PATH, model.getDisplayPath());
        assertEquals(new UserPrefs(), model.getUserPrefs());
        assertEquals(ROOT_PATH, model.getDisplayPath());
        assertFalse(model.isShowTaskList());
    }

    //=========== UserPrefs ==================================================================================
    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        model.setUserPrefs(userPrefs);
        assertEquals(userPrefs, model.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, model.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        model.setGuiSettings(guiSettings);
        assertEquals(guiSettings, model.getGuiSettings());
    }

    @Test
    public void setProfBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setProfBookFilePath(null));
    }

    @Test
    public void setProfBookFilePath_validPath_setsProfBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        model.setProfBookFilePath(path);
        assertEquals(path, model.getProfBookFilePath());
    }

    //=========== ProfBook Model ================================================================================
    @Test
    public void setRoot_nullRoot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setRoot(null));
    }

    @Test
    public void setRoot_validRoot_throwsNullPointerException() {
        Root root = new Root();
        model.setRoot(root);

        assertEquals(root, model.getRoot());
        assertEquals(ROOT_PATH, model.getCurrPath());
        assertEquals(ROOT_PATH, model.getDisplayPath());
        assertFalse(model.isShowTaskList());
    }

    @Test
    public void hasTaskListInCurrentPath_rootPath_returnsFalse() {
        model.changeDirectory(ROOT_PATH);
        assertFalse(model.hasTaskListInCurrentPath());
    }

    @Test
    public void hasTaskListInCurrentPath_groupPath_returnsTrue() throws InvalidPathException {
        model.changeDirectory(PATH_TO_GROUP_ONE);
        assertTrue(model.hasTaskListInCurrentPath());
    }

    @Test
    public void hasChildrenListInCurrentPath_rootPath_returnsTrue() throws InvalidPathException {
        model.changeDirectory(ROOT_PATH);
        assertTrue(model.hasChildrenListInCurrentPath());
    }

    @Test
    public void hasChildrenListInCurrentPath_groupPath_returnsTrue() throws InvalidPathException {
        model.changeDirectory(PATH_TO_GROUP_ONE);
        assertTrue(model.hasChildrenListInCurrentPath());
    }

    @Test
    public void hasGroupWithId_existingGroupId_returnsTrue() {
        GroupId existingGroupId = GROUP_ONE.getId();
        assertTrue(model.hasGroupWithId(existingGroupId));
    }

    @Test
    public void hasGroupWithId_nonExistentGroupId_returnsFalse() {
        GroupId nonExistentGroupId = GROUP_TWO.getId();
        assertFalse(model.hasGroupWithId(nonExistentGroupId));
    }

    @Test
    public void hasStudentWithId_existingStudentId_returnsTrue() {
        StudentId existingStudentId = ALICE.getId();
        assertTrue(model.hasStudentWithId(existingStudentId));
    }

    @Test
    public void hasStudentWithId_nonExistentStudentId_returnsFalse() {
        StudentId nonExistentStudentId = LEO.getId();
        assertFalse(model.hasStudentWithId(nonExistentStudentId));
    }

    @Test
    public void getGroupWithId_existingGroupId_returnsGroup() {
        GroupId existingGroupId = GROUP_ONE.getId();
        assertEquals(GROUP_ONE, model.getGroupWithId(existingGroupId));
    }

    @Test
    public void getGroupWithId_nonExistentGroupId_throwsIllegalArgumentException() {
        GroupId nonExistentGroupId = GROUP_TWO.getId();
        assertThrows(IllegalArgumentException.class, () -> model.getGroupWithId(nonExistentGroupId));
    }

    @Test
    public void getStudentWithId_existingStudentId_returnsGroup() {
        StudentId existingStudentId = ALICE.getId();
        assertEquals(ALICE, model.getStudentWithId(existingStudentId));
    }

    @Test
    public void getStudentWithId_nonExistentStudentId_throwsIllegalArgumentException() {
        StudentId nonExistentStudentId = LEO.getId();
        assertThrows(IllegalArgumentException.class, () -> model.getStudentWithId(nonExistentStudentId));
    }

    @Test
    public void hasGroup_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasGroup(null));
    }

    @Test
    public void hasGroup_rootPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.hasGroup(ROOT_PATH));
    }

    @Test
    public void hasGroup_existingValidPath_returnsTrue() {
        assertTrue(model.hasGroup(PATH_TO_ALICE));
        assertTrue(model.hasGroup(PATH_TO_GROUP_ONE));
    }

    @Test
    public void hasGroup_validPathWithExistingGroupNonExistentStudent_returnsTrue() {
        AbsolutePath path = new PathBuilder().withGroup(GROUP_ONE).withStudent(LEO).build();
        assertTrue(model.hasGroup(path));
    }

    @Test
    public void hasGroup_nonExistentValidPath_returnsTrue() {
        assertFalse(model.hasGroup(PATH_TO_FIONA));
        assertFalse(model.hasGroup(PATH_TO_GROUP_TWO));
    }

    @Test
    public void hasStudent_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasStudent(null));
    }

    @Test
    public void hasStudent_invalidPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.hasStudent(ROOT_PATH));
        assertThrows(IllegalArgumentException.class, () -> model.hasStudent(PATH_TO_GROUP_ONE));
    }

    @Test
    public void hasStudent_validPathWithNonExistentGroupNonExistentStudent_returnsFalse() {
        assertFalse(model.hasStudent(PATH_TO_FIONA));
    }

    @Test
    public void hasStudent_validPathWithExistingGroupNonExistentStudent_returnsFalse() {
        AbsolutePath path = new PathBuilder().withGroup(GROUP_ONE).withStudent(LEO).build();
        assertFalse(model.hasStudent(path));
    }

    @Test
    public void hasStudent_validPathWithExistingGroupExistingStudent_returnsTrue() {
        assertTrue(model.hasStudent(PATH_TO_ALICE));
    }

    @Test
    public void hasPath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasPath(null));
    }

    @Test
    public void hasPath_validExistingPath_returnsTrue() {
        assertTrue(model.hasPath(PATH_TO_ALICE));
        assertTrue(model.hasPath(PATH_TO_GROUP_ONE));
    }

    @Test
    public void hasPath_validNonExistentPath_returnsTrue() {
        assertFalse(model.hasPath(PATH_TO_FIONA));
        assertFalse(model.hasPath(PATH_TO_GROUP_TWO));
    }

    @Test
    public void changeDirectory_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.changeDirectory(null));
    }

    @Test
    public void changeDirectory_nonExistentPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.changeDirectory(PATH_TO_GROUP_TWO));
    }

    @Test
    public void changeDirectory_studentPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.changeDirectory(PATH_TO_FIONA));
        assertThrows(IllegalArgumentException.class, () -> model.changeDirectory(PATH_TO_ALICE));
    }

    @Test
    public void changeDirectory_validExistingPath_changesDirectory() {
        // cd to group one
        model.changeDirectory(PATH_TO_GROUP_ONE);
        assertEquals(PATH_TO_GROUP_ONE, model.getCurrPath());
        assertEquals(PATH_TO_GROUP_ONE, model.getDisplayPath());
        assertFalse(model.isShowTaskList());

        // cd to root
        model.changeDirectory(ROOT_PATH);
        assertEquals(ROOT_PATH, model.getCurrPath());
        assertEquals(ROOT_PATH, model.getDisplayPath());
        assertFalse(model.isShowTaskList());
    }

    //=========== Display Panel Settings =============================================================

    @Test
    public void setDisplayPath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setDisplayPath(null));
    }

    @Test
    public void setDisplayPath_nonExistentPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.setDisplayPath(PATH_TO_GROUP_TWO));
        assertThrows(IllegalArgumentException.class, () -> model.setDisplayPath(PATH_TO_FIONA));
    }

    @Test
    public void setDisplayPath_existingPath_setsDisplayPath() {
        model.setDisplayPath(ROOT_PATH);
        model.setDisplayPath(PATH_TO_GROUP_ONE);
        model.setDisplayPath(PATH_TO_ALICE);
    }

    @Test
    public void hasTaskListInDisplayPath_rootPath_returnsFalse() {
        model.setDisplayPath(ROOT_PATH);
        assertFalse(model.hasTaskListInDisplayPath());
    }

    @Test
    public void hasTaskListInDisplayPath_studentPath_returnsTrue() {
        model.setDisplayPath(PATH_TO_ALICE);
        assertTrue(model.hasTaskListInDisplayPath());
    }

    @Test
    public void hasTaskListInDisplayPath_groupPath_returnsTrue() {
        model.setDisplayPath(PATH_TO_GROUP_ONE);
        assertTrue(model.hasTaskListInDisplayPath());
    }

    @Test
    public void hasChildrenListInDisplayPath_rootPath_returnsTrue() {
        model.setDisplayPath(ROOT_PATH);
        assertTrue(model.hasChildrenListInDisplayPath());
    }

    @Test
    public void hasChildrenListInDisplayPath_studentPath_returnsFalse() {
        model.setDisplayPath(PATH_TO_ALICE);
        assertFalse(model.hasChildrenListInDisplayPath());
    }

    @Test
    public void hasChildrenListInDisplayPath_groupPath_returnsTrue() {
        model.setDisplayPath(PATH_TO_GROUP_ONE);
        assertTrue(model.hasChildrenListInDisplayPath());
    }

    @Test
    public void showChildrenList_rootPath_showsChildrenList() {
        model.setDisplayPath(ROOT_PATH);
        model.showChildrenList();
        assertFalse(model.isShowTaskList());
    }

    @Test
    public void showChildrenList_groupPath_showsChildrenList() {
        model.setDisplayPath(PATH_TO_GROUP_ONE);
        model.showChildrenList();
        assertFalse(model.isShowTaskList());
    }

    @Test
    public void showChildrenList_studentPath_throwsIllegalArgumentException() {
        model.setDisplayPath(PATH_TO_ALICE);
        assertThrows(IllegalArgumentException.class, () -> model.showChildrenList());
    }

    @Test
    public void showTaskList_rootPath_throwsIllegalArgumentException() {
        model.setDisplayPath(ROOT_PATH);
        assertThrows(IllegalArgumentException.class, () -> model.showTaskList());
    }

    @Test
    public void showTaskList_groupPath_showsTaskList() {
        model.setDisplayPath(PATH_TO_GROUP_ONE);
        model.showTaskList();
        assertTrue(model.isShowTaskList());
    }

    @Test
    public void showTaskList_studentPath_showsTaskList() {
        model.setDisplayPath(PATH_TO_ALICE);
        model.showTaskList();
        assertTrue(model.isShowTaskList());
    }

    //=========== Model Management Operation =============================================================

    @Test
    public void groupChildOperation_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.groupChildOperation(null));
    }

    @Test
    public void groupChildOperation_rootPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.groupChildOperation(ROOT_PATH));
    }

    @Test
    public void groupChildOperation_nonExistentValidPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.groupChildOperation(PATH_TO_GROUP_TWO));
        assertThrows(IllegalArgumentException.class, () -> model.groupChildOperation(PATH_TO_FIONA));
    }

    @Test
    public void groupChildOperation_existingValidPath_returnsGroupChildOperation() {
        assertEquals(new ChildOperation<>(GROUP_ONE), model.groupChildOperation(PATH_TO_GROUP_ONE));
        assertEquals(new ChildOperation<>(GROUP_ONE), model.groupChildOperation(PATH_TO_ALICE));
    }

    @Test
    public void taskOperation_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.taskOperation(null));
    }

    @Test
    public void taskOperation_rootPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.taskOperation(ROOT_PATH));
    }

    @Test
    public void taskOperation_nonExistentValidPath_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> model.taskOperation(PATH_TO_GROUP_TWO));
    }

    @Test
    public void taskOperation_existingValidPath_returnsTaskOperation() {
        assertEquals(new TaskOperation(GROUP_ONE), model.taskOperation(PATH_TO_GROUP_ONE));
        assertEquals(new TaskOperation(ALICE), model.taskOperation(PATH_TO_ALICE));
    }

    //=========== Helper Method =============================================================
    @Test
    public void hasTaskListInPath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasTaskListInPath(null));
    }

    @Test
    public void hasTaskLIstInPath_groupPathOrStudentPath_returnsTrue() {
        assertTrue(model.hasTaskListInPath(PATH_TO_GROUP_TWO));
        assertTrue(model.hasTaskListInPath(PATH_TO_FIONA));
    }

    @Test
    public void hasTaskLIstInPath_rootPath_returnsFalse() {
        assertFalse(model.hasTaskListInPath(ROOT_PATH));
    }

    @Test
    public void hasChildrenListInPath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasChildrenListInPath(null));
    }

    @Test
    public void hasChildrenListInPath_groupPathOrRootPath_returnsTrue() {
        assertTrue(model.hasChildrenListInPath(PATH_TO_GROUP_TWO));
        assertTrue(model.hasChildrenListInPath(ROOT_PATH));
    }

    @Test
    public void hasChildrenListInPath_studentPath_returnsFalse() {
        assertFalse(model.hasChildrenListInPath(PATH_TO_FIONA));
    }

    @Test
    public void equalsMethod() {
        Model model1 = new ModelManager(ROOT_PATH, PROFBOOK_WITH_GROUP_ONE, new UserPrefs());
        Model model1Copy = new ModelManager(ROOT_PATH, PROFBOOK_WITH_GROUP_ONE, new UserPrefs());
        Model model2 = new ModelManager(ROOT_PATH, PROFBOOK_WITH_TWO_GROUPS, new UserPrefs());

        // same object -> returns true
        assertEquals(model1, model1);

        // same values -> returns true
        assertEquals(model1, model1Copy);

        // different types -> returns false
        assertNotEquals(PROFBOOK_WITH_GROUP_ONE, model1);

        // null -> returns false
        assertNotEquals(null, model1);

        // different values
        assertNotEquals(model1, model2);
    }

    @Test
    public void toStringMethod() {
        String expected = ModelManager.class.getCanonicalName()
                + "{showTaskList=" + model.isShowTaskList() + ", Current Path=" + model.getCurrPath()
                + ", Display List=" + model.getDisplayList() + ", Display Path=" + model.getDisplayPath()
                + ", root=" + model.getRoot() + ", userPrefs=" + model.getUserPrefs() + "}";
        assertEquals(expected, model.toString());
    }

}
