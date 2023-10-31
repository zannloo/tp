package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserPrefsTest {
    private UserPrefs userPrefs;
    @BeforeEach
    public void setup() {
        userPrefs = new UserPrefs();
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equalsNull_returnsFalse() {
        assertFalse(userPrefs.equals(null));
    }

    @Test
    public void hashCode_equalUserPrefs_returnsSameHashCode() {
        UserPrefs test = new UserPrefs();
        assertEquals(userPrefs.hashCode(), test.hashCode());
    }

    @Test
    public void equals_equalUserPrefs_returnsTrue() {
        UserPrefs test = new UserPrefs();
        assertTrue(userPrefs.equals(test));
    }

    @Test
    public void equals_nullUserPrefs_returnsFalse() {
        assertFalse(userPrefs.equals(null));
    }

    @Test
    public void toString_returnsTrue() {
        String message = "Gui Settings :"
                + " seedu.address.commons.core.GuiSettings{windowWidth=450.0,"
                + " windowHeight=600.0, windowCoordinates=null}\n"
                + "Local data file location : data/profbook.json";
        assertEquals(userPrefs.toString(), message);
    }

}
