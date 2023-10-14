package seedu.address.model.statemanager;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.ChildrenManager;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.Root;
import seedu.address.ui.Displayable;

/**
 * Current state of ProfBook.
 */
public class State {
    private static final Logger logger = LogsCenter.getLogger(State.class);
    private AbsolutePath currentPath;
    private final Root root;
    private ChildrenManager<? extends IChildElement> currManager;
    private ObservableList<Displayable> filteredList = FXCollections.observableArrayList();
    private final UserPrefs userPrefs;
    private boolean showTaskList = false;

    /**
     * Construct a new state with current path and the root object.
     */
    public State(AbsolutePath currentPath, Root root, ReadOnlyUserPrefs userPrefs) {
        this.currentPath = currentPath;
        this.root = root;
        this.currManager = getCurrManager(currentPath);
        showChildrenList();
        this.userPrefs = new UserPrefs(userPrefs);
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

    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== ProfBook ================================================================================

    public AbsolutePath getCurrPath() {
        return this.currentPath;
    }

    public Root getRoot() {
        return this.root;
    }

    public ChildrenManager<? extends IChildElement> getCurrManager(AbsolutePath path) {
        try {
            if (path.isGroupDirectory()) {
                return StateManager.getGroupFromPath(root, path);
            }
        } catch (UnsupportedPathOperationException e) {
            throw new IllegalArgumentException("Invalid Current Path: Must be children manager path!");
        }
        if (path.isRootDirectory()) {
            return root;
        }
        throw new IllegalArgumentException("Invalid Current Path: Must be children manager path!");
    }

    /**
     * Change directory to destination path
     */
    public void changeDirectory(AbsolutePath path) {
        this.currentPath = path;
        this.currManager = getCurrManager(path);
        updateList();
    }

    //=========== Filtered List Accessors =============================================================
    public ObservableList<Displayable> getFilteredList() {
        return this.filteredList;
    }

    /**
     * Update list with latest data
     */
    public void updateList() {
        List<? extends Displayable> temp;
        if (showTaskList) {
            temp = new ArrayList<>(currManager.getAllTask());
        } else {
            temp = new ArrayList<>(currManager.asUnmodifiableObservableList());
        }
        this.filteredList.clear();
        this.filteredList.setAll(temp);
    }

    /**
     * Show children list
     */
    public void showChildrenList() {
        showTaskList = false;
        updateList();
    }

    /**
     * Show task list
     */
    public void showTaskList() {
        showTaskList = true;
        updateList();
    }

    // public void updateFilteredPersonList(Predicate<? extends IChildElement> predicate) {
    //     requireNonNull(predicate);
    //     filteredPersons.setPredicate(predicate);
    // }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof State)) {
            return false;
        }

        State otherState = (State) other;
        return this.currManager.equals(otherState.currManager)
                && this.currentPath.equals(otherState.currentPath)
                && this.filteredList.equals(otherState.filteredList)
                && this.root.equals(otherState.root)
                && this.userPrefs.equals(otherState.userPrefs);
    }
}
