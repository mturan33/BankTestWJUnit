
# BankTestWJUnit

This project implements a `Bank` interface in Java, complete with JUnit test cases to verify the functionality of various banking operations. The test suite, using JUnit and the JaCoCo Maven plugin, provides code coverage reports to ensure robust test coverage. This project serves as an example of unit testing in Java with detailed test cases for core banking methods.

## Project Structure

The main components of the project are:
- **Bank Interface**: Defines core banking operations.
- **BankImpl Class**: Implements the `Bank` interface.
- **BankTest Suite**: JUnit test cases for `BankImpl`.
- **JaCoCo Coverage Report**: Provides test coverage metrics for all methods.

## Features

- **Account Management**: Create, find, and delete accounts.
- **Transaction Processing**: Deposit, withdraw, and transfer funds between accounts.
- **JUnit Testing**: Comprehensive tests for each method in `BankImpl`.
- **Code Coverage Reports**: JaCoCo integration to generate coverage reports, ensuring each method is tested.

## Getting Started

### Prerequisites

- Java 8 or later
- Maven 3.6+
- JUnit 5.7+
- JaCoCo Plugin

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mturan33/BankTestWJUnit.git
   ```
2. Navigate to the project directory:
   ```bash
   cd BankTestWJUnit
   ```

### Running Tests

To run the tests and generate the JaCoCo coverage report, use:
```bash
mvn clean test
```

### Viewing Code Coverage

After running the tests, the coverage report can be found in:
```bash
target/site/jacoco/index.html
```
Open this file in your browser to view the detailed coverage results.

## Test Coverage

The project aims for comprehensive coverage, with test cases for the following methods:
- **createAccount**: Validates account creation.
- **findAccount**: Checks retrieval of accounts by ID.
- **deposit**: Verifies deposits are added correctly.
- **withdraw**: Ensures withdrawals handle balances properly.
- **transfer**: Tests funds transfer between accounts, ensuring proper balances.

## Contributing

Feel free to contribute by creating issues or pull requests. Please follow the standard code style and ensure that all new code includes JUnit tests.

## License

This project is licensed under the MIT License. See `LICENSE` for more details.
