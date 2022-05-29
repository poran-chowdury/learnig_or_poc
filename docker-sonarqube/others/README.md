# sonarqube-poc-other 
#### i.e. Solidity,

## How to use SonarQube
* **Step 1:** Goto the project directory and run below codes
```
sonar-scanner \
  -Dsonar.projectKey=<project_key> \
  -Dsonar.sources=. \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<token>
```
* **Step 2:**  to check report goto: http://localhost:9000/projects