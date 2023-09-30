package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IdValidatorTest {
    private IdValidator validator;

    @BeforeEach
    public void setUp() {
        // Initialize the validator with a regular expression pattern
        validator = new IdValidator("stu-\\d{3}");
    }

    @Test
    public void isValid_validId_returnsTrue() {
        assertTrue(validator.isValid("stu-123"));
    }

    @Test
    public void isValid_invalidId_returnsFalse() {
        assertFalse(validator.isValid("stu-1"));
        assertFalse(validator.isValid("stu-12"));
        assertFalse(validator.isValid("stu-1234"));
    }

    @Test
    public void isValid_nullId_returnsFalse() {
        assertFalse(validator.isValid(null));
    }

}
