# JMeter Java DSL Load Testing Framework

A comprehensive load testing framework built with JMeter Java DSL for API performance testing. This project provides a programmatic approach to creating and executing JMeter test plans using Java, making test automation more maintainable and version-control friendly.

## 📋 Overview

This project implements load testing for CIAM (Customer Identity and Access Management) APIs, specifically focusing on user registration and authentication flows. It uses the JMeter Java DSL library to define test plans programmatically, eliminating the need for XML-based JMX files.

## ✨ Features

- **Programmatic Test Definition**: Define JMeter test plans using Java code instead of XML
- **Multiple Environment Support**: Pre-configured for SIT, UAT, UVMC Test, and UVMC SIT environments
- **CSV Data-Driven Testing**: Load test data from CSV files for realistic user scenarios
- **Comprehensive API Coverage**: Tests complete registration and authentication flows
- **HTML Reporting**: Generates detailed HTML reports with performance metrics
- **Encryption Support**: Built-in support for AES/RSA encryption in API requests
- **Transaction Tracking**: Organized test scenarios with transaction controllers
- **Flexible Configuration**: Easy environment switching and parameter customization

## 🔧 Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6 or higher
- **JMeter**: 5.6.3 (managed via Maven dependencies)

## 📦 Installation

1. **Clone the repository**:

   ```bash
   git clone <repository-url>
   cd Jmeter_Java/jmeter-java-project
   ```

2. **Install dependencies**:

   ```bash
   mvn clean install
   ```

3. **Verify installation**:

   ```bash
   mvn compile
   ```

## 🚀 Usage

### Running Tests

#### Using the Shell Script (Recommended)

```bash
cd jmeter-java-project
chmod +x run-test.sh
./run-test.sh
```

#### Using Maven Directly

```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.loadtest.JMeterRunner"
```

### Test Output

After execution, check the following files for results:

- **`uvmc-results.jtl`**: Raw test results in JTL format
- **`uvmc-html-report/`**: HTML dashboard with detailed metrics
- **`html-report/`**: Alternative HTML report location

## 📁 Project Structure

```text
jmeter-java-project/
├── src/main/java/com/example/loadtest/
│   ├── apis/
│   │   ├── RegistrationUVMCApiTest.java    # Main UVMC registration test
│   │   ├── RegistrationApiTest.java        # Standard registration test
│   │   ├── LoginApiTest.java               # Login flow test
│   │   └── SingleRequestTest.java          # Single API request test
│   ├── config/
│   │   ├── TestConfig.java                 # Environment configurations
│   │   └── ApiConstants.java               # API endpoint constants
│   ├── model/
│   │   ├── UserAutoSignInRequest.java      # Auto sign-in request model
│   │   ├── RegistrationInitRequest.java    # Registration init model
│   │   ├── ProfileRequest.java             # User profile model
│   │   ├── PasswordEncryptRequest.java     # Password encryption model
│   │   ├── NotificationTokenRequest.java   # Notification token model
│   │   └── UpdateDeviceRequest.java        # Device update model
│   ├── data/
│   │   └── TestData.java                   # Test data utilities
│   └── JMeterRunner.java                   # Main test runner
├── export_20260112.csv                     # Test data CSV file
├── user.properties                         # JMeter user properties
├── run-test.sh                             # Test execution script
├── pom.xml                                 # Maven configuration
└── .gitignore                              # Git ignore rules
```

## ⚙️ Configuration

### Environment Selection

The project supports multiple environments configured in [`TestConfig.java`](jmeter-java-project/src/main/java/com/example/loadtest/config/TestConfig.java):

- **UVMC SIT**: `TestConfig.uvmcSitConfig()` (Default)
- **UVMC Test**: `TestConfig.uvmcTestConfig()`
- **SIT**: `TestConfig.sitConfig()`
- **UAT**: `TestConfig.uatConfig()`

To change environments, modify the configuration in [`RegistrationUVMCApiTest.java`](jmeter-java-project/src/main/java/com/example/loadtest/apis/RegistrationUVMCApiTest.java):

```java
TestConfig config = TestConfig.uvmcSitConfig(); // Change this line
```

### Test Parameters

Key configuration parameters in `TestConfig`:

| Parameter | Description | Default |
| :-------- | :---------- | :------ |
| `baseUrl` | API base URL | Environment-specific |
| `tpsOnboarding` | Transactions per second | 2 |
| `targetThroughput` | Requests per minute | 60.0 |
| `appVersion` | Application version | 3.1.0 |
| `notificationLimit` | Notification limit | 100 |

### CSV Test Data

The test uses [`export_20260112.csv`](jmeter-java-project/export_20260112.csv) for data-driven testing. The CSV should contain:

- `email`: User email addresses
- `onboardingId`: Unique onboarding identifiers

## 🧪 Test Scenarios

### UVMC Registration Flow

The main test ([`RegistrationUVMCApiTest.java`](jmeter-java-project/src/main/java/com/example/loadtest/apis/RegistrationUVMCApiTest.java)) covers:

1. **Get Registration URL**: Retrieves Singpass registration URL and public key
2. **User Auto Sign-In**: Authenticates user and obtains access token
3. **Get Current User**: Fetches current user profile
4. **Wallet Registration**: Registers user wallet (conditional)
5. **Update Device**: Updates device information
6. **Update Notification Token**: Registers notification token
7. **Update Profile**: Updates user profile information
8. **Logout**: Ends user session

### Transaction Metrics

Tests are organized with transaction controllers for accurate performance measurement:

- Response times
- Throughput (requests/second)
- Error rates
- Percentile response times (90th, 95th, 99th)

## 📊 Reporting

### HTML Dashboard

The HTML report includes:

- **Statistics**: Min, max, average, median response times
- **Graphs**: Response time over time, throughput, latency
- **Error Analysis**: Failed requests and error messages
- **Percentiles**: 90th, 95th, 99th percentile response times

### JTL Results

Raw results in JTL format can be:

- Imported into JMeter GUI for analysis
- Processed with custom scripts
- Integrated with CI/CD pipelines

## 🔐 Security Features

- **RSA/AES Encryption**: Supports encrypted API requests
- **Token Management**: Automatic access token extraction and usage
- **Secure Headers**: Configurable security headers (NETS-APP-SECRET)

## 🛠️ Development

### Adding New Tests

1. Create a new test class in `src/main/java/com/example/loadtest/apis/`
2. Extend the test structure using JMeter DSL
3. Update `JMeterRunner.java` to include the new test
4. Add any required models in the `model/` package

### Modifying Existing Tests

1. Locate the test in the `apis/` directory
2. Modify the test plan using JMeter DSL methods
3. Update configuration in `TestConfig.java` if needed
4. Recompile and run tests

## 📚 Dependencies

- **Apache JMeter Core**: 5.6.3
- **Apache JMeter HTTP**: 5.6.3
- **JMeter Java DSL**: 2.2
- **Gson**: 2.10.1 (JSON serialization)
- **SLF4J**: 2.0.9 (Logging)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📝 License

[Specify your license here]

## 📧 Contact

[Add contact information or team details]

---

**Last Updated**: January 2026
