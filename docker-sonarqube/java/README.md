# sonarqube-poc-JAVA

## For maven project please follow below instructions
* step 1: Use below command for running the project with sonarqube
`mvn clean install sonar:sonar -Dsonar.host.url=https://localhost:9000 -Dsonar.login=<token>`
* step 2: to check report goto: https://localhost:9000/projects

## For Gradle project please follow below instructions
* **step 1:** Add below plugins in `build.gradle` file plugins 
```
{
    id "org.sonarqube" version "2.6"
}
```
* **step 2:** Use below command for running the project with sonarqube
`./gradlew sonarqube -Dsonar.host.url=https://localhost:9000 -Dsonar.login=<token>`
* **step 3:** to check report goto: https://localhost:9000/projects