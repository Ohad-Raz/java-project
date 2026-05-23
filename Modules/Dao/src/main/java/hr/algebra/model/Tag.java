/*
 * Project setup note:
 * Based on Exercise 11-12 Comparable/TreeSet pattern (Author). Adapted for Tag entity.
 */

package hr.algebra.model;

import java.util.Objects;

public final class Tag implements Comparable<Tag> {

    private int id;
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(int id, String name) {
        this(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag other) {
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Tag other = (Tag) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
