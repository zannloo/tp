package seedu.address.model.statemanager;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.ChildrenManager;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.Root;

/**
 * Current state of ProfBook.
 */
public class State {
    private AbsolutePath currentPath;
    private final Root root;
    private ChildrenManager<? extends IChildElement> currManager;
    private ObservableList<IChildElement> filteredList = FXCollections.observableArrayList();
    private final UserPrefs userPrefs;

    /**
     * Construct a new state with current path and the root object.
     */
    public State(AbsolutePath currentPath, Root root, ReadOnlyUserPrefs userPrefs) {
        this.currentPath = currentPath;
        this.root = root;
        this.currManager = getCurrManager(currentPath);
        updateFilteredList();
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
        updateFilteredList();
    }

    //=========== Filtered List Accessors =============================================================
    public ObservableList<? extends IChildElement> getFilteredList() {
        return this.filteredList;
    }

    /**
     * Reset filtered list with updated list.
     *
     */
    public void updateFilteredList() {
        List<? extends IChildElement> temp = new ArrayList<>(currManager.asUnmodifiableObservableList());
        this.filteredList.clear();
        this.filteredList.setAll(temp);
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
