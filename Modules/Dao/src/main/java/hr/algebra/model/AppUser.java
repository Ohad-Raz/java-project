/*
 * Project setup note:
 * Based on Exercise 07-08 entity pattern (Article). Adapted for AppUser and salted password storage.
 */

package hr.algebra.model;

import java.util.Objects;

public final class AppUser {

    private int id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private Role role;

    public AppUser() {
    }

    public AppUser(String username, String passwordHash, String passwordSalt, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.role = role;
    }

    public AppUser(int id, String username, String passwordHash, String passwordSalt, Role role) {
        this(username, passwordHash, passwordSalt, role);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AppUser other = (AppUser) object;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + username + " (" + role + ")";
    }
}
