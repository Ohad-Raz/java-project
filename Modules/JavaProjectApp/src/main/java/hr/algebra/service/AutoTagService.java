package hr.algebra.service;

import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Matches existing tag names against image title/description during import.
public final class AutoTagService {

    public AutoTagService() {
    }

    // Returns existing tags whose names appear in the image title or description.
    public List<Tag> findMatchingTags(NasaImage image, List<Tag> availableTags) {
        // Extracts searchable text from the image.
        Function<NasaImage, String> imageTextExtractor = nasaImage -> buildHaystack(nasaImage);
        String haystack = imageTextExtractor.apply(image);
        if (haystack.isBlank() || availableTags == null || availableTags.isEmpty()) {
            return List.of();
        }

        Predicate<Tag> tagMatchesImageText = tag -> matchesTagName(haystack, tag);

        return availableTags.stream()
                .filter(tagMatchesImageText)//calls tagMatchesImageText for each tag:)
                .collect(Collectors.toList());
    }

    // Combines title and description into one searchable string  
    private static String buildHaystack(NasaImage image) {
        if (image == null) {
            return "";
        }
        return (nullToEmpty(image.getTitle()) + " " + nullToEmpty(image.getDescription())).trim();
    }

    // Case-insensitive whole-word match; Pattern.quote keeps names like "Space Station" literal.
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
//helper method to handle null or blank title or description
    private static String nullToEmpty(String value) {
        return value == null || value.isBlank() ? "" : value;
    }
}
