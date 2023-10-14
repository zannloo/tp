package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArgumentTokenizerTest {

    private final Option unknownOption = new Option("--unknown");
    private final Option name = new Option("--name", "-n");
    private final Option id = new Option("--id", "-i");
    private final Option address = new Option("--address", "-a");

    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, name);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, name);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code option} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Option)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Option option, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(option).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(option).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(option).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Option option) {
        assertFalse(argMultimap.getValue(option).isPresent());
    }

    @Test
    public void tokenize_noOptions_allTakenAsPreamble() {
        String argsString = "  some random string /t tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    @Test
    public void tokenize_oneArgument() {

        // <----long hand----->

        // Preamble present
        String argsString = "  Some preamble string --name Nereus Ng Wei Bin";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, name);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, name, "Nereus Ng Wei Bin");

        // No preamble
        argsString = " --name   Nereus Ng Wei Bin ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, name, "Nereus Ng Wei Bin");

        // <----short hand----->

        // Preamble present
        argsString = "  Some preamble string -n Nereus Ng Wei Bin";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, name, "Nereus Ng Wei Bin");

        // No preamble
        argsString = " -n   Nereus Ng Wei Bin";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, name, "Nereus Ng Wei Bin");

    }

    @Test
    public void tokenize_multipleArguments() {
        // Only two arguments are present
        String argsString = "SomePreambleString --id id-Value --name name-Value";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, name, "name-Value");
        assertArgumentPresent(argMultimap, id, "id-Value");
        assertArgumentAbsent(argMultimap, address);

        // All three arguments are present
        argsString = "Different Preamble String --id id-Value --name name-Value --address address-Value";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, name, "name-Value");
        assertArgumentPresent(argMultimap, id, "id-Value");
        assertArgumentPresent(argMultimap, address, "address-Value");

        // All three arguments with mix of short hand
        argsString = "Different Preamble String -i id-Value --name name-Value -a address-Value";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, name, "name-Value");
        assertArgumentPresent(argMultimap, id, "id-Value");
        assertArgumentPresent(argMultimap, address, "address-Value");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, name);

        /* Also covers: testing for options not specified as a option */

        // Options not previously given to the tokenizer should not return any values
        argsString = unknownOption + "some value";
        argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertArgumentAbsent(argMultimap, unknownOption);
        assertPreamblePresent(argMultimap, argsString); // Unknown option is taken as part of preamble
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString --id id-Value -a -a -i another id value --name name value --id";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, name, "name value");
        assertArgumentPresent(argMultimap, id, "id-Value", "another id value", "");
        assertArgumentPresent(argMultimap, address, "", "");
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleString--name name joined-ijoined -i not joined--addressjoined";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, name, id, address);
        assertPreamblePresent(argMultimap, "SomePreambleString--name name joined-ijoined");
        assertArgumentAbsent(argMultimap, name);
        assertArgumentPresent(argMultimap, id, "not joined--addressjoined");
        assertArgumentAbsent(argMultimap, address);
    }

    @Test
    public void equalsMethod() {
        Option aaa = new Option("--aaa", "-a");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Option("--aaa", "-a"));

        assertNotEquals(aaa, "--aaa");
        assertNotEquals(aaa, new Option("--aab", "-a"));
        assertNotEquals(aaa, new Option("--aaa", "-b"));
    }

}
