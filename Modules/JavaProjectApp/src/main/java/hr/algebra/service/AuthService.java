package hr.algebra.service;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.AppUser;
import hr.algebra.model.Role;
import hr.algebra.utilities.PasswordUtils;
import hr.algebra.utilities.PasswordUtils.PasswordHashResult;
import java.util.Optional;

// Login and registration through Repository + PasswordUtils (no SQL here).
public final class AuthService {

    private final Repository repository;

    public AuthService() {
        this(RepositoryFactory.getRepository());
    }

    public AuthService(Repository repository) {
        this.repository = repository;
    }

    // Checks username/password against stored hash and salt.
    public Optional<AppUser> authenticate(String username, char[] password) throws Exception {
        if (username == null || username.isBlank()) {
            return Optional.empty();
        }
        Optional<AppUser> user = repository.selectUserByUsername(username.trim());
        if (user.isEmpty()) {
            return Optional.empty();
        }
        AppUser storedUser = user.get();
        boolean valid = PasswordUtils.verifyPassword(
                password,
                storedUser.getPasswordHash(),
                storedUser.getPasswordSalt());
        return valid ? user : Optional.empty();
    }

    // Registers a new USER account with hashed password and salt.
    public void registerUser(String username, char[] password) throws Exception {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException("Password is required.");
        }
        String trimmedUsername = username.trim();
        if (repository.selectUserByUsername(trimmedUsername).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }
        PasswordHashResult hashed = PasswordUtils.hashPassword(password);
        AppUser newUser = new AppUser(
                trimmedUsername,
                hashed.hashBase64(),
                hashed.saltBase64(),
                Role.USER);
        repository.createUser(newUser);
    }
}
