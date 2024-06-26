package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FilterTagCommand object
 */
public class FilterTagCommandParser implements Parser<FilterTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTagCommand
     * and returns a FilterTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTagCommand.MESSAGE_USAGE));
        }
        String[] tagKeywords = trimmedArgs.split("\\s+");
        return new FilterTagCommand(new TagContainsKeywordsPredicate(createTags(tagKeywords)));
    }

    /**
     * Parses {@code tagKeywords} into a {@code List<Tag>}.
     */
    public static List<Tag> createTags(String... tagKeywords) throws ParseException {
        List<Tag> tags = new ArrayList<>();
        for (String keyword : tagKeywords) {
            tags.add(ParserUtil.parseTag(keyword));
        }
        return tags;
    }

}
