# Smart Platform - Lottery Service

Lottery service for different lottery types

### Spring profiles
```
For production: -Dspring.profiles.active=prod
For development in local: default, so no need to set profile
```

### Dependencies
https://github.com/ProudProgrammer/smart-logging-filter

### Build
```
Manually:
$ git clone https://github.com/ProudProgrammer/smart-logging-filter.git
$ mvn clean install
$ git clone https://github.com/ProudProgrammer/smart-lottery-service.git
$ mvn clean install

With start script:
$ git clone https://github.com/ProudProgrammer/smart-tools.git
$ ./start.sh

See readme: https://github.com/ProudProgrammer/smart-tools/blob/master/README.md
Use helper of start script: $ ./start.sh -h
```

### System architecture of Smart Platform
![System Architecture](https://raw.githubusercontent.com/ProudProgrammer/smart-tools/master/plantuml/system-architecture.png)
