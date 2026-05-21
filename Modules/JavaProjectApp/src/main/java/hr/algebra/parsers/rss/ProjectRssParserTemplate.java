/*
 * Project setup note:
 * Placeholder based on Exercise 07-08 ArticleParser.
 * In the professor solution, the concrete RSS parser lives in the application module, not Dao.
 * Final implementation will be added after choosing the RSS source and domain entity.
 */

package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Structural placeholder mirroring Exercise 07-08 {@code ArticleParser}.
 * No RSS URL, no final entity type, no repository calls yet.
 */
public final class ProjectRssParserTemplate {

    private static final Logger LOGGER = Logger.getLogger(ProjectRssParserTemplate.class.getName());

    // TODO: RSS feed URL will be decided in a later step (professor uses a constant in ArticleParser).
    private static final String RSS_URL_PLACEHOLDER = "";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private ProjectRssParserTemplate() {
    }

    /**
     * Placeholder entry point matching {@code ArticleParser.parse()} signature style.
     * Returns a list once domain type and feed URL exist; for now rejects unconfigured setup.
     */
    public static List<Object> parse() throws IOException, XMLStreamException {
        if (RSS_URL_PLACEHOLDER.isBlank()) {
            throw new UnsupportedOperationException(
                    "RSS feed URL and domain entity are not configured yet. Replace this placeholder with a concrete parser."
            );
        }

        List<Object> items = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL_PLACEHOLDER);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);
            Optional<TagType> tagType = Optional.empty();
            Object currentItem = null;
            StartElement startElement = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String localName = startElement.getName().getLocalPart();
                        tagType = TagType.from(localName);
                        if (tagType.isPresent() && tagType.get() == TagType.ITEM) {
                            // TODO: currentItem = new FinalEntity();
                            items.add(currentItem);
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && currentItem != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            mapCharacters(tagType.get(), data, startElement, currentItem);
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        return items;
    }

    private static void mapCharacters(TagType tagType, String data, StartElement startElement, Object currentItem) {
        // TODO: map RSS fields to final entity setters (see Exercise 07-08 ArticleParser switch).
        switch (tagType) {
            case ENCLOSURE -> {
                if (startElement != null) {
                    Attribute urlAttribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                    if (urlAttribute != null) {
                        handlePicturePlaceholder(urlAttribute.getValue());
                    }
                }
            }
            default -> {
            }
        }
    }

    private static void handlePicturePlaceholder(String pictureUrl) {
        try {
            String localPicturePath = DIR + File.separator + "placeholder-image" + EXT;
            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Image download failed", ex);
        }
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");

        private final String name;

        TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
