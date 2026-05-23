/*
 * Java Programming 1 — Final Project
 * Based on Exercise 07-08 sqlScript.sql (CREATE DATABASE, tables, stored procedures).
 * Minimal scope: AppUser, NasaImage, Tag, NasaImageTag
 *
 * Run once on SQL Server (SSMS / sqlcmd) with sufficient privileges.
 * Adjust database name if needed; default: NASA_IOTD
 */

CREATE DATABASE NASA_IOTD
GO

USE NASA_IOTD
GO

/* -------------------------------------------------------------------------- */
/* Tables                                                                     */
/* -------------------------------------------------------------------------- */

CREATE TABLE AppUser
(
    IDAppUser INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(100) NOT NULL UNIQUE,
    PasswordHash NVARCHAR(256) NOT NULL,
    PasswordSalt NVARCHAR(128) NOT NULL,
    Role NVARCHAR(20) NOT NULL,
    CONSTRAINT CK_AppUser_Role CHECK (Role IN ('ADMIN', 'USER'))
)
GO

CREATE TABLE NasaImage
(
    IDNasaImage INT PRIMARY KEY IDENTITY,
    Title NVARCHAR(300) NOT NULL,
    Link NVARCHAR(500) NOT NULL,
    Description NVARCHAR(900) NULL,
    PicturePath NVARCHAR(260) NULL,
    PublishedDate NVARCHAR(90) NOT NULL,
    Guid NVARCHAR(500) NULL,
    CONSTRAINT UQ_NasaImage_Guid UNIQUE (Guid)
)
GO

CREATE TABLE Tag
(
    IDTag INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL UNIQUE
)
GO

CREATE TABLE NasaImageTag
(
    IDNasaImage INT NOT NULL,
    IDTag INT NOT NULL,
    CONSTRAINT PK_NasaImageTag PRIMARY KEY (IDNasaImage, IDTag),
    CONSTRAINT FK_NasaImageTag_NasaImage FOREIGN KEY (IDNasaImage)
        REFERENCES NasaImage (IDNasaImage) ON DELETE CASCADE,
    CONSTRAINT FK_NasaImageTag_Tag FOREIGN KEY (IDTag)
        REFERENCES Tag (IDTag) ON DELETE CASCADE
)
GO

/* -------------------------------------------------------------------------- */
/* AppUser procedures                                                         */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE createUser
    @Username NVARCHAR(100),
    @PasswordHash NVARCHAR(256),
    @PasswordSalt NVARCHAR(128),
    @Role NVARCHAR(20),
    @IDAppUser INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    /* PasswordHash and PasswordSalt are produced in Java (PasswordUtils, PBKDF2). SQL stores only. */
    INSERT INTO AppUser (Username, PasswordHash, PasswordSalt, Role)
    VALUES (@Username, @PasswordHash, @PasswordSalt, @Role);

    SET @IDAppUser = SCOPE_IDENTITY();
END
GO

CREATE PROCEDURE selectUserByUsername
    @Username NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    /* Java loads hash + salt, then PasswordUtils.verify(plainPassword, hash, salt). No password check in SQL. */
    SELECT
        IDAppUser,
        Username,
        PasswordHash,
        PasswordSalt,
        Role
    FROM AppUser
    WHERE Username = @Username;
END
GO

CREATE PROCEDURE selectUser
    @IDAppUser INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDAppUser,
        Username,
        PasswordHash,
        PasswordSalt,
        Role
    FROM AppUser
    WHERE IDAppUser = @IDAppUser;
END
GO

CREATE PROCEDURE selectUsers
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDAppUser,
        Username,
        PasswordHash,
        PasswordSalt,
        Role
    FROM AppUser
    ORDER BY Username;
END
GO

/* -------------------------------------------------------------------------- */
/* NasaImage procedures (Exercise 07-08 Article pattern)                        */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE createNasaImage
    @Title NVARCHAR(300),
    @Link NVARCHAR(500),
    @Description NVARCHAR(900),
    @PicturePath NVARCHAR(260),
    @PublishedDate NVARCHAR(90),
    @Guid NVARCHAR(500) = NULL,
    @IDNasaImage INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    IF @Guid IS NOT NULL AND EXISTS (SELECT 1 FROM NasaImage WHERE Guid = @Guid)
    BEGIN
        RAISERROR ('NasaImage with the same Guid already exists.', 16, 1);
        RETURN;
    END

    INSERT INTO NasaImage (Title, Link, Description, PicturePath, PublishedDate, Guid)
    VALUES (@Title, @Link, @Description, @PicturePath, @PublishedDate, @Guid);

    SET @IDNasaImage = SCOPE_IDENTITY();
END
GO

CREATE PROCEDURE updateNasaImage
    @IDNasaImage INT,
    @Title NVARCHAR(300),
    @Link NVARCHAR(500),
    @Description NVARCHAR(900),
    @PicturePath NVARCHAR(260),
    @PublishedDate NVARCHAR(90),
    @Guid NVARCHAR(500) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    IF @Guid IS NOT NULL
       AND EXISTS (
           SELECT 1
           FROM NasaImage
           WHERE Guid = @Guid
             AND IDNasaImage <> @IDNasaImage
       )
    BEGIN
        RAISERROR ('Another NasaImage already uses this Guid.', 16, 1);
        RETURN;
    END

    UPDATE NasaImage
    SET
        Title = @Title,
        Link = @Link,
        Description = @Description,
        PicturePath = @PicturePath,
        PublishedDate = @PublishedDate,
        Guid = @Guid
    WHERE IDNasaImage = @IDNasaImage;
END
GO

CREATE PROCEDURE deleteNasaImage
    @IDNasaImage INT
AS
BEGIN
    SET NOCOUNT ON;

    /* NasaImageTag rows removed by ON DELETE CASCADE */
    DELETE FROM NasaImage
    WHERE IDNasaImage = @IDNasaImage;
END
GO

CREATE PROCEDURE selectNasaImage
    @IDNasaImage INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDNasaImage,
        Title,
        Link,
        Description,
        PicturePath,
        PublishedDate,
        Guid
    FROM NasaImage
    WHERE IDNasaImage = @IDNasaImage;
END
GO

CREATE PROCEDURE selectNasaImageByGuid
    @Guid NVARCHAR(500)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDNasaImage,
        Title,
        Link,
        Description,
        PicturePath,
        PublishedDate,
        Guid
    FROM NasaImage
    WHERE Guid = @Guid;
END
GO

CREATE PROCEDURE selectNasaImages
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDNasaImage,
        Title,
        Link,
        Description,
        PicturePath,
        PublishedDate,
        Guid
    FROM NasaImage
    ORDER BY PublishedDate DESC, IDNasaImage DESC;
END
GO

/* -------------------------------------------------------------------------- */
/* Tag procedures                                                             */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE createTag
    @Name NVARCHAR(100),
    @IDTag INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Tag (Name)
    VALUES (@Name);

    SET @IDTag = SCOPE_IDENTITY();
END
GO

CREATE PROCEDURE updateTag
    @IDTag INT,
    @Name NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Tag
    SET Name = @Name
    WHERE IDTag = @IDTag;
END
GO

CREATE PROCEDURE deleteTag
    @IDTag INT
AS
BEGIN
    SET NOCOUNT ON;

    /* NasaImageTag rows removed by ON DELETE CASCADE */
    DELETE FROM Tag
    WHERE IDTag = @IDTag;
END
GO

CREATE PROCEDURE selectTag
    @IDTag INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDTag,
        Name
    FROM Tag
    WHERE IDTag = @IDTag;
END
GO

CREATE PROCEDURE selectTags
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDTag,
        Name
    FROM Tag
    ORDER BY Name;
END
GO

/* -------------------------------------------------------------------------- */
/* Relationship procedures                                                    */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE linkNasaImageTag
    @IDNasaImage INT,
    @IDTag INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM NasaImage WHERE IDNasaImage = @IDNasaImage)
    BEGIN
        RAISERROR ('NasaImage does not exist.', 16, 1);
        RETURN;
    END

    IF NOT EXISTS (SELECT 1 FROM Tag WHERE IDTag = @IDTag)
    BEGIN
        RAISERROR ('Tag does not exist.', 16, 1);
        RETURN;
    END

    IF NOT EXISTS (
        SELECT 1
        FROM NasaImageTag
        WHERE IDNasaImage = @IDNasaImage
          AND IDTag = @IDTag
    )
    BEGIN
        INSERT INTO NasaImageTag (IDNasaImage, IDTag)
        VALUES (@IDNasaImage, @IDTag);
    END
END
GO

CREATE PROCEDURE unlinkNasaImageTag
    @IDNasaImage INT,
    @IDTag INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM NasaImageTag
    WHERE IDNasaImage = @IDNasaImage
      AND IDTag = @IDTag;
END
GO

CREATE PROCEDURE selectTagsForNasaImage
    @IDNasaImage INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        t.IDTag,
        t.Name
    FROM Tag t
    INNER JOIN NasaImageTag nit ON nit.IDTag = t.IDTag
    WHERE nit.IDNasaImage = @IDNasaImage
    ORDER BY t.Name;
END
GO

CREATE PROCEDURE selectNasaImagesForTag
    @IDTag INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        ni.IDNasaImage,
        ni.Title,
        ni.Link,
        ni.Description,
        ni.PicturePath,
        ni.PublishedDate,
        ni.Guid
    FROM NasaImage ni
    INNER JOIN NasaImageTag nit ON nit.IDNasaImage = ni.IDNasaImage
    WHERE nit.IDTag = @IDTag
    ORDER BY ni.PublishedDate DESC, ni.IDNasaImage DESC;
END
GO

/* -------------------------------------------------------------------------- */
/* Lifecycle procedures                                                       */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE deleteImportedData
AS
BEGIN
    SET NOCOUNT ON;

    /*
     * Admin RSS reload (PDF): remove image-tag links and imported NasaImage rows only.
     * Tags and AppUser are kept. Java must also clear assets/ image files.
     */
    DELETE FROM NasaImageTag;
    DELETE FROM NasaImage;
END
GO

CREATE PROCEDURE resetApplicationData
AS
BEGIN
    SET NOCOUNT ON;

    /*
     * Resets application content: images, links, and all tags.
     * AppUser accounts are preserved (no lock-out).
     * Restores default demo tags after wipe.
     */
    DELETE FROM NasaImageTag;
    DELETE FROM NasaImage;
    TRUNCATE TABLE Tag; /* resets IDTag identity so seed starts at 1 */

    EXEC seedDefaultTags;
END
GO

CREATE PROCEDURE deleteAllData
AS
BEGIN
    SET NOCOUNT ON;

    /*
     * PDF "delete all data" for application domain tables.
     * Does NOT delete AppUser rows (login safety — admin is never removed).
     * Wipes images/links/tags, reseeds default tags and ensures default admin row exists.
     * Java must clear assets/. AppUser rows are preserved; admin seed uses PasswordUtils hash/salt.
     */
    DELETE FROM NasaImageTag;
    DELETE FROM NasaImage;
    TRUNCATE TABLE Tag; /* resets IDTag identity so seed starts at 1 */

    EXEC seedDefaultTags;
    EXEC seedDefaultAdmin;
END
GO

/* -------------------------------------------------------------------------- */
/* Seed procedures (PDF: admin after init; default tags for demo)             */
/* -------------------------------------------------------------------------- */

CREATE PROCEDURE seedDefaultAdmin
AS
BEGIN
    SET NOCOUNT ON;

    /*
     * Seeded hash/salt generated by hr.algebra.utilities.PasswordUtils (PBKDF2WithHmacSHA256).
     * Password plaintext is NOT stored. Regenerate with: java ... PasswordUtils <password>
     * Login: Java selectUserByUsername + PasswordUtils.verifyPassword.
     */
    IF NOT EXISTS (SELECT 1 FROM AppUser WHERE Username = N'admin')
    BEGIN
        DECLARE @IDAppUser INT;

        EXEC createUser
            @Username = N'admin',
            @PasswordHash = N'sIEDCV4rYpkuizQPPRyGnXXQ7LNjNUgdVsivADRoYWA=',
            @PasswordSalt = N'NKeGnrQp7GKyeVKOyTE3bA==',
            @Role = N'ADMIN',
            @IDAppUser = @IDAppUser OUTPUT;
    END
END
GO

CREATE PROCEDURE seedDefaultTags
AS
BEGIN
    SET NOCOUNT ON;

    /*
     * If Tag is empty (e.g. manual DELETE), reset identity so new rows get IDTag 1, 2, 3...
     * resetApplicationData / deleteAllData use TRUNCATE TABLE Tag for the same effect.
     */
    IF NOT EXISTS (SELECT 1 FROM Tag)
    BEGIN
        DBCC CHECKIDENT (N'Tag', RESEED, 0);
    END

    DECLARE @Names TABLE (Name NVARCHAR(100));
    DECLARE @IDTag INT;

    INSERT INTO @Names (Name) VALUES
        (N'Mars'),
        (N'Moon'),
        (N'Webb'),
        (N'Earth'),
        (N'Artemis'),
        (N'Hubble'),
        (N'Orion'),
        (N'Space Station');

    DECLARE tag_cursor CURSOR LOCAL FAST_FORWARD FOR
        SELECT Name FROM @Names;

    DECLARE @Name NVARCHAR(100);

    OPEN tag_cursor;
    FETCH NEXT FROM tag_cursor INTO @Name;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM Tag WHERE Name = @Name)
        BEGIN
            EXEC createTag @Name = @Name, @IDTag = @IDTag OUTPUT;
        END

        FETCH NEXT FROM tag_cursor INTO @Name;
    END

    CLOSE tag_cursor;
    DEALLOCATE tag_cursor;
END
GO

/* -------------------------------------------------------------------------- */
/* Initial seed (run once with this script)                                   */
/* -------------------------------------------------------------------------- */

EXEC seedDefaultAdmin
GO

EXEC seedDefaultTags
GO
