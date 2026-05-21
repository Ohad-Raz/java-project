/*
 * Java Programming 1 — Final Project
 * Data reset script — stored procedures only (Exercise 07-08 style).
 *
 * Procedure purposes:
 *   deleteImportedData     — RSS images + NasaImageTag links only (admin reload feed)
 *   resetApplicationData   — images + links + all tags; reseeds default tags; keeps AppUser
 *   deleteAllData          — same content wipe + reseed tags + ensure admin row;
 *                            AppUser rows are NEVER deleted (login safety)
 *
 * Java must delete assets/ files when clearing imported images.
 */

USE NASA_IOTD
GO

/* Default for 02_delete_data.sql (PDF delete-all-data script entry point): full application reset */
EXEC deleteAllData
GO

/*
 * Alternative: RSS import only (typical admin action before parser reload):

EXEC deleteImportedData
GO
*/

/*
 * Alternative: reset content + default tags, keep users:

EXEC resetApplicationData
GO
*/
