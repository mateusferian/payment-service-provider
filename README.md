# Payment Service Provider
project developed for the Pagar.me challenge, making payment transfers, 
processing debit or credit cards, being able to consult the total balance
of each type of transaction and create the amounts to be paid for these transactions.

### prerequisites

what do you need to run the project?
 * [Gradle](https://gradle.org/)
 * [MySQL](https://www.mysql.com/)
 * [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
 * [Docker Compose](https://docs.docker.com/compose/)

### how can we download the application?
#### SSH
```
git@gitlab.com:Mateusferian/payment-service-provider.git
```
#### HTTPs
```
https://gitlab.com/Mateusferian/payment-service-provider.git
```

### code versioning best practices
* Using Gitflow
* Using semantic commit

### how to run the application?
open the terminal and run the command below:

```
sh docker-compose-dev.sh
```

### how to run unit tests?
open the terminal and run the command below:

```
./gradlew test
```

### to access API documentation
Open your browser and go to the following link:
```
http://localhost:8080/swagger-ui.html#/
```
### technologies used

* [Gradle](https://gradle.org/) - from mobile apps to microservices, from small businesses to large enterprises, Gradle helps teams build, automate, and deliver better software, faster.
####
* [Spring Boot Web Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - starter for building web, including RESTful apps, using Spring MVC. Uses Tomcat as the default embedded container
####
* [Model Mapper](http://modelmapper.org/) - applications often consist of similar but different object models where the data in two models may be similar but the structure and concerns of the models are different. Object mapping makes it easy to convert from one model to another, allowing separate models to remain segregated.
####
* [Swagger](https://swagger.io/) - Simplify API development for users, teams, and enterprises with the open source, professional Swagger toolset.
####
* [Power Mock](https://powermock.github.io/) - PowerMock is a framework that extends other mock libraries like EasyMock with more powerful features. PowerMock uses a custom class loader and bytecode handling to allow mocking static methods, constructors, final classes and methods, private methods, removing static initializers, and much more.
