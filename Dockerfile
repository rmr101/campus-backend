FROM openjdk:14.0
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
