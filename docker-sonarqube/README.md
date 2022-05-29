# sonarqube-poc

## How to use SonarQube
 * **step 1:** if docker is not running, please run docker: ```docker-compose up -d --build```
 * **step 2:** goto: http://localhost:9000/sessions/new
 * **step 3:** user:password=admin:admin
 * **step 4:** goto: http://localhost:9000/account/security/
 * **step 5:** generate token using a token name and save the token
 * **step 6:** to check report goto: http://localhost:9000/projects

## Install Sonar-Scanner in Ubuntu
* **step 1:** 
```
wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.7.0.2747-linux.zip
unzip sonar-scanner-cli-4.7.0.2747-linux.zip
mv sonar-scanner-4.7.0.2747-linux /opt/sonar-scanner
rm sonar-scanner-cli-4.7.0.2747-linux.zip
```
* **step 2:**
```
vi /opt/sonar-scanner/conf/sonar-scanner.properties
```
* **step 3:**
```
sonar.host.url=http://localhost:9000
sonar.sourceEncoding=UTF-8
```
* **step 4:**
```
vi /etc/profile.d/sonar-scanner.sh
```
* **step 5:**
```
#/bin/bash
export PATH="$PATH:/opt/sonar-scanner/bin"
```
* **step 6:**
```
source /etc/profile.d/sonar-scanner.sh
```
* **step 7:**
```
sonar-scanner -v
```
* **step 8:**
```
sonar-scanner \
  -Dsonar.projectKey=<project_key> \
  -Dsonar.sources=. \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<token>
```