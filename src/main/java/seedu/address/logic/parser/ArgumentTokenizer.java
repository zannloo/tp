package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <option> value <option> value ...}<br>
 *     e.g. {@code some preamble text -t 11.00 -t 12.00 -k -m July}  where options are {@code -t -k -m}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code -k} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code -t}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    /**
     * Extracts preamble from the argString.
     */
    public static String extractPreamble(String argsString) {
        String[] s = argsString.split(" -", 2);
        return unescape(s[0].trim());
    }

    /**
     * Extracts all option names appear in argString.
     */
    public static List<String> extractAllOptionNames(String argString) {
        List<String> list = new ArrayList<>();
        String[] splitBySpace = argString.split(" ");
        for (String s : splitBySpace) {
            if (s.startsWith("-") || s.startsWith("--")) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps options to their
     * respective argument values. Only the given options will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <option> value <option> value ...}
     * @param options   Options to tokenize the arguments string with
     * @return           ArgumentMultimap object that maps options to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Option... options) {
        // Add a space at the end to avoid skipping last option without value, e.g. "-t smart -t"
        List<OptionPosition> positions = findAllOptionPositions(argsString + " ", options);
        return extractArguments(argsString + " ", positions);
    }

    /**
     * Finds all zero-based option positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <option> value <option> value ...}
     * @param options   Options to find in the arguments string
     * @return          List of zero-based option positions in the given arguments string
     */
    private static List<OptionPosition> findAllOptionPositions(String argsString, Option... options) {
        return Arrays.stream(options)
                .flatMap(option -> findOptionPositions(argsString, option).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllOptionPositions}
     */
    private static List<OptionPosition> findOptionPositions(String argsString, Option option) {
        List<OptionPosition> positions = new ArrayList<>();

        // find long name
        String longName = option.getLongName();
        int longNamePosition = findOptionPosition(argsString, longName, 0);
        while (longNamePosition != -1) {
            OptionPosition extendedOption = new OptionPosition(option, longNamePosition, false);
            positions.add(extendedOption);
            longNamePosition = findOptionPosition(argsString, longName, longNamePosition);
        }

        // find short name (if applicable)
        if (option.hasShortName()) {
            String shortName = option.getShortName();
            int shortNamePosition = findOptionPosition(argsString, shortName, 0);
            while (shortNamePosition != -1) {
                OptionPosition extendedOption = new OptionPosition(option, shortNamePosition, true);
                positions.add(extendedOption);
                shortNamePosition = findOptionPosition(argsString, shortName, shortNamePosition);
            }
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of {@code option} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before {@code option}. Returns -1 if no
     * such occurrence can be found.
     *
     * E.g if {@code argsString} = "-e hi-p 900", {@code option} = "-p" and
     * {@code fromIndex} = 0, this method returns -1 as there are no valid
     * occurrences of "-p" with whitespace before it. However, if
     * {@code argsString} = "-e hi -p 900", {@code option} = "-p" and
     * {@code fromIndex} = 0, this method returns 5.
     */
    private static int findOptionPosition(String argsString, String option, int fromIndex) {
        int optionIndex = argsString.indexOf(" " + option + " ", fromIndex);
        return optionIndex == -1 ? -1
                : optionIndex + 1; // +1 as offset for whitespace
    }

    /**
     * Extracts options and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted options to their respective arguments. Options are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <option> value <option> value ...}
     * @param optionPositions Zero-based positions of all options in {@code argsString}
     * @return                ArgumentMultimap object that maps options to their arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<OptionPosition> optionPositions) {

        // Sort by start position
        optionPositions.sort((option1, option2) -> option1.getStartPosition() - option2.getStartPosition());

        // Insert a OptionPosition to represent the preamble
        OptionPosition preambleMarker = new OptionPosition(new Option(""), 0, false);
        optionPositions.add(0, preambleMarker);

        // Add a dummy OptionPosition to represent the end of the string
        OptionPosition endPositionMarker = new OptionPosition(new Option(""), argsString.length(), false);
        optionPositions.add(endPositionMarker);

        // Map options to their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < optionPositions.size() - 1; i++) {
            // Extract and store options and their arguments
            Option argOption = optionPositions.get(i).getOption();
            String argValue = extractArgumentValue(argsString, optionPositions.get(i), optionPositions.get(i + 1));
            argMultimap.put(argOption, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns the trimmed and unescaped value of the argument
     * in the arguments string specified by {@code currentOptionPosition}.
     * The end position of the value is determined by {@code nextOptionPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                        OptionPosition currentOptionPosition,
                                        OptionPosition nextOptionPosition) {
        Option option = currentOptionPosition.getOption();

        int optionLength = currentOptionPosition.isShortHand()
                ? option.getShortNameLength()
                : option.getLongNameLength();

        int valueStartPos = currentOptionPosition.getStartPosition() + optionLength;
        String value = argsString.substring(valueStartPos, nextOptionPosition.getStartPosition());

        return unescape(value.trim());
    }

    /**
     * Unescape special characters e.g. '\' and '-'.
     */
    private static String unescape(String input) {
        // Use regular expression to unescape backslash and hyphen
        String unescapedInput = input.replaceAll("\\\\(.)", "$1");
        return unescapedInput;
    }

    /**
     * Represents a option's position in an arguments string.
     */
    private static class OptionPosition {
        private final int startPosition;
        private final Option option;
        private final boolean isShortHand;

        OptionPosition(Option option, int startPosition, boolean isShortHand) {
            this.option = option;
            this.startPosition = startPosition;
            this.isShortHand = isShortHand;
        }

        int getStartPosition() {
            return startPosition;
        }

        Option getOption() {
            return option;
        }

        boolean isShortHand() {
            return isShortHand;
        }
    }

}
