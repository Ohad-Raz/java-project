package hr.algebra.service;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import hr.algebra.parsers.rss.NasaImageParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

// Adapted from Exercise 07-08 upload flow; import orchestration separated from AdminPanel.
public final class AdminImportService {

    private static final Logger LOGGER = Logger.getLogger(AdminImportService.class.getName());
    private static final String RUNTIME_ASSETS_DIR = "assets";

    private final Repository repository;
    private final AutoTagService autoTagService;

    public AdminImportService() {
        this(RepositoryFactory.getRepository(), new AutoTagService());
    }

    public AdminImportService(Repository repository, AutoTagService autoTagService) {
        this.repository = repository;
        this.autoTagService = autoTagService;
    }

    // Parses and imports NASA images. Can clear existing imported data first.
    public AdminImportResult importImages(boolean clearExistingFirst) throws Exception {
        AdminImportResult result = new AdminImportResult();

        if (clearExistingFirst) {
            clearImportedDataAndAssets();
        }

        // Parser may download images before duplicate GUIDs are detected.
        // Skipped duplicates can leave orphan files until the next clear-first import.
        List<NasaImage> parsedImages = NasaImageParser.parse();
        result.setParsedCount(parsedImages.size());

        List<Tag> availableTags = repository.selectTags();
        importParsedImages(parsedImages, availableTags, result);

        return result;
    }

    // Clears imported rows and runtime asset files.
    public void clearImportedData() throws Exception {
        clearImportedDataAndAssets();
    }

    // Deletes imported DB rows, then files under runtime assets/.
    private void clearImportedDataAndAssets() throws Exception {
        repository.deleteImportedData();
        clearRuntimeAssetsDirectory();
    }

    // Inserts parsed images; skips GUID duplicates; auto-links matching tags.
    private void importParsedImages(List<NasaImage> parsedImages, List<Tag> availableTags, AdminImportResult result)
            throws Exception {
        for (NasaImage image : parsedImages) {
            try {
                if (isDuplicateByGuid(image)) {
                    result.incrementSkippedDuplicate();
                    deleteOrphanDownloadedFile(image);
                    continue;
                }

                int imageId = repository.createNasaImage(image);
                image.setId(imageId);
                result.incrementInserted();

                linkAutomaticallyMatchedTags(image, availableTags, imageId, result);
            } catch (Exception ex) {
                result.incrementFailedImage();
                LOGGER.log(Level.SEVERE, "Failed to import image: " + image.getTitle(), ex);
            }
        }
    }

    // True when the image GUID already exists in the database.
    private boolean isDuplicateByGuid(NasaImage image) throws Exception {
        String guid = image.getGuid();
        if (guid == null || guid.isBlank()) {
            return false;
        }
        return repository.selectNasaImageByGuid(guid).isPresent();
    }

    // Links tags returned by AutoTagService for one inserted image.
    private void linkAutomaticallyMatchedTags(
            NasaImage image,
            List<Tag> availableTags,
            int imageId,
            AdminImportResult result) throws Exception {
        List<Tag> matchedTags = autoTagService.findMatchingTags(image, availableTags);
        for (Tag tag : matchedTags) {
            repository.linkNasaImageTag(imageId, tag.getId());
            result.incrementLinkedTag();
        }
    }

    // Removes a downloaded file left behind when a duplicate GUID was skipped.
    private static void deleteOrphanDownloadedFile(NasaImage image) {
        String picturePath = image.getPicturePath();
        if (picturePath == null || picturePath.isBlank()) {
            return;
        }
        try {
            Path path = Paths.get(picturePath);
            if (isSafeRuntimeAssetFile(path)) {
                Files.deleteIfExists(path);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Could not delete orphan asset: " + picturePath, ex);
        }
    }

    // Deletes regular files inside runtime assets/ only.
    private static void clearRuntimeAssetsDirectory() throws IOException {
        Path assetsDir = Paths.get(RUNTIME_ASSETS_DIR).toAbsolutePath().normalize();
        if (!isSafeRuntimeAssetsDirectory(assetsDir)) {
            LOGGER.warning("Skipped assets cleanup; path is not the expected runtime directory: " + assetsDir);
            return;
        }
        if (!Files.isDirectory(assetsDir)) {
            return;
        }

        try (Stream<Path> files = Files.list(assetsDir)) {
            files.filter(Files::isRegularFile)
                    .forEach(AdminImportService::deleteFileQuietly);
        }
    }

    // Never treats src/main/resources/assets as the runtime cleanup target.
    private static boolean isSafeRuntimeAssetsDirectory(Path assetsDir) {
        String normalized = assetsDir.toString().replace('\\', '/').toLowerCase();
        return !normalized.contains("/src/main/resources/")
                && normalized.endsWith("/assets");
    }

    private static boolean isSafeRuntimeAssetFile(Path file) {
        Path parent = file.toAbsolutePath().normalize().getParent();
        return parent != null && isSafeRuntimeAssetsDirectory(parent);
    }

    private static void deleteFileQuietly(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Could not delete asset file: " + file, ex);
        }
    }
}
