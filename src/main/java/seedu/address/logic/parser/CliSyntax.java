package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Option definitions */
    public static final Option OPTION_NAME = new Option("--name", "-n");
    public static final Option OPTION_PHONE = new Option("--phone", "-p");
    public static final Option OPTION_EMAIL = new Option("--email", "-e");
    public static final Option OPTION_ADDRESS = new Option("--address", "-a");
    public static final Option OPTION_TAG = new Option("--tag", "-t");
    public static final Option OPTION_ID = new Option("--id", "-i");
    public static final Option OPTION_DESC = new Option("--desc", "-d");
    public static final Option OPTION_DATETIME = new Option("--datetime", "-dt");
    public static final Option OPTION_ALL = new Option("--all", "-al");
    public static final Option OPTION_HELP = new Option("--help", "-h");
}
