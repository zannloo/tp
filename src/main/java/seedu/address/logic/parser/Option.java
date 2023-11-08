package seedu.address.logic.parser;

import java.util.Objects;

/**
 * An option is a command line argument that can be used in a command.
 * An option typically has a long name and may have an optional short name.
 * E.g. '--name' in 'touch ~/grp-001/0001Y --name Ming Yuan'.
 */
public class Option {
    private final String longName;
    private final String shortName;
    private final boolean hasShortName;

    /**
     * Constructs an option with both long and short names.
     *
     * @param longName The long name of the option.
     * @param shortName The short name of the option.
     */
    public Option(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
        this.hasShortName = true;
    }

    /**
     * Constructs an option with only a long name.
     *
     * @param longName The long name of the option.
     */
    public Option(String longName) {
        this.longName = longName;
        this.shortName = null;
        this.hasShortName = false;
    }

    public boolean hasShortName() {
        return hasShortName;
    }

    public int getShortNameLength() {
        return getShortName().length();
    }
    public int getLongNameLength() {
        return getLongName().length();
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return this.longName
                + ((this.shortName != null) ? "/" + this.shortName : "");
    }

    @Override
    public int hashCode() {
        return Objects.hash(longName, shortName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Option)) {
            return false;
        }

        Option otherOption = (Option) other;

        if (shortName != null) {
            return longName.equals(otherOption.longName) && shortName.equals(otherOption.shortName);
        }
        return longName.equals(otherOption.longName);
    }
}

