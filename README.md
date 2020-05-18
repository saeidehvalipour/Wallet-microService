# wallet-microservice

Wallet MicroService is a Spring Boot project. its basically a service that manages player accounts and registers transactions for game players . Spring Framework is employed for this mini project as it provides the extension configuration 
on convention mode reducing the need to write a lot of configuration and boilerplate code.

# production ready dependencies
1. Basic health-check and monitoring functions using spring HealthIndicator ,This module has some of the great features like metrics, health check, log, info, etc. exposed as the endpoints. 
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-remote-shell</artifactId>
</dependency>
```
2. Logger configuration (log4j2.xml) in resource path
3. Since Spring Boot is not yet released to production, we will need to set a custom repository (http://repo.spring.io/milestone) to pull all the dependencies:
```xml
<pluginRepository>
	<id>spring-releases</id>
	<url>https://repo.spring.io/libs-release</url>
</pluginRepository>

<pluginRepository>
	<id>spring-releases</id>
	<url>https://repo.spring.io/libs-release</url>
</pluginRepository>
```

4. Dockerised container appraoch to deployments 
5. Adjusting the number of concurrent threads in application.properties i.e server.tomcat.max-threads=400
