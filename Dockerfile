FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/UserService-0.0.1-SNAPSHOT.jar userService.jar
ENTRYPOINT [ "java","-jar","/userService.jar" ]