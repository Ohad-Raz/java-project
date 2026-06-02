/*
 * Project setup note:
 * Final project role enum. Values match AppUser.Role CHECK in SQL (ADMIN, USER).
 */

package hr.algebra.model;

public enum Role {
    ADMIN,
    USER;

    public static Role fromDatabaseValue(String value) {
        return Role.valueOf(value);
    }

    public String toDatabaseValue() {
        return name();
    }
}
