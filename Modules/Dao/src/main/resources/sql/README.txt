SQL scripts — NASA_IOTD (Exercise 07-08 style)

Run order:
  1. 01_init_database.sql
  2. 02_delete_data.sql  (default: EXEC deleteAllData)

Passwords (AppUser):
  - PasswordHash + PasswordSalt stored in SQL; hashing only in Java (PasswordUtils, PBKDF2WithHmacSHA256).
  - Login: CALL selectUserByUsername → Java verifies password with salt.
  - authenticateUser was removed (no password comparison in SQL).
  - seedDefaultAdmin uses PBKDF2 hash/salt from PasswordUtils (admin password set at seed generation time).

Delete procedures:
  - deleteImportedData      — NasaImageTag + NasaImage only
  - resetApplicationData    — + Tag wipe, then seedDefaultTags; AppUser preserved
  - deleteAllData           — content wipe + seedDefaultTags + seedDefaultAdmin; AppUser preserved

Database: NASA_IOTD
