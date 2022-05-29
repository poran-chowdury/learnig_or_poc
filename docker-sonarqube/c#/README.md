# sonarqube-poc-C# or .Net

## Change the Dockerfile
* **Line 8,9,10:** Please put SonarQube ```Token```, ```Project Key``` (not required), ```Host URL```
* **Line 18,20:** Depending on project requirement add extra config files for NuGet Config and comment out the lines
* **Line 42:** Please add the directory name depending on project holding the ```csproj``` file
* **Line 47,48:** Please add the directory name depending on project holding the ```csproj``` file
* **Line 60:** Please modify the ```dll``` file name depending on project ```AssemblyName``` i.e. line 59


## How to use SonarQube
* **step 1:** if docker is not build, please run: ```docker build .```
* **step 2:** goto: http://localhost:9000/sessions/new
* **step 3:** login:password=admin:admin
* **step 4:** goto: http://localhost:9000/account/security/
* **step 5:** generate token using a token name and save the token
* **step 6:** to check report goto: http://localhost:9000/projects