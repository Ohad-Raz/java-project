package hr.algebra.dal;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

// Loads SqlRepository from repository.properties (Exercise 07-08 pattern).
public final class RepositoryFactory {

    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "/config/repository.properties";
    private static final String CLASS_NAME = "CLASS_NAME";

    private static final Repository REPOSITORY;

    static {
        Repository loaded = null;
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)) {
            if (is == null) {
                throw new IllegalStateException("Missing resource: " + PATH);
            }
            PROPERTIES.load(is);
            String className = PROPERTIES.getProperty(CLASS_NAME);
            loaded = (Repository) Class.forName(className)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        REPOSITORY = loaded;
    }

    private RepositoryFactory() {
    }

    public static Repository getRepository() {
        return REPOSITORY;
    }
}
