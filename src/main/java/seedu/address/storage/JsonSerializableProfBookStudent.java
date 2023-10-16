package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyProfBook;
import seedu.address.model.person.Person;
import seedu.address.model.profbook.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "profbook student")
public class JsonSerializableProfBookStudent {
    public static final String MESSAGE_DUPLICATE_PERSON = "Student list contains duplicate person(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

//    public ReadOnlyProfBook toModelType() throws IllegalValueException {
//        Profbook profbook= new ProfBook();
//        for (JsonAdaptedStudent jsonAdaptedStudent : persons) {
//            Student person = jsonAdaptedStudent.toModelType();
//            if (addressBook.hasPerson(person)) {
//                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
//            }
//            addressBook.addPerson(person);
//        }
//        return addressBook;
//    }
}
