package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

// Adapted from Exercise 07-08 ArticleParser for NASA RSS items.
public final class NasaImageParser {

    private static final String RSS_URL = "https://www.nasa.gov/feeds/iotd-feed/";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private NasaImageParser() {
    }

    public static List<NasaImage> parse() throws IOException, XMLStreamException {
        List<NasaImage> images = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            NasaImage image = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            image = new NasaImage();
                            images.add(image);
                        }
                        // enclosure url is read from the start element, not character data
                        if (tagType.isPresent()
                                && tagType.get().equals(TagType.ENCLOSURE)
                                && image != null
                                && image.getPicturePath() == null) {
                            Attribute urlAttribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                            if (urlAttribute != null) {
                                handlePicture(image, urlAttribute.getValue());
                            }
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && image != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        image.setTitle(data);
                                    }
                                }
                                case LINK -> {
                                    if (!data.isEmpty()) {
                                        image.setLink(data);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        image.setDescription(data);
                                    }
                                }
                                case GUID -> {
                                    if (!data.isEmpty()) {
                                        image.setGuid(data);
                                    }
                                }
                                case PUB_DATE -> {
                                    if (!data.isEmpty()) {
                                        ZonedDateTime publishedDate = ZonedDateTime.parse(data, NasaImage.DATE_FORMATTER);
                                        image.setPublishedDate(publishedDate.toLocalDateTime());
                                    }
                                }
                                default -> {
                                }
                            }
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        return images;
    }

    // Downloads one enclosure image; logs and continues on failure.
    private static void handlePicture(NasaImage image, String pictureUrl) {
        try {
            String pathPart = pictureUrl;
            int queryIndex = pathPart.indexOf('?');
            if (queryIndex >= 0) {
                pathPart = pathPart.substring(0, queryIndex);
            }
            String ext = pathPart.substring(pathPart.lastIndexOf('.'));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            image.setPicturePath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(NasaImageParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        GUID("guid"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");

        private final String name;

        private TagType(String name) {
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
