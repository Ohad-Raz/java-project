/*
 * Project setup note:
 * Based on Exercise 11-12, Comparable/TreeSet related-entity pattern. Placeholder until domain model is defined.
 */

package hr.algebra.model;

/**
 * Exercise 11-12 reference: TreeSet requires Comparable to avoid duplicates.
 * Replace with final related-entity type when domain model is defined.
 */
public final class ComparableEntityExample implements Comparable<ComparableEntityExample> {

    private final String uniqueKey;
    private final String label;

    public ComparableEntityExample(String uniqueKey, String label) {
        this.uniqueKey = uniqueKey;
        this.label = label;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int compareTo(ComparableEntityExample other) {
        return uniqueKey.compareTo(other.uniqueKey);
    }

    @Override
    public String toString() {
        return uniqueKey + " - " + label;
    }
}
