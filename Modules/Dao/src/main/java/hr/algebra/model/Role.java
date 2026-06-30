package hr.algebra.model;

// ADMIN/USER values match the AppUser.Role CHECK constraint in SQL.
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
