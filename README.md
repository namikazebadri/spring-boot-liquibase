# Spring Boot Liquibase

This is a project to explore and demonstrate the use of liquibase as migration tools in a spring boot project. 
This project consist of these points:

1. Spring Boot and Liquibase setup
2. Creating table
3. Dropping table
4. Add unique constraint
5. Creating index
6. Dropping index
7. Add Column
8. Change column
9. Drop Column

## Prepare Configuration

Set Environment variables for database connection (this is used by app when running):

```shell
DB_URL=
DB_SCHEMA=
DB_USERNAME=
DB_PASSWORD=
```

Rename `gradle.properties.example` to `gradle.properties`, then set the values for database connection:

```properties
liquibaseDbUrl=
liquibaseDbUsername=
liquibaseDbPass=
liquibaseDbSchema=
```

properties in `gradle.properties` used by liquibase migration, since we run migration in separate process from running application.

## Running Liquibase

We can run liquibase migration by executing this command:

```shell
./gradlew update
```