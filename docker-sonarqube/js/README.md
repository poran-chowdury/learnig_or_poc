# sonarqube-poc-JS

## How to use SonarQube
* **Step 1:** Add sonar-project.properties file in the project directory. Below is a sample properties file:
```
sonar.host.url=http://localhost:9000
sonar.login=admin
sonar.password=admin
sonar.projectKey=ecap-angular-project
sonar.projectName=ecap-angular-project
sonar.projectVersion=1.0
sonar.sourceEncoding=UTF-8
sonar.sources=src
sonar.exclusions=**/node_modules/**
sonar.tests=src
sonar.test.inclusions=**/*.spec.ts
sonar.typescript.lcov.reportPaths=coverage/lcov.info
```
* **Step 2:**  add below script in package.json file
```
"scripts": {
"sonar": "sonar-scanner"
}
```
* **Step 3:** Execute the command `npm run sonar`
