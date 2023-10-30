package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserPrefsTest {
        UserPrefs userPrefs;
    @BeforeEach
    public void setup() {
        UserPrefs userPref = new UserPrefs();
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

}
