# Java Programming 1 — Final Project

Multi-module Maven Swing application (Algebra course), structured like Exercise 07-08:

| Module | Role |
|--------|------|
| **Utilities** | `MessageUtils`, `FileUtils`, `IconUtils`, `JAXBUtils`, `ParserFactory`, `UrlConnectionFactory` |
| **Dao** | Model, `Repository` pattern, `DataSourceSingleton`, SQL config |
| **JavaProjectApp** | Swing GUI, MVC-style controller, RSS parser placeholder (`parsers/rss`) |

## Requirements

- Java 17+
- Maven 3.x
- Microsoft SQL Server (for later phases)

## Database config

Copy the example file and edit locally (not committed):

```text
Modules/Dao/src/main/resources/config/db.properties.example
  → Modules/Dao/src/main/resources/config/db.properties
```

## Build

From this directory (`Java_Project/`):

```bash
mvn clean install
```

## Run

```bash
mvn -pl Modules/JavaProjectApp exec:java
```

Main class: `hr.algebra.Main`

## Branches

- `main` — stable project structure
- `dev` — active development

## Setup status

Setup phase only: placeholders for RSS feed, entities, DDL/stored procedures, and full CRUD. See source `Project setup note` comments for Exercise 07-08 / 09-10 / 11-12 references.
