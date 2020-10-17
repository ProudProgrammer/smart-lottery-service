# Smart Platform - Lottery Service
Lottery service for different lottery types
### Spring profiles
```
For production: -Dspring.profiles.active=prod
For development in local: default, so no need to set profile
```
### Maven profiles
```
Without tests: mvn clean install -Pfast
With docker image creation: mvn clean install -Pdocker
Without tests and with docker image creation: mvn clean install -Pfast,docker
```
### Dependencies
- https://github.com/ProudProgrammer/smart-logging-filter
### Build
Manually (the order is important):
```
$ git clone https://github.com/ProudProgrammer/smart-logging-filter.git
$ mvn clean install
$ git clone https://github.com/ProudProgrammer/smart-lottery-service.git
$ mvn clean install
```
With build script:
- https://github.com/ProudProgrammer/smart-tools
```
$ git clone https://github.com/ProudProgrammer/smart-tools.git
$ ./build.sh
```
Usage of helper of build script:
```
$ ./build.sh -h
```
### Run
```
from project root
$ mvn spring-boot:run
or from target folder
$ java -jar lottery-service-release-1.0-SNAPSHOT.jar
```
With start script:
- https://github.com/ProudProgrammer/smart-tools
```
$ git clone https://github.com/ProudProgrammer/smart-tools.git
$ ./start.sh
```
Usage of helper of build script:
```
$ ./start.sh -h
```
### Solutions
* Data Validation with Bean Validation API
* Error Handling with @RestControllerAdvice
* Logging with SLF4J, MDC, AspectJ
* Localization with Spring MessageSource
* External HTTP calls with RestTemplate
* REST API documentation with Swagger
* Unit Tests with JUnit 5
### Modular architecture of Lottery Service
![Modular Architecture](https://raw.githubusercontent.com/ProudProgrammer/smart-tools/master/plantuml/modular-architecture-lottery-service.png)
### System architecture of Smart Platform
Applied software development techniques:
- Microservice Architecture
- API Gateway Pattern

![System Architecture](https://raw.githubusercontent.com/ProudProgrammer/smart-tools/master/plantuml/system-architecture.png)
