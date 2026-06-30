/*
 Data reset script
*/

USE NASA_IOTD
GO

-- Default reset used for project cleanup.
EXEC deleteAllData
GO

/*
Alternative reset options:

-- Clear imported RSS images only:
EXEC deleteImportedData
GO

-- Reset images/tags while keeping application users:
EXEC resetApplicationData
GO
*/