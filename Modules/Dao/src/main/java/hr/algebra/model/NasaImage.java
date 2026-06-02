/*
 * Project setup note:
 * Based on Exercise 07-08 Article model. Adapted for NASA IOTD RSS fields and Guid deduplication.
 */

package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class NasaImage implements Comparable<NasaImage> {

    /** Matches RSS {@code pubDate} storage (RFC 1123), e.g. Tue, 19 May 2026 17:08 GMT */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

    /** Formats {@link LocalDateTime} as RFC 1123 UTC (formatter requires offset). */
    public static String formatPublishedDate(LocalDateTime publishedDate) {
        return ZonedDateTime.of(publishedDate, ZoneOffset.UTC).format(DATE_FORMATTER);
    }

    /** Parses RFC 1123 text into {@link LocalDateTime} (UTC). */
    public static LocalDateTime parsePublishedDate(String value) {
        return ZonedDateTime.parse(value, DATE_FORMATTER).toLocalDateTime();
    }

    private int id;
    private String title;
    private String link;
    private String description;
    private String picturePath;
    private LocalDateTime publishedDate;
    private String guid;
//default constructor for xml
    public NasaImage() {
    }

    public NasaImage(String title, String link, String description, String picturePath,
            LocalDateTime publishedDate, String guid) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.guid = guid;
    }

    public NasaImage(int id, String title, String link, String description, String picturePath,
            LocalDateTime publishedDate, String guid) {
        this(title, link, description, picturePath, publishedDate, guid);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public int compareTo(NasaImage other) {
        int byDate = other.publishedDate.compareTo(this.publishedDate);
        if (byDate != 0) {
            return byDate;
        }
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        NasaImage other = (NasaImage) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
}
