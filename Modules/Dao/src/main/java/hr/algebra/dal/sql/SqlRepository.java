/*
 * Project setup note:
 * Based on Exercise 07-08 SqlRepository. All persistence via stored procedures and CallableStatement.
 */

package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.AppUser;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Role;
import hr.algebra.model.Tag;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_APP_USER = "IDAppUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD_HASH = "PasswordHash";
    private static final String PASSWORD_SALT = "PasswordSalt";
    private static final String ROLE = "Role";

    private static final String ID_NASA_IMAGE = "IDNasaImage";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String GUID = "Guid";

    private static final String ID_TAG = "IDTag";
    private static final String NAME = "Name";

    private static final String CREATE_USER = "{ CALL createUser (?,?,?,?,?) }";
    private static final String SELECT_USER = "{ CALL selectUser (?) }";
    private static final String SELECT_USER_BY_USERNAME = "{ CALL selectUserByUsername (?) }";
    private static final String SELECT_USERS = "{ CALL selectUsers }";

    private static final String CREATE_NASA_IMAGE = "{ CALL createNasaImage (?,?,?,?,?,?,?) }";
    private static final String UPDATE_NASA_IMAGE = "{ CALL updateNasaImage (?,?,?,?,?,?,?) }";
    private static final String DELETE_NASA_IMAGE = "{ CALL deleteNasaImage (?) }";
    private static final String SELECT_NASA_IMAGE = "{ CALL selectNasaImage (?) }";
    private static final String SELECT_NASA_IMAGE_BY_GUID = "{ CALL selectNasaImageByGuid (?) }";
    private static final String SELECT_NASA_IMAGES = "{ CALL selectNasaImages }";

    private static final String CREATE_TAG = "{ CALL createTag (?,?) }";
    private static final String UPDATE_TAG = "{ CALL updateTag (?,?) }";
    private static final String DELETE_TAG = "{ CALL deleteTag (?) }";
    private static final String SELECT_TAG = "{ CALL selectTag (?) }";
    private static final String SELECT_TAGS = "{ CALL selectTags }";

    private static final String LINK_NASA_IMAGE_TAG = "{ CALL linkNasaImageTag (?,?) }";
    private static final String UNLINK_NASA_IMAGE_TAG = "{ CALL unlinkNasaImageTag (?,?) }";
    private static final String SELECT_TAGS_FOR_NASA_IMAGE = "{ CALL selectTagsForNasaImage (?) }";
    private static final String SELECT_NASA_IMAGES_FOR_TAG = "{ CALL selectNasaImagesForTag (?) }";

    private static final String DELETE_IMPORTED_DATA = "{ CALL deleteImportedData }";
    private static final String RESET_APPLICATION_DATA = "{ CALL resetApplicationData }";
    private static final String DELETE_ALL_DATA = "{ CALL deleteAllData }";

    @Override
    public void createUser(AppUser user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {

            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD_HASH, user.getPasswordHash());
            stmt.setString(PASSWORD_SALT, user.getPasswordSalt());
            stmt.setString(ROLE, user.getRole().toDatabaseValue());
            stmt.registerOutParameter(ID_APP_USER, Types.INTEGER);

            stmt.executeUpdate();
            user.setId(stmt.getInt(ID_APP_USER));
        }
    }

    @Override
    public Optional<AppUser> selectUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER)) {

            stmt.setInt(ID_APP_USER, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapAppUser(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppUser> selectUserByUsername(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER_BY_USERNAME)) {

            stmt.setString(USERNAME, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapAppUser(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<AppUser> selectUsers() throws Exception {
        List<AppUser> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USERS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapAppUser(rs));
            }
        }
        return users;
    }

    @Override
    public int createNasaImage(NasaImage image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NASA_IMAGE)) {

            bindNasaImage(stmt, image);
            stmt.registerOutParameter(ID_NASA_IMAGE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_NASA_IMAGE);
        }
    }

    @Override
    public void updateNasaImage(int id, NasaImage image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_NASA_IMAGE)) {

            stmt.setInt(ID_NASA_IMAGE, id);
            bindNasaImage(stmt, image);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteNasaImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_NASA_IMAGE)) {

            stmt.setInt(ID_NASA_IMAGE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<NasaImage> selectNasaImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGE)) {

            stmt.setInt(ID_NASA_IMAGE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapNasaImage(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<NasaImage> selectNasaImageByGuid(String guid) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGE_BY_GUID)) {

            stmt.setString(GUID, guid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapNasaImage(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<NasaImage> selectNasaImages() throws Exception {
        List<NasaImage> images = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                images.add(mapNasaImage(rs));
            }
        }
        return images;
    }

    @Override
    public int createTag(Tag tag) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_TAG)) {

            stmt.setString(NAME, tag.getName());
            stmt.registerOutParameter(ID_TAG, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_TAG);
        }
    }

    @Override
    public void updateTag(int id, Tag tag) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_TAG)) {

            stmt.setInt(ID_TAG, id);
            stmt.setString(NAME, tag.getName());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteTag(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_TAG)) {

            stmt.setInt(ID_TAG, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Tag> selectTag(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_TAG)) {

            stmt.setInt(ID_TAG, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTag(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Tag> selectTags() throws Exception {
        List<Tag> tags = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_TAGS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tags.add(mapTag(rs));
            }
        }
        return tags;
    }

    @Override
    public void linkNasaImageTag(int nasaImageId, int tagId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(LINK_NASA_IMAGE_TAG)) {

            stmt.setInt(ID_NASA_IMAGE, nasaImageId);
            stmt.setInt(ID_TAG, tagId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void unlinkNasaImageTag(int nasaImageId, int tagId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UNLINK_NASA_IMAGE_TAG)) {

            stmt.setInt(ID_NASA_IMAGE, nasaImageId);
            stmt.setInt(ID_TAG, tagId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Tag> selectTagsForNasaImage(int nasaImageId) throws Exception {
        List<Tag> tags = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_TAGS_FOR_NASA_IMAGE)) {

            stmt.setInt(ID_NASA_IMAGE, nasaImageId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tags.add(mapTag(rs));
                }
            }
        }
        return tags;
    }

    @Override
    public List<NasaImage> selectNasaImagesForTag(int tagId) throws Exception {
        List<NasaImage> images = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGES_FOR_TAG)) {

            stmt.setInt(ID_TAG, tagId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    images.add(mapNasaImage(rs));
                }
            }
        }
        return images;
    }

    @Override
    public void deleteImportedData() throws Exception {
        executeLifecycle(DELETE_IMPORTED_DATA);
    }

    @Override
    public void resetApplicationData() throws Exception {
        executeLifecycle(RESET_APPLICATION_DATA);
    }

    @Override
    public void deleteAllData() throws Exception {
        executeLifecycle(DELETE_ALL_DATA);
    }

    private void executeLifecycle(String call) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(call)) {
            stmt.executeUpdate();
        }
    }

    private static void bindNasaImage(CallableStatement stmt, NasaImage image) throws SQLException {
        stmt.setString(TITLE, image.getTitle());
        stmt.setString(LINK, image.getLink());
        stmt.setString(DESCRIPTION, image.getDescription());
        stmt.setString(PICTURE_PATH, image.getPicturePath());
        stmt.setString(PUBLISHED_DATE, NasaImage.formatPublishedDate(image.getPublishedDate()));
        if (image.getGuid() == null) {
            stmt.setNull(GUID, Types.NVARCHAR);
        } else {
            stmt.setString(GUID, image.getGuid());
        }
    }

    private static AppUser mapAppUser(ResultSet rs) throws SQLException {
        return new AppUser(
                rs.getInt(ID_APP_USER),
                rs.getString(USERNAME),
                rs.getString(PASSWORD_HASH),
                rs.getString(PASSWORD_SALT),
                Role.fromDatabaseValue(rs.getString(ROLE)));
    }

    private static Tag mapTag(ResultSet rs) throws SQLException {
        return new Tag(rs.getInt(ID_TAG), rs.getString(NAME));
    }

    private static NasaImage mapNasaImage(ResultSet rs) throws SQLException {
        return new NasaImage(
                rs.getInt(ID_NASA_IMAGE),
                rs.getString(TITLE),
                rs.getString(LINK),
                rs.getString(DESCRIPTION),
                rs.getString(PICTURE_PATH),
                NasaImage.parsePublishedDate(rs.getString(PUBLISHED_DATE)),
                rs.getString(GUID));
    }
}
