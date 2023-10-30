package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.ui.Displayable;

/**
 * Represents the in-memory model of the ProfBook data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(Model.class);
    private static final String MESSAGE_INTERNAL_ERROR = "Internal error: %1$s";
    private final ObservableList<Displayable> displayList = FXCollections.observableArrayList();
    private final UserPrefs userPrefs;
    private Root root;
    private AbsolutePath currentPath;
    private boolean showTaskList = false;
    private AbsolutePath displayPath;

    /**
     * Construct a model manager with current path, root (ProfBook) and userPrefs.
     */
    public ModelManager(AbsolutePath currentPath, Root root, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(currentPath, root, userPrefs);
        this.currentPath = currentPath;
        this.displayPath = currentPath;
        this.root = new Root(root);
        this.userPrefs = new UserPrefs(userPrefs);
        updateList();
    }

    /**
     * Constructs a model manager with all fields.
     */
    public ModelManager(AbsolutePath currPath, Root root, ReadOnlyUserPrefs usePrefs,
            AbsolutePath displayPath, boolean showTaskList) {
        this(currPath, root, usePrefs);
        requireAllNonNull(displayPath, showTaskList);
        this.displayPath = displayPath;
        this.showTaskList = showTaskList;
        updateList();
    }

    /**
     * Constructs a new model manager with empty data.
     */
    public ModelManager() {
        this.currentPath = AbsolutePath.ROOT_PATH;
        this.displayPath = AbsolutePath.ROOT_PATH;
        this.root = new Root();
        this.userPrefs = new UserPrefs();
        updateList();
    }

    //=========== UserPrefs ==================================================================================
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    public Path getProfBookFilePath() {
        return userPrefs.getProfBookFilePath();
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== ProfBook Model ================================================================================
    @Override
    public void setRoot(Root root) {
        this.root = root;
        this.currentPath = AbsolutePath.ROOT_PATH;
        this.displayPath = AbsolutePath.ROOT_PATH;
        this.showTaskList = false;
        this.updateList();
    }

    @Override
    public AbsolutePath getCurrPath() {
        return this.currentPath;
    }

    @Override
    public AbsolutePath getDisplayPath() {
        return this.displayPath;
    }

    @Override
    public boolean isShowTaskList() {
        return this.showTaskList;
    }

    @Override
    public boolean hasTaskListInCurrentPath() {
        return hasTaskListInPath(currentPath);
    }

    @Override
    public boolean hasChildrenListInCurrentPath() {
        return hasChildrenListInPath(currentPath);
    }

    @Override
    public boolean hasGroupWithId(GroupId id) {
        return this.root.hasChild(id);
    }

    @Override
    public boolean hasStudentWithId(StudentId id) {
        for (Group group : this.root.getAllChildren()) {
            if (group.hasChild(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasGroup(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(!path.isRootDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "path must have group information"));
        GroupId grpId = path.getGroupId().get();
        return root.hasChild(grpId);
    }

    @Override
    public boolean hasStudent(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(path.isStudentDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "path must be student path"));

        if (!hasGroup(path)) {
            return false;
        }

        StudentId stuId = path.getStudentId().get();
        Group grp = getGroupFromPath(path);

        return grp.hasChild(stuId);
    }

    @Override
    public boolean hasPath(AbsolutePath path) {
        requireNonNull(path);
        if (path.isRootDirectory()) {
            return true;
        }

        GroupId grpId = path.getGroupId().get();

        if (path.isGroupDirectory()) {
            return root.hasChild(grpId);
        }

        Group grp = getGroupFromPath(path);
        StudentId stuId = path.getStudentId().get();

        return grp.hasChild(stuId);
    }

    @Override
    public void changeDirectory(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(hasPath(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must exist in ProfBook"));
        checkArgument(!path.isStudentDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "Student path is not navigable"));
        currentPath = path;
        displayPath = path;
        logger.fine("Change directory to " + currentPath);
        showChildrenList();
    }

    //=========== Display Panel Settings =============================================================
    @Override
    public ObservableList<Displayable> getDisplayList() {
        return displayList;
    }

    @Override
    public void updateList() {
        List<? extends Displayable> temp = new ArrayList<>();
        if (showTaskList) {
            TaskOperation taskOperation = taskOperation(displayPath);
            temp = new ArrayList<>(taskOperation.getAllTasks());
        } else if (displayPath.isRootDirectory()) {
            ChildOperation<Group> childOperation = rootChildOperation();
            temp = new ArrayList<>(childOperation.getAllChildren());
        } else if (displayPath.isGroupDirectory()) {
            ChildOperation<Student> childOperation = groupChildOperation(displayPath);
            temp = new ArrayList<>(childOperation.getAllChildren());
        }
        displayList.clear();
        displayList.setAll(temp);
    }

    @Override
    public void setDisplayPath(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(hasPath(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must exist in ProfBook"));
        displayPath = path;
    }

    @Override
    public boolean hasTaskListInDisplayPath() {
        return hasTaskListInPath(displayPath);
    }

    @Override
    public boolean hasChildrenListInDisplayPath() {
        return hasChildrenListInPath(displayPath);
    }

    @Override
    public void showChildrenList() {
        checkArgument(hasChildrenListInDisplayPath(),
                String.format(MESSAGE_INTERNAL_ERROR, "Current display path must have children list"));
        showTaskList = false;
        updateList();
    }

    @Override
    public void showTaskList() {
        checkArgument(hasTaskListInDisplayPath(),
                String.format(MESSAGE_INTERNAL_ERROR, "Current display path must have task list: " + displayPath));
        showTaskList = true;
        updateList();
    }

    //=========== Model Management Operation =============================================================
    @Override
    public ChildOperation<Group> rootChildOperation() {
        return new ChildOperation<>(root);
    }

    @Override
    public ChildOperation<Student> groupChildOperation(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(path.isGroupDirectory() || path.isStudentDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must have group information"));
        checkArgument(hasGroup(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Group must exist in ProfBook"));
        return new ChildOperation<>(getGroupFromPath(path).getChildrenManger());
    }

    @Override
    public TaskOperation taskOperation(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(hasTaskListInPath(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must have task list"));
        checkArgument(hasPath(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must exist in ProfBook"));

        if (path.isGroupDirectory()) {
            return new TaskOperation(getGroupFromPath(path).getTaskListManager());
        }

        return new TaskOperation(getStudentFromPath(path));
    }

    //=========== Helper Method =============================================================
    @Override
    public boolean hasTaskListInPath(AbsolutePath path) {
        requireNonNull(path);
        return path.isGroupDirectory() || path.isStudentDirectory();
    }

    @Override
    public boolean hasChildrenListInPath(AbsolutePath path) {
        requireNonNull(path);
        return path.isRootDirectory() || path.isGroupDirectory();
    }

    /**
     * Return the group of the given path.
     * {@code path} must has a valid group info
     * i.e group exists in ProfBook.
     */
    private Group getGroupFromPath(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(path.isGroupDirectory() || path.isStudentDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must have group info"));
        checkArgument(hasGroup(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Group must exist in ProfBook"));

        GroupId grpId = path.getGroupId().get();

        return root.getChild(grpId);
    }

    /**
     * Return the group of the given path.
     * {@code path} must be student path that exists in ProfBook.
     */
    private Student getStudentFromPath(AbsolutePath path) {
        requireNonNull(path);
        checkArgument(path.isStudentDirectory(),
                String.format(MESSAGE_INTERNAL_ERROR, "Path must be student directory"));
        checkArgument(hasStudent(path),
                String.format(MESSAGE_INTERNAL_ERROR, "Student must exist in ProfBook"));

        GroupId grpId = path.getGroupId().get();
        StudentId stuId = path.getStudentId().get();

        return root.getChild(grpId).getChild(stuId);
    }

    /**
     * Return the Root of addressbook.
     */
    @Override
    public Root getRoot() {
        return this.root;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherStateManager = (ModelManager) other;
        return this.showTaskList == otherStateManager.showTaskList
                && this.currentPath.equals(otherStateManager.currentPath)
                && this.displayList.equals(otherStateManager.displayList)
                && this.displayPath.equals(otherStateManager.displayPath)
                && this.root.equals(otherStateManager.root)
                && this.userPrefs.equals(otherStateManager.userPrefs);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("showTaskList", showTaskList)
                .add("Current Path", currentPath)
                .add("Display List", displayList)
                .add("Display Path", displayPath)
                .add("root", root)
                .add("userPrefs", userPrefs)
                .toString();
    }

}
