FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar platforme.jar
ENTRYPOINT ["java","-jar","/platforme.jar"]