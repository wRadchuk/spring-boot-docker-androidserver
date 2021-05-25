FROM openjdk:8-jdk-alpine
ADD target/docker-spring-boot.jar docker-spring-boot.jar
EXPOSE 22:22
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]