package seedu.address.model.statemanager;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

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
    private final AbsolutePath currentPath;
    private final Root root;
    private final ChildrenManager<? extends IChildElement> currManager;
    private ObservableList<? extends IChildElement> filteredList;
    private final UserPrefs userPrefs;

    /**
     * Construct a new state with current path and the root object.
     */
    public State(AbsolutePath currentPath, Root root, ReadOnlyUserPrefs userPrefs) {
        this.currentPath = currentPath;
        this.root = root;
        this.currManager = getCurrManager(currentPath);
        this.filteredList = currManager.asUnmodifiableObservableList();
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

    //=========== Filtered List Accessors =============================================================
    public ObservableList<? extends IChildElement> getFilteredList() {
        return this.filteredList;
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
