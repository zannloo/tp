//@@author mingyuanc
package seedu.address.model;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.ui.Displayable;

/**
 * The API of the Model component.
 */
public interface Model {
    //=========== UserPrefs ==================================================================================
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    ReadOnlyUserPrefs getUserPrefs();

    GuiSettings getGuiSettings();

    void setGuiSettings(GuiSettings guiSettings);

    Path getProfBookFilePath();

    void setProfBookFilePath(Path addressBookFilePath);

    //=========== ProfBook Model ================================================================================
    /**
     * Sets root of ProfBook to the given root and set the current path to root path.
     */
    void setRoot(Root root);

    /**
     * Returns current directory.
     */
    AbsolutePath getCurrPath();

    /**
     * Returns Root.
     */
    Root getRoot();

    /**
     * Returns current display path.
     */
    AbsolutePath getDisplayPath();

    /**
     * Returns true if display panel is showing task list.
     */
    boolean isShowTaskList();


    /**
     * Returns true if current path has task list.
     */
    boolean hasTaskListInCurrentPath();

    /**
     * Returns true if current path has children list.
     */
    boolean hasChildrenListInCurrentPath();

    /**
     * Returns true if there is group in ProfBook with the given id.
     */
    boolean hasGroupWithId(GroupId id);

    /**
     * Returns true if there is student in ProfBook with the given id.
     */
    boolean hasStudentWithId(StudentId id);

    /**
     * Returns {@code Group} with given {@code id}.
     * {@code id} must exist in ProfBook.
     */
    Group getGroupWithId(GroupId id);

    /**
     * Returns {@code Student} with given {@code id}.
     * {@code id} must exist in ProfBook.
     */
    Student getStudentWithId(StudentId id);

    /**
     * Returns true if group in given path exists in the ProfBook.
     * {@code path} must be path with group information.
     * i.e. Group directory or Student Directory.
     */
    boolean hasGroup(AbsolutePath path);

    /**
     * Returns true if student exists in the ProfBook.
     * {@code path} must be student path.
     */
    boolean hasStudent(AbsolutePath path);

    /**
     * Returns true if given path exists in the ProfBook.
     */
    boolean hasPath(AbsolutePath path);

    /**
     * Changes directory to destination path
     * {@code path} must exist in ProfBook and is not student path.
     */
    void changeDirectory(AbsolutePath path);

    //=========== Display Panel Settings =============================================================

    /**
     * Return the current list shown on display panel.
     */
    ObservableList<Displayable> getDisplayList();

    /**
     * Update list with latest data according to
     * current display path and display content.
     */
    void updateList();

    /**
     * Set the path to be shown on display panel.
     * {@code path} must exist in ProfBook.
     */
    void setDisplayPath(AbsolutePath path);

    /**
     * Return true if current display path has task list.
     */
    boolean hasTaskListInDisplayPath();

    /**
     * Return true if current display path has children list.
     */
    boolean hasChildrenListInDisplayPath();

    /**
     * Displays a children list on the display panel.
     * This method should be called after checking that the current display path contains children list
     * by using the {@link hasChildrenListInDisplayPath} method.
     */
    void showChildrenList();

    /**
     * Displays a task list on the display panel.
     * This method should be called after checking that the current display path contains task list
     * by using the {@link hasTaskListInDisplayPath} method.
     */
    void showTaskList();

    //=========== Model Management Operation =============================================================

    /**
     * Creates a ChildOperation class that performs operation on root.
     */
    ChildOperation<Group> rootChildOperation();

    /**
     * Creates a ChildOperation that performs operation on the specified group.
     * {@code path} must be a directory with group information and exist in the ProfBook.
     */
    ChildOperation<Student> groupChildOperation(AbsolutePath path);

    /**
     * Creates a TaskOperation that performs task operation on the specified task list.
     * {@code path} must be a directory with task list and exist in the ProfBook.
     */
    TaskOperation taskOperation(AbsolutePath path);

    //=========== Helper Method =============================================================

    /**
     * Return true if given path has task list.
     */
    boolean hasTaskListInPath(AbsolutePath path);

    /**
     * Return true if given path has children list.
     */
    boolean hasChildrenListInPath(AbsolutePath path);
}
