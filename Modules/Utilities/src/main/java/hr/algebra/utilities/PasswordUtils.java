package hr.algebra.utilities;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// PBKDF2 hash/salt helpers for AppUser login and registration.
public final class PasswordUtils {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int SALT_BYTES = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH_BITS = 256;

    private PasswordUtils() {
    }
    // Hashes a password with a newly generated random salt.
    public static PasswordHashResult hashPassword(char[] password) {
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        byte[] salt = generateSalt();
        try {
            byte[] hash = deriveKey(password, salt);
            return new PasswordHashResult(
                    Base64.getEncoder().encodeToString(hash),
                    Base64.getEncoder().encodeToString(salt)
            );
        } finally {
            clearPassword(password);
        }
    }

    // Verifies a plaintext password against stored Base64 hash and salt from the database.
    public static boolean verifyPassword(char[] password, String storedHashBase64, String storedSaltBase64) {
        if (password == null || password.length == 0) {
            return false;
        }
        if (storedHashBase64 == null || storedHashBase64.isBlank()
                || storedSaltBase64 == null || storedSaltBase64.isBlank()) {
            return false;
        }
        try {
            byte[] expectedHash = Base64.getDecoder().decode(storedHashBase64);
            byte[] salt = Base64.getDecoder().decode(storedSaltBase64);
            byte[] actualHash = deriveKey(password, salt);
            return Arrays.equals(expectedHash, actualHash);
        } catch (IllegalArgumentException ex) {
            return false;
        } finally {
            clearPassword(password);
        }
    }

    // Derives a fixed-length hash from password and salt using PBKDF2.
    private static byte[] deriveKey(char[] password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH_BITS);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new IllegalStateException("Password hashing failed.", ex);
        }
    }

    // Generates a random salt for password hashing.
    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_BYTES];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    // Clears the password char array after use.
    private static void clearPassword(char[] password) {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }

    // Prints Base64 hash/salt for SQL seed generation: java ... PasswordUtils <password> (for admin password)
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: PasswordUtils <password>");
            return;
        }
        PasswordHashResult result = hashPassword(args[0].toCharArray());
        System.out.println("PasswordHash=" + result.hashBase64());
        System.out.println("PasswordSalt=" + result.saltBase64());
    }

    // Container for the resulting Base64 hash and salt.
    public static final class PasswordHashResult {

        private final String hashBase64;
        private final String saltBase64;

        public PasswordHashResult(String hashBase64, String saltBase64) {
            this.hashBase64 = hashBase64;
            this.saltBase64 = saltBase64;
        }

        public String hashBase64() {
            return hashBase64;
        }

        public String saltBase64() {
            return saltBase64;
        }
    }
}
