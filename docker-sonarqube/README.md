# sonarqube-poc

#### How to use SonarQube
 * **step 1:** if docker is not running, please run docker: ```docker-compose up -d --build```
 * **step 2:** goto: http://localhost:9000/sessions/new
 * **step 3:** login:password=admin:admin
 * **step 4:** goto: http://localhost:9000/account/security/
 * **step 5:** generate token using a token name and save the token
 * **step 5:** run: ```$ ./maven-clean-install <token>```
 * **or** run: ```mvn clean install  sonar:sonar  -Dsonar.host.url=http://localhost:9000   -Dsonar.login=<token>```
 * **step 6:** to check report goto: http://localhost:9000/projects


#### For Gradle project please follow below instructions

** Add below plugins in ```build.gradle``` file
``` 
plugins {
    id "org.sonarqube" version "2.6"
}
```

*** Use below command for running the project with sonar qube
```./gradlew sonarqube -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token>```