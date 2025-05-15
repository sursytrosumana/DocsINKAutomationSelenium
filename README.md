# DocsINK (Basic Registration Test)

## Overview
This is a starter Selenium Java framework using Page Object Model (POM) for OrangeHRM demo site. It includes a basic login test and is ready for CI/CD with GitHub Actions.

## Prerequisites
- Java 11 or higher
- Maven
- Chrome browser

## How to Run
1. Clone the repository or unzip this project.
2. Update `src/main/resources/config.properties` if needed.
3. Run tests:
   ```sh
   mvn clean test
   ```

## Project Structure
- `src/main/java/com/orangehrm/pages/` - Page Objects
- `src/test/java/com/orangehrm/tests/` - Test Classes
- `src/main/resources/config.properties` - Configurations
- `.github/workflows/selenium-tests.yml` - GitHub Actions CI

## Credentials
- Username: admin@diagonal.software
- Password: 2b11099c60fb7fb093a2D@

## CI/CD
Tests run automatically on push via GitHub Actions.
