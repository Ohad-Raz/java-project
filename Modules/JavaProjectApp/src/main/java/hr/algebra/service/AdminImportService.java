/*
 * Project setup note:
 * Based on Exercise 07-08 admin upload flow (UploadArticlesPanel + ArticleParser), but separated
 * into a service for cleaner MVC/SOC in the final NASA IOTD project.
 *
 * NasaImageParser parses the RSS feed and downloads images into the runtime assets/ directory.
 * Repository persists NasaImage rows and tag links through stored procedures only.
 * AutoTagService detects which seeded tags match each image title/description.
 *
 * The GUI will later call importImages(...) from a background thread so the UI does not freeze.
 */

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

    /**
     * Parses and imports NASA images. Optionally clears previous imported rows and runtime assets first.
     */
    public AdminImportResult importImages(boolean clearExistingFirst) throws Exception {
        AdminImportResult result = new AdminImportResult();

        if (clearExistingFirst) {
            clearImportedDataAndAssets();
        }

        /*
         * NasaImageParser downloads enclosure images before this service can skip duplicates by guid.
         * Skipped duplicates may leave unused files in assets/ until the next clearExistingFirst import.
         */
        List<NasaImage> parsedImages = NasaImageParser.parse();
        result.setParsedCount(parsedImages.size());

        List<Tag> availableTags = repository.selectTags();
        importParsedImages(parsedImages, availableTags, result);

        return result;
    }

    /** Clears imported database rows and deletes files from the runtime assets directory. */
    private void clearImportedDataAndAssets() throws Exception {
        repository.deleteImportedData();
        clearRuntimeAssetsDirectory();
    }

    /** Inserts parsed images, skips duplicates by RSS guid, and links automatically matched tags. */
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

    /** Skips duplicate images by RSS guid using selectNasaImageByGuid. */
    private boolean isDuplicateByGuid(NasaImage image) throws Exception {
        String guid = image.getGuid();
        if (guid == null || guid.isBlank()) {
            return false;
        }
        return repository.selectNasaImageByGuid(guid).isPresent();
    }

    /** Links automatically matched tags for one inserted image. */
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
