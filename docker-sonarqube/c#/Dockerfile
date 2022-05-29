#############
## Stage 1 ##
#############
FROM mcr.microsoft.com/dotnet/sdk:5.0-buster-slim AS build-env
WORKDIR /app

## Arguments for setting the Sonarqube
ENV SONAR_TOKEN "<token>"
ENV SONAR_PRJ_KEY "<project_key>"
ENV SONAR_HOST "<sonarqube_host>"

## Install Java, because the sonarscanner needs it.
RUN rm -rf /usr/share/man/man1/
RUN mkdir -p /usr/share/man/man1/
RUN apt-get update && apt-get dist-upgrade -y && apt-get install -y openjdk-11-jre

## Make a dir for NuGet
#RUN mkdir -p /root/.nuget/NuGet
## Copy NuGet Config
#COPY ./config/NuGetPackageSource.Config /root/.nuget/NuGet/NuGet.Config

## Install sonarscanner
RUN dotnet tool install --global dotnet-sonarscanner --version 5.3.3

## Install report generator
RUN dotnet tool install --global dotnet-reportgenerator-globaltool --version 5.1.9

## Set the dotnet tools folder in the PATH env variable
ENV PATH="${PATH}:/root/.dotnet/tools"

## Start scanner
RUN dotnet sonarscanner begin \
	/o:"$SONAR_ORG" \
	/k:"$SONAR_PRJ_KEY" \
	/d:sonar.host.url="$SONAR_HOST" \
	/d:sonar.login="$SONAR_TOKEN"

## Copy the applications .csproj
COPY /src ./src

## Restore packages
RUN dotnet restore src/<dir_holding_csproj_file>/

## Copy everything else
COPY . ./

RUN dotnet build src/<dir_holding_csproj_file>/*.csproj
RUN dotnet publish src/<dir_holding_csproj_file>/*.csproj -o /app/publish

## Stop scanner
RUN dotnet sonarscanner end /d:sonar.login="$SONAR_TOKEN"

#############
## Stage 2 ##
#############
FROM mcr.microsoft.com/dotnet/aspnet:5.0-buster-slim
WORKDIR /app
COPY --from=build-env /app/publish .
#ENTRYPOINT ["dotnet", "Example.Assembly.Name.dll"]
ENTRYPOINT ["dotnet", "<Assembly_Name>.dll"]