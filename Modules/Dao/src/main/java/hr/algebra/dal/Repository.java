package hr.algebra.dal;

import hr.algebra.model.AppUser;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import java.util.List;
import java.util.Optional;

// Defines database operations used by services and Swing panels (Exercise 07-08 pattern).
public interface Repository {

    void createUser(AppUser user) throws Exception;

    Optional<AppUser> selectUser(int id) throws Exception;

    Optional<AppUser> selectUserByUsername(String username) throws Exception;

    List<AppUser> selectUsers() throws Exception;

    int createNasaImage(NasaImage image) throws Exception;

    void updateNasaImage(int id, NasaImage image) throws Exception;

    void deleteNasaImage(int id) throws Exception;

    Optional<NasaImage> selectNasaImage(int id) throws Exception;

    Optional<NasaImage> selectNasaImageByGuid(String guid) throws Exception;

    List<NasaImage> selectNasaImages() throws Exception;

    // Lightweight presence check based on selectNasaImages().
    default boolean hasNasaImages() throws Exception {
        return !selectNasaImages().isEmpty();
    }

    int createTag(Tag tag) throws Exception;

    void updateTag(int id, Tag tag) throws Exception;

    void deleteTag(int id) throws Exception;

    Optional<Tag> selectTag(int id) throws Exception;

    List<Tag> selectTags() throws Exception;

    void linkNasaImageTag(int nasaImageId, int tagId) throws Exception;

    void unlinkNasaImageTag(int nasaImageId, int tagId) throws Exception;

    List<Tag> selectTagsForNasaImage(int nasaImageId) throws Exception;

    List<NasaImage> selectNasaImagesForTag(int tagId) throws Exception;

    void deleteImportedData() throws Exception;

    void resetApplicationData() throws Exception;

    void deleteAllData() throws Exception;
}
