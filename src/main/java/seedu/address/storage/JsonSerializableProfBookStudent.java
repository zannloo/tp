package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "profbook student")
public class JsonSerializableProfBookStudent {
    public static final String MESSAGE_DUPLICATE_PERSON = "Student list contains duplicate person(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
}
