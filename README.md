# Java Programming 1 — Final Project

NASA Image of the Day Manager is a multi-module Maven Swing application aligned with Exercise 07-08 / 09-10 / 11-12 patterns and the final-project PDF requirements.

## Module structure

- `Modules/Utilities` — reusable utilities (`MessageUtils`, `FileUtils`, `IconUtils`, `JAXBUtils`, parser/connection factories)
- `Modules/Dao` — models, repository abstractions, SQL Server access, stored-procedure calls, SQL scripts
- `Modules/JavaProjectApp` — Swing GUI, controllers/session, RSS parser, services, view models

## Prerequisites

- Java 17+
- Maven 3.x
- Microsoft SQL Server

## Database setup

1. Create database objects by running:
   - `Modules/Dao/src/main/resources/sql/01_init_database.sql`
2. Optional full cleanup/reset script:
   - `Modules/Dao/src/main/resources/sql/02_delete_data.sql`
3. Configure local DB connection:
   - copy `Modules/Dao/src/main/resources/config/db.properties.example`
   - to `Modules/Dao/src/main/resources/config/db.properties`
   - edit values for your SQL Server environment

## Build and run

From project root (`Java_Project/`):

```bash
mvn clean install
mvn -pl Modules/JavaProjectApp exec:java
```

Main class: `hr.algebra.Main`

## Main features

- Login and role-based access (`ADMIN`, `USER`)
- User registration (USER role)
- Admin RSS import and clear workflow
- Image download to runtime `assets/` directory and relative path persistence
- NASA image CRUD with table model (`NasaImageTableModel`)
- Tag CRUD with table model (`TagTableModel`)
- Image-tag assignment with Drag and Drop (`TransferHandler`/`Transferable`)
- JAXB XML export (all images or selected image) with assigned tag names

## Default admin note

The initialization SQL seeds an `admin` account using a pre-generated password hash and salt.  
Use your local course-provided/admin-agreed credentials for login and regenerate seed hash/salt locally when needed.

## Local-only and ignored files

The following are intentionally local/ignored and should not be committed:

- `Modules/Dao/src/main/resources/config/db.properties`
- `_course_reference/`
- `/assets/` (runtime downloaded images)
- `PROJECT_HANDOFF.md`
- `target/`

`Modules/JavaProjectApp/src/main/resources/assets/no_image.png` is an application resource and should remain tracked.

## Safety reminder

Never commit local secrets, runtime-downloaded images, or professor reference materials.
