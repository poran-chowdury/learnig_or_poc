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