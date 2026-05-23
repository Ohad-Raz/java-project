/*
 * Project setup note:
 * Auto-tag matching helper for the final Java Programming 1 project.
 *
 * This class supports the NASA Image of the Day import workflow.
 * It does not parse RSS, does not access the database, and does not update the GUI.
 *
 * The service receives a NasaImage and a list of prepared Tag objects loaded from the repository.
 * It combines the image title and description, then checks each tag name using a case-insensitive
 * whole-word regular expression. Pattern.quote is used so tag names such as "Space Station"
 * are treated as literal text.
 *
 * Matching tags are returned to the caller. Later, the Admin import workflow will save the
 * relationship through Repository.linkNasaImageTag(imageId, tagId).
 *
 * This keeps responsibilities separated according to the professor architecture:
 * parser = RSS/image loading, repository = database procedures, service = matching logic,
 * GUI/controller = workflow orchestration.
 */

package hr.algebra.service;

import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class AutoTagService {

    private static final Function<Tag, Predicate<String>> TAG_MATCH_PREDICATE =
            tag -> haystack -> matchesTagName(haystack, tag);

    public AutoTagService() {
    }

    public List<Tag> findMatchingTags(NasaImage image, List<Tag> availableTags) {
        String haystack = buildHaystack(image);
        if (haystack.isBlank() || availableTags == null || availableTags.isEmpty()) {
            return List.of();
        }

        return availableTags.stream()
                .filter(tag -> TAG_MATCH_PREDICATE.apply(tag).test(haystack))
                .collect(Collectors.toList());
    }

    private static String buildHaystack(NasaImage image) {
        if (image == null) {
            return "";
        }
        return (nullToEmpty(image.getTitle()) + " " + nullToEmpty(image.getDescription())).trim();
    }

    private static boolean matchesTagName(String haystack, Tag tag) {
        if (tag == null) {
            return false;
        }
        String name = tag.getName();
        if (name == null || name.isBlank()) {
            return false;
        }
        Pattern pattern = Pattern.compile(
                "\\b" + Pattern.quote(name) + "\\b",
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(haystack).find();
    }

    private static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
