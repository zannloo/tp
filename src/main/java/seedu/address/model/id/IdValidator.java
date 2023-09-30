package seedu.address.model.id;

/**
 * The {@code IdValidator} class provides validation functionality for IDs
 * using a regular expression pattern.
 */
public class IdValidator {
    private final String regexPattern;

    /**
     * Constructs an {@code IdValidator} object with the specified regular expression pattern.
     *
     * @param regexPattern The regular expression pattern used for ID validation.
     */
    public IdValidator(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    /**
     * Checks whether the provided ID string is valid according to the configured regular expression pattern.
     *
     * @param id The ID string to validate.
     * @return {@code true} if the ID is valid; {@code false} otherwise.
     */
    public boolean isValid(String id) {
        return id != null && id.matches(regexPattern);
    }
}
