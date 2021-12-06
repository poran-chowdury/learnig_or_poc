# ts4u_blog_api_automation

### Summary
API framework test APIs

**Tool Stack:**
- **_Programming Language:_** Java
- **_Test Runner:_** Junit-5
- **_API Client:_** REST-Assured
- **_Build tool:_** Maven
- **_Reporting Tool:_** Allure

**Project Setup:**
- Install **Maven** and Import as Maven project in IntelliJ or Eclipse
- Install **Lombok** plugin within the IDE (IntelliJ or Eclipse)
- In order to generate a report, we should install Allure command-line interpreter using ```npm install -g allure-commandline```
- Download the latest version as a zip archive from binary: https://bintray.com/qameta/generic/allure2/2.7.0#files/io%2Fqameta%2Fallure%2Fallure%2F2.7.0
- Unpack the archive.
- Navigate to the bin directory.
- Add allure’s bin directory to system Path variable.

**Framework Structure:**
- **_dto:_** POJOs corresponding to API responses
- **_entity:_** Entity classes for collections
- **_enums:_** Enum classes for collections
- **_httprequests:_** All API Requests, Endpoints and Headers
- **_providers:_** De-serialized json mapped to provider methods
- **_reqquestBody:_** Dynamic request body for APIs
- **_service:_** All test cases classes
- **_stepDefs:_** Classes containing functions used in test cases
- **_utils:_**  Commonly used methods
- **_features:_** All test features commands
- **_custom.properties:_** All api endpoints

**Command to run all scenarios:**
   ```bash
     mvn clean test
   ```

**Command to run a specific feature:**
   ```bash
     mvn clean test -Dtest=<test class name>"
   ```
**Report Generation command: Allure**
   ```bash
    allure generate -c target/allure-results
   ```

**Report Open command: Allure**
   ```bash
    allure open
   ```

**Report Generation and Open command: Allure**
   ```bash
    allure serve target/allure-results
   ```


**Maven Libraries:**
- **_cucumber-java:_** Cucumber BDD Java client
- **_cucumber-junit:_** Cucumber BDD JUnit client
- **_rest-assured:_** REST API test automation
- **_json-simple:_**  JSON data read library
- **_allure-cucumber4-jvm:_** Allure reporting library
- **_groovy:_** Groovy library support
- **_hamcrest-all:_** Supporting library for data read
- **_gson:_** Supporting library for data read
- **_commons-io:_**  Supporting library for common input output actions
- **_mongodb:_**  Supporting library for database commands
