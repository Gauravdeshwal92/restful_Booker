# RestAssuredWithJavaTestFramework

This repository contains an API automation framework built with Java, Maven, TestNG, RestAssured, and Allure reporting.

## Overview

- Uses `RestAssured` for HTTP API requests and validation.
- Uses `TestNG` to manage test execution and suites.
- Uses `Allure` for test reporting.
- Includes a sample test class at `src/test/java/com/framework/tests/BookingTest.java`.

## Prerequisites

- Java JDK 8
- Maven 3.x
- Git (optional)

## Project Structure

- `pom.xml` - Maven build configuration and dependencies.
- `testng.xml` - TestNG suite configuration.
- `src/test/java/com/framework/tests/` - API test classes.
- `target/` - Maven build output.
- `allure-results/` - generated Allure result files.

## Common Commands

Run the full TestNG suite:

```bash
mvn test
```

Run tests with the specific `testng.xml` suite:

```bash
mvn -Dsurefire.suiteXmlFiles=testng.xml test
```

Generate an Allure report after test execution:

```bash
mvn allure:report
```

Open the generated Allure report locally:

```bash
mvn allure:serve
```

## Notes

- Allure results are written into `target/allure-results` or `allure-results` depending on plugin settings.
- The project uses `aspectjweaver` as a runtime agent for enhanced reporting/tracing.
- Update `testng.xml` to add more test classes or configure groups.

## Contribution

1. Add or update API test classes under `src/test/java/com/framework/tests/`.
2. Add any custom listeners or utilities under `src/main/java/com/framework/`.
3. Update `testng.xml` to include new test classes or suite configuration.
